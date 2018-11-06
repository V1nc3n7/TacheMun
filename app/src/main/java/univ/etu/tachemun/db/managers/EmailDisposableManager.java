package univ.etu.tachemun.db.managers;

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

    public long insert(EmailDisposable e) {
        return 0;
    }

    public int update(EmailDisposable e) {
        return 0;
    }

    public int delete(EmailDisposable e) {
        return 0;
    }

    public EmailDisposable getFromId(int id) {
        return null;
    }
}
