package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.EmailDisposable;

public class EmailDisposableManager extends TableManager {

    private static final String tableName = "EmailDisposable";
    private static final String ID_EMAILDISPOSABLE = "id";
    private static final String DOMAINE = "domaine";
    private static final String DateHeure_Ajout = "ts_ajout";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_EMAILDISPOSABLE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DOMAINE + " TEXT NOT NULL,"
            + DateHeure_Ajout + " INTEGER"
            + ");";

    public EmailDisposableManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(EmailDisposable e) {
        ContentValues v = new ContentValues();

        v.put(ID_EMAILDISPOSABLE, e.getID());
        v.put(DOMAINE, e.getDomaine());
        v.put(DateHeure_Ajout, e.getDateHeureAjout().getTime());


        return v;
    }

    public long insert(EmailDisposable e) {

        ContentValues v = putInContent(e);
        v.remove(ID_EMAILDISPOSABLE);
        return db.insert(tableName, null, v);
    }

    public int update(EmailDisposable e) {


        ContentValues values = putInContent(e);
        String where = ID_EMAILDISPOSABLE + " = ?";
        String[] whereArgs = {e.getID() + ""};
        return db.update(tableName, values, where, whereArgs);
    }

    public int delete(EmailDisposable e) {


        String where = ID_EMAILDISPOSABLE + " = ?";
        String[] whereArgs = {e.getID() + ""};
        return db.delete(tableName, where, whereArgs);

    }

    public EmailDisposable getFromId(int id) {
        return null;
    }
}
