package univ.etu.tachemun.db.tablemanagers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import univ.etu.tachemun.db.AndroidTask;

abstract class TableManager {
    private static String createTableScript;
    SQLiteDatabase db;
    Context context;
    private String tableName;
    private AndroidTask base;

    TableManager(Context context) {

        this.base = AndroidTask.getInstance(context);
        this.context = context;
    }

    public static String getCreateTableScript() {
        return createTableScript;
    }

    public String getTableName() {
        return tableName;
    }

    void open() {
        //on ouvre la table en lecture/écriture
        db = base.getWritableDatabase();
    }

    void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public Cursor getAll() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
}
