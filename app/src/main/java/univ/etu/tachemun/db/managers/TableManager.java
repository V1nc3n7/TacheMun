package univ.etu.tachemun.db.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import univ.etu.tachemun.db.AndroidTask;

public abstract class TableManager<T> {
    static String createTableScript;
    String tableName;
    SQLiteDatabase db;

    private AndroidTask base;

    public TableManager(Context context) {

        this.base = AndroidTask.getInstance(context);
    }

    public static String getCreateTableScript() {
        return createTableScript;
    }

    public String getTableName() {
        return tableName;
    }

    public void open() {
        //on ouvre la table en lecture/écriture
        db = base.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public Cursor getAll() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
}
