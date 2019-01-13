package univ.etu.tachemun.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import univ.etu.tachemun.db.tablemanagers.ActionUserManager;
import univ.etu.tachemun.db.tablemanagers.AssigneTacheManager;
import univ.etu.tachemun.db.tablemanagers.ContactsManager;
import univ.etu.tachemun.db.tablemanagers.DroitsManager;
import univ.etu.tachemun.db.tablemanagers.EmailDisposableManager;
import univ.etu.tachemun.db.tablemanagers.GroupeManager;
import univ.etu.tachemun.db.tablemanagers.ListeTacheGroupeManager;
import univ.etu.tachemun.db.tablemanagers.ListeTacheManager;
import univ.etu.tachemun.db.tablemanagers.MembreManager;
import univ.etu.tachemun.db.tablemanagers.PartageListeManager;
import univ.etu.tachemun.db.tablemanagers.RealiseTacheManager;
import univ.etu.tachemun.db.tablemanagers.TacheManager;
import univ.etu.tachemun.db.tablemanagers.UtilisateurManager;

public class AndroidTask extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "androidTask.db";
    private static final int DATABASE_VERSION = 1;
    private static AndroidTask sInstance;
    private final Context context;
    private String DATABASE_PATH;


    private AndroidTask(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        String filesDir = context.getFilesDir().getPath(); // /data/data/com.package.nom/files/
        DATABASE_PATH = filesDir.substring(0, filesDir.lastIndexOf("/")) + "/databases/";
        // /data/data/com.package.nom/databases/

        if (!checkdatabase()) {
            copydatabase();
        }
    }

    public static synchronized AndroidTask getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AndroidTask(context);
        }
        return sInstance;
    }

    private boolean checkdatabase() {
        // retourne true/false si la bdd existe dans le dossier de l'app
        File dbfile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbfile.exists();
    }

    private void copydatabase() {

        final String outFileName = DATABASE_PATH + DATABASE_NAME;

        InputStream myInput;
        try {
            myInput = context.getAssets().open(DATABASE_NAME);

            File pathFile = new File(DATABASE_PATH
            );
            if (!pathFile.exists
                    ()) {
                if (!pathFile.mkdirs()) {
                    Toast.makeText(context, "Erreur : copydatabase(), pathFile.mkdirs()", Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
            }

            OutputStream myOutput = new FileOutputStream(
                    outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Fermeture
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            SQLiteDatabase checkdb = SQLiteDatabase.openDatabase(
                    DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase
                            .OPEN_READWRITE);
            checkdb.setVersion(DATABASE_VERSION);
        } catch (SQLiteException e) {
            // bdd n'existe pas
        }

    } // copydatabase()

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EmailDisposableManager.createTableScript);
        db.execSQL(UtilisateurManager.createTableScript);
        db.execSQL(ContactsManager.createTableScript);
        db.execSQL(ListeTacheManager.createTableScript);
        db.execSQL(TacheManager.createTableScript);
        db.execSQL(RealiseTacheManager.createTableScript);
        db.execSQL(GroupeManager.createTableScript);
        db.execSQL(MembreManager.createTableScript);
        db.execSQL(DroitsManager.createTableScript);
        db.execSQL(PartageListeManager.createTableScript);
        db.execSQL(AssigneTacheManager.createTableScript);
        db.execSQL(ListeTacheGroupeManager.createTableScript);
        db.execSQL(ActionUserManager.createTableScript);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {


        onCreate(sqLiteDatabase);
    }
}

