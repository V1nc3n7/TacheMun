package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.EmailDisposable;

public class EmailDisposableManager extends TableManager {
    /*

CREATE TABLE email_disposable (
  id int(10) UNSIGNED NOT NULL,
  domaine varchar(255) DEFAULT NULL,
  ts_ajout timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

     */
    static final String tableName = "EmailDisposable";
    static final String ID_EMAILDISPOSABLE = "id";
    static final String DOMAINE = "domaine";
    static final String DateHeure_Ajout = "ts_ajout";


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

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
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
