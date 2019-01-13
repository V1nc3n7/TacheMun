package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import univ.etu.tachemun.db.tableclass.RealiseTache;
import univ.etu.tachemun.db.tableclass.Tache;

public class TacheManager extends TableManager {

    static final String tableName = "Tache";
    static final String ID_Tache = "ID_Tache";
    private static final String ID_ListeTache = "ID_ListeTache";
    private static final String ID_Createur = "ID_Createur";
    private static final String LIBELLE = "libelle";
    private static final String DESCRIPTION = "description";
    private static final String DateHeure_Creation = "DateHeure_Creation";
    private static final String NUMERO = "numero";
    private static final String PRIORITE = "priorite";
    private static final String HAS_ECHEANCE = "has_echeance";
    private static final String ECHEANCE = "echeance";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_Tache + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_ListeTache + " TEXT NOT NULL,"
            + ID_Createur + " TEXT NOT NULL,"
            + LIBELLE + " TEXT ,"
            + DESCRIPTION + " TEXT,"
            + DateHeure_Creation + " INTEGER ,"

            + NUMERO + " INTEGER ,"
            + PRIORITE + " INTEGER ,"

            + HAS_ECHEANCE + " INTEGER ,"
            + ECHEANCE + " INTEGER "
            + ",FOREIGN KEY (" + ID_Createur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_LISTETACHE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";


    public TacheManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(Tache tache) {

        ContentValues values = new ContentValues();


        values.put(ID_Tache, tache.getID());
        values.put(ID_ListeTache, tache.getIdLsteTache());
        values.put(ID_Createur, tache.getCreateur());
        values.put(LIBELLE, tache.getLibelle());
        values.put(DESCRIPTION, tache.getDescription());
        values.put(DateHeure_Creation, tache.getDateHeureCreation().getTime());
        values.put(NUMERO, tache.getNumero());
        values.put(PRIORITE, tache.getPriorite());
        values.put(HAS_ECHEANCE, (tache.hasEcheance() ? 1 : 0));
        if (tache.hasEcheance())
            values.put(ECHEANCE, tache.getDateHeureEcheance().getTime());

        return values;
    }


    public long insert(Tache t) {
        ContentValues v = putInContent(t);
        v.remove(ID_Tache);
        v.remove(NUMERO);
        v.put(NUMERO, getNextNumTask(t));
        this.open();
        long r = db.insert(tableName, null, v);
        this.close();
        return r;
    }

    public long update(Tache t) {
        ContentValues values = putInContent(t);
        String where = ID_Tache + " = ?";
        String[] whereArgs = {t.getID() + ""};
        this.open();
        long r = db.update(tableName, values, where, whereArgs);
        this.close();
        return r;
    }

    public long delete(Tache t) {
        String where = ID_Tache + " = ?";
        String[] whereArgs = {t.getID() + ""};
        this.open();
        long r = db.delete(tableName, where, whereArgs);
        this.close();
        return r;

    }

    /**
     * @param idListe
     * @return les taches de la liste id_liste qui restent à faire
     */

    public ArrayList<Tache> getTachesNonRealiseesFromListe(int idListe) {
        ArrayList<Tache> list = new ArrayList<>();
        this.open();

        Cursor c = db.rawQuery(

                "SELECT " + tableName + "." + ID_Tache + " FROM " + tableName
                        + " WHERE " +
                        tableName + "." + ID_Tache + " NOT IN ( SELECT " + tableName + "." + ID_Tache + " FROM " + tableName +
                        " INNER JOIN " + RealiseTacheManager.tableName + " ON " + tableName + "." + ID_Tache + "=" + RealiseTacheManager.tableName + "." +
                        RealiseTacheManager.ID_Tache + " ) AND " + tableName + "." + ID_ListeTache + "=" + idListe, null);

        if (c.moveToFirst()) {
            do {
                list.add(getFromId(c.getInt(c.getColumnIndex(ID_Tache))));

            } while (c.moveToNext());


        }
        this.close();
        c.close();
        return list;
    }

