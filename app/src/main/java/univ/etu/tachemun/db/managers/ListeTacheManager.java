package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import univ.etu.tachemun.db.tableclass.ListeTache;

public class ListeTacheManager extends TableManager {

    static final String tableName = "ListeTache";

    static final String ID_LISTETACHE = "ID_ListeTache";
    private static final String nom_ListeTache = "nom";
    private static final String IS_PRIVE = "is_prive";
    private static final String DESCRIPTION = "description";
    private static final String DateHeure_Creation = "dateCreation";
    private static final String HAS_ECHEANCE = "has_echeance";
    private static final String ECHEANCE = "echeance";
    private static final String COULEUR = "couleur";


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

    public long insertNew(ListeTache l) {
        ContentValues v = putInContent(l);
        v.remove(ID_LISTETACHE);
        long dc = v.getAsLong(DateHeure_Creation);
        v.remove(DateHeure_Creation);
        v.put(DateHeure_Creation, dc);
        if (v.getAsBoolean(HAS_ECHEANCE)) {
            long ech = v.getAsLong(ECHEANCE);
            v.remove(DateHeure_Creation);
            v.put(DateHeure_Creation, ech);
        }

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

    public ArrayList<ListeTache> getListesOfUser(String username) {
//TODO
        ArrayList<ListeTache> listes = new ArrayList<>();

        this.open();
        String aliasID_LISTE_TACHE = "idlstch";

        Cursor cursor = db.rawQuery("SELECT " + tableName + "." + ID_LISTETACHE + " AS " + aliasID_LISTE_TACHE + " ," + nom_ListeTache + " ,"
                + IS_PRIVE + " ," + DESCRIPTION + " ," + DateHeure_Creation + " ," + HAS_ECHEANCE + " ," + ECHEANCE +
                " ," + COULEUR + " FROM " + tableName + " INNER JOIN " + ProprietaireListeManager.tableName
                + " ON " + aliasID_LISTE_TACHE + " = " + ProprietaireListeManager.tableName + "." + ProprietaireListeManager.ID_ListeTache + " WHERE "
                + ProprietaireListeManager.tableName + "." + ProprietaireListeManager.PSEUDO + "=\"" + username + "\"", null);


        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {

                ListeTache li = new ListeTache(cursor.getInt(cursor.getColumnIndex(aliasID_LISTE_TACHE)),
                        cursor.getString(cursor.getColumnIndex(nom_ListeTache)),
                        true, cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(DateHeure_Creation)),
                        (cursor.getInt(cursor.getColumnIndex(HAS_ECHEANCE)) == 1)
                        , cursor.getInt(cursor.getColumnIndex(ECHEANCE))
                        , cursor.getInt(cursor.getColumnIndex(COULEUR)));
                //System.out.println("LT " + li.toString() + " : " + li.getDateHeureCreation().getTime());
                listes.add(li);

            }
            this.close();

        } else {

        }
        cursor.close();
        return listes;
    }
    public ListeTache getFromId(int id) {
        return null;
    }
}
