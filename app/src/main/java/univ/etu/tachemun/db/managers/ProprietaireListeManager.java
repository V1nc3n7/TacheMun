package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.ProprietaireListe;

public class ProprietaireListeManager extends TableManager {


    static final String tableName = "ProprietaireListe";
    static final String ID_ProprietaireListe = "ID_ProprietaireListe";
    static final String PSEUDO = "pseudo";
    static final String ID_ListeTache = "ID_ListeTache";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_ProprietaireListe + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PSEUDO + " TEXT NOT NULL,"
            + ID_ListeTache + " INTEGER NOT NULL"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_LISTETACHE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + PSEUDO + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public ProprietaireListeManager(Context context) {
        super(context);
    }

    public long insert(ProprietaireListe p) {

        ContentValues v = putInContent(p);
        return db.insert(tableName, null, v);
    }

    public int update(ProprietaireListe p) {

        ContentValues values = putInContent(p);
        String where = ID_ProprietaireListe + " = ?";
        String[] whereArgs = {p.getID() + ""};
        return db.update(tableName, values, where, whereArgs);
    }

    private ContentValues putInContent(ProprietaireListe p) {
        ContentValues c = new ContentValues();
        c.put(ID_ProprietaireListe, p.getID());
        c.put(PSEUDO, p.getProprietaire());
        c.put(ID_ListeTache, p.getIdListe());
        return c;
    }

    public int delete(ProprietaireListe p) {

        String where = ID_ProprietaireListe + " = ?";
        String[] whereArgs = {p.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public ProprietaireListe getFromId(int id) {
        return null;
    }


}
