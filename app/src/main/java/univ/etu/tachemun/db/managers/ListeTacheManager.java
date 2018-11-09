package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import java.util.Collection;

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
        if (l.HasEcheance()) {
            c.put(ECHEANCE, l.getDateHeureEcheance().getTime());
        }
        c.put(COULEUR, l.getCouleur());


        return c;
    }

    public long insert(ListeTache l) {
        ContentValues v = putInContent(l);
        v.remove(ID_LISTETACHE);
        this.open();
        long m = db.insert(tableName, null, v);
        this.close();
        return m;
    }

    public long update(ListeTache l) {

        ContentValues values = putInContent(l);
        String where = ID_LISTETACHE + " = ?";
        String[] whereArgs = {l.getID() + ""};
        this.open();
        long m = db.update(tableName, values, where, whereArgs);
        this.close();
        return m;
    }

    public long delete(ListeTache l) {
        String where = ID_LISTETACHE + " = ?";
        String[] whereArgs = {l.getID() + ""};
        this.open();
        long m = db.delete(tableName, where, whereArgs);
        this.close();
        return m;
    }

    public Collection<ListeTache> getListesOfUser(String username) {
//TODO
        Collection<ListeTache> collection;

        return null;
    }
    public ListeTache getFromId(int id) {
        return null;
    }
}
