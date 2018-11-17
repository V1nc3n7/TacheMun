package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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

    /**
     * TEST avant lien bdde
     */
    private void put() {
        this.open();

        Cursor c = db.rawQuery(
                "SELECT " + DOMAINE + " FROM " + tableName, null);

        Log.i(getClass().toString(), "c.size " + c.getCount());
        if (c.getCount() == 0) {

            insert(new EmailDisposable("0815.ru0clickemail.com"));
            insert(new EmailDisposable("0-mail.com"));
            insert(new EmailDisposable("0wnd.net"));
            insert(new EmailDisposable("0wnd.org"));
            insert(new EmailDisposable("10minutemail.com"));
            insert(new EmailDisposable("20minutemail.com"));
            insert(new EmailDisposable("2prong.com"));
            insert(new EmailDisposable("3d-painting.com"));
            insert(new EmailDisposable("4warding.com"));
            insert(new EmailDisposable("4warding.net"));
            insert(new EmailDisposable("4warding.org"));
            insert(new EmailDisposable("9ox.net"));
            insert(new EmailDisposable("a-bc.net"));
            insert(new EmailDisposable("ag.us.to"));
            insert(new EmailDisposable("amilegit.com"));
            insert(new EmailDisposable("anonbox.net"));
            insert(new EmailDisposable("anonymbox.com"));
            insert(new EmailDisposable("antichef.com"));
            insert(new EmailDisposable("0815.ru0clickemail.com"));
            insert(new EmailDisposable("0-mail.com"));


        }
        c.close();
        this.close();
    }

    public boolean mailAccepted(String mail) {
        put();
        this.open();
        String d = mail.split("@")[1];
        Cursor c = db.rawQuery(
                "SELECT " + DOMAINE + " FROM " + tableName + " WHERE " +
                        DOMAINE + "=\"" + d + "\"", null);

        if (c.getCount() != 0) {
            c.close();
            this.close();
            return false;
        } else {
            return true;
        }
    }

    public EmailDisposable getFromId(int id) {
        return null;
    }
}