    /**
     * @param id_liste
     * @return TOUTES les taches de la liste id_liste
     */
    public ArrayList<Tache> getAllTachesFromListe(int id_liste) {
        ArrayList<Tache> list = new ArrayList<>();
        this.open();
        Cursor c = db.rawQuery(

                "SELECT " + ID_Tache + " FROM " + tableName
                        + " WHERE " +
                        ID_ListeTache + "=" + id_liste, null);
        if (c.moveToFirst()) {
            do {
                list.add(getFromId(c.getInt(c.getColumnIndex(ID_Tache))));

            } while (c.moveToNext());


        }
        this.close();
        c.close();
        return list;
    }

    /**
     * @param id_liste
     * @return les taches de la liste id_liste qui on été réalisées
     */
    public ArrayList<Tache> getTachesRealFromListe(int id_liste) {
        ArrayList<Tache> list = new ArrayList<>();
        this.open();
        Cursor c = db.rawQuery(

                "SELECT " + tableName + "." + ID_Tache + " FROM " + tableName
                        + " WHERE " +
                        tableName + "." + ID_Tache + " IN ( SELECT " + tableName + "." + ID_Tache + " FROM " + tableName +
                        " INNER JOIN " + RealiseTacheManager.tableName + " ON " + tableName + "." + ID_Tache + "=" + RealiseTacheManager.tableName + "." +
                        RealiseTacheManager.ID_Tache + " ) AND " + tableName + "." + ID_ListeTache + "=" + id_liste, null);

        if (c.moveToFirst()) {
            do {
                list.add(getFromId(c.getInt(c.getColumnIndex(ID_Tache))));

            } while (c.moveToNext());


        }
        this.close();
        c.close();
        return list;
    }


    public long realiseTache(Tache t, String username, String message) {
        RealiseTacheManager realiseTacheManager = new RealiseTacheManager(context);
        return realiseTacheManager.insert(new RealiseTache(username, t.getID(), message));
    }


    /**
     * increment du numero de la tache dans la liste
     *
     * @param t
     * @return
     */
    private int getNextNumTask(Tache t) {
        int r = 0;
        this.open();
        String aliasnum = "NUMAL";
        Cursor c = db.rawQuery(
                "SELECT MAX(" + NUMERO + ") AS " + aliasnum + " FROM " + tableName
                        + " WHERE " +
                        ID_ListeTache + "=" + t.getIdLsteTache(), null);
        if (c.moveToFirst()) {
            r = c.getInt(c.getColumnIndex(aliasnum));

            r++;
        }

        this.close();
        c.close();
        return r;

    }


    public Tache getFromId(int id) {

        Tache t = new Tache();
        this.open();
        Cursor c = db.rawQuery(
                "SELECT " + ID_Tache + ", " + ID_ListeTache + ", " + ID_Createur
                        + " , " + LIBELLE + " , " + DESCRIPTION + " , "
                        + DateHeure_Creation + " , " + NUMERO + " , " + PRIORITE + " , "
                        + HAS_ECHEANCE + " , "
                        + ECHEANCE + " FROM " + tableName
                        + " WHERE " +
                        ID_Tache + "=" + id, null);
        if (c.moveToFirst()) {
            t.setID(c.getInt(c.getColumnIndex(ID_Tache)));
            t.setIdLsteTache(c.getInt(c.getColumnIndex(ID_ListeTache)));
            t.setCreateur(c.getString(c.getColumnIndex(ID_Createur)));
            t.setLibelle(c.getString(c.getColumnIndex(LIBELLE)));
            t.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));
            t.setDateHeureCreation(c.getLong(c.getColumnIndex(DateHeure_Creation)));
            t.setNumero(c.getInt(c.getColumnIndex(NUMERO)));
            t.setPriorite(c.getInt(c.getColumnIndex(PRIORITE)));
            if (c.getInt(c.getColumnIndex(HAS_ECHEANCE)) == 0)
                t.setDateHeureEcheance(null);
            else
                t.setDateHeureEcheance(c.getLong(c.getColumnIndex(ECHEANCE)));

            this.close();
            c.close();
            return t;
        } else {
            this.close();
            return null;
        }


    }


}
