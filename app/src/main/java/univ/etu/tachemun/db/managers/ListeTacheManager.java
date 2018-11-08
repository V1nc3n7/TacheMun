package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.ListeTache;

public class ListeTacheManager extends TableManager {

    static final String tableName = "ListeTache";
    static final String ID_LISTETACHE = "ID_ListeTache";
    static final String nom_ListeTache = "nom";
    static final String IS_PRIVE = "is_prive";
    static final String DESCRIPTION = "description";
    static final String DateHeure_Creation = "dateCreation";
    static final String HAS_ECHEANCE = "has_echeance";
    static final String ECHEANCE = "echeance";
    static final String COULEUR = "couleur";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_LISTETACHE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + nom_ListeTache + " TEXT NOT NULL,"
            + IS_PRIVE + " INTEGER NOT NULL,"
            + DESCRIPTION + " TEXT,"
            + DateHeure_Creation + " INTEGER ,"
            + HAS_ECHEANCE + " INTEGER ,"
            + ECHEANCE + " INTEGER ,"
            + COULEUR + " INTEGER "

            + ");";

    public ListeTacheManager(Context context) {
        super(context);
    }


    private ContentValues putInContent(ListeTache l) {
        ContentValues c = new ContentValues();
        c.put(ID_LISTETACHE, l.getID());
        c.put(nom_ListeTache, l.getNom());
        c.put(IS_PRIVE, (l.isPerso() ? 1 : 0));
        c.put(DESCRIPTION, l.getDescription());
        c.put(DateHeure_Creation, l.getDateHeureCreation().getTime());
        c.put(HAS_ECHEANCE, (l.HasEcheance() ? 1 : 0));

        c.put(ECHEANCE, l.getDateHeureEcheance().getTime());
        c.put(COULEUR, l.getCouleur());


        return c;
    }

    public long insert(ListeTache l) {
        ContentValues v = putInContent(l);
        v.remove(ID_LISTETACHE);
        return db.insert(tableName, null, v);
    }

    public int update(ListeTache l) {

        ContentValues values = putInContent(l);
        String where = ID_LISTETACHE + " = ?";
        String[] whereArgs = {l.getID() + ""};
        return db.update(tableName, values, where, whereArgs);
    }

    public int delete(ListeTache l) {
        String where = ID_LISTETACHE + " = ?";
        String[] whereArgs = {l.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public ListeTache getFromId(int id) {
        return null;
    }
}
