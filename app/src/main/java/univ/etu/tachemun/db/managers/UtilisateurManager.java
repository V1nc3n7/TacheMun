package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tableclass.Utilisateur;

public class UtilisateurManager extends TableManager {

    static final String tableName = "Utilisateur";
    static final String ID_UTILISATEUR = "pseudo_Utilisateur";
    private static final String PASSWORD = "motDePasse_Utilisateur";
    private static final String MAIL = "mail_Utilisateur";
    private static final String DateHeure_INSCRIPTION = "dateInscription_Utilisateur";

    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" + ID_UTILISATEUR + " TEXT PRIMARY KEY NOT NULL,"
            + PASSWORD + " TEXT NOT NULL,"

            + DateHeure_INSCRIPTION + " INTEGER,"
            + MAIL + " TEXT NOT NULL"
            + ");";


    /**
     * @param context
     */
    public UtilisateurManager(Context context) {
        super(context);
    }

    private static String getSHA256(String input) {

        String toReturn = null;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    /**
     * @param u
     * @return
     */
    private ContentValues putInContent(Utilisateur u) {
        ContentValues values = new ContentValues();
        values.put(ID_UTILISATEUR, u.getPseudo());
        values.put(PASSWORD, u.getMotDePasse());
        values.put(MAIL, u.getMail());
        values.put(DateHeure_INSCRIPTION, u.getDateInscription().getTime());
        return values;
    }

    public long insertRaw(Utilisateur u) {
        ContentValues v = putInContent(u);
        this.open();
        long r = db.insert(tableName, null, v);
        this.close();
        return r;
    }

    public long insertNew(Utilisateur u) {
        ContentValues v = putInContent(u);
        v.remove(PASSWORD);
        v.put(PASSWORD, getSHA256(u.getMotDePasse()));
        System.out.println("u= " + u.toString());
        this.open();
        long r = db.insert(tableName, null, v);
        this.close();

        ListeTacheManager listeTacheManager = new ListeTacheManager(context);

        System.out.println("u2= " + getFromId(u.getPseudo()).toString());


        Random random = new Random();
        ListeTache listeTache = new ListeTache(-1, "Liste principale", true, u.getPseudo()
                , "Liste principale contenant les tâches à réaliser", System.currentTimeMillis()
                , false, 0, random.nextInt(10));

        int idListe = (int) listeTacheManager.insertNew(listeTache);
        TacheManager tm = new TacheManager(context);
        int n = 0;
        tm.insert(new Tache(-1, idListe, u.getPseudo(), "Tache " + n++, "Tache auto", System.currentTimeMillis(), n, n, -1));
        tm.insert(new Tache(-1, idListe, u.getPseudo(), "Tache " + n++, "Tache auto", System.currentTimeMillis(), n, n, -1));
        tm.insert(new Tache(-1, idListe, u.getPseudo(), "Tache " + n++, "Tache auto", System.currentTimeMillis(), n, n, -1));
        tm.insert(new Tache(-1, idListe, u.getPseudo(), "Tache " + n++, "Tache auto", System.currentTimeMillis(), n, n, -1));
        tm.insert(new Tache(-1, idListe, u.getPseudo(), "Tache " + n++, "Tache auto", System.currentTimeMillis(), n, n, -1));

        return r;
    }

    public long update(Utilisateur u) {
        ContentValues v = putInContent(u);
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {u.getPseudo() + ""};
        this.open();
        long r = db.update(tableName, v, where, whereArgs);
        this.close();
        return r;
    }

    /**
     * permet de changer le pseudo d'un utilisateur
     *
     * @param ancienPseudo
     * @param u
     * @return
     */
    public long updatePseudoUser(String ancienPseudo, Utilisateur u) {
        ContentValues values = putInContent(u);
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {ancienPseudo + ""};
        this.open();
        long r = db.update(tableName, values, where, whereArgs);
        this.close();
        return r;
    }

    public long delete(Utilisateur u) {
        this.open();
        long r = delete(u.getPseudo());
        this.close();
        return r;
    }

    private long delete(String pseudo) {

        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {pseudo + ""};
        this.open();
        long r = db.delete(tableName, where, whereArgs);
        this.close();
        return r;
    }

    public boolean connectUser(String userName, String password) {
        this.open();
        Cursor c = db.rawQuery(
                "SELECT " + ID_UTILISATEUR + " FROM " + tableName + " WHERE " +
                        ID_UTILISATEUR + "=\"" + userName + "\" AND " + PASSWORD + "=\"" + getSHA256(password) + "\"", null);

        if (c.getCount() != 0) {
            c.close();
            this.close();
            return true;
        } else {

            c.close();
            this.close();
            return false;
        }

    }

    private Utilisateur getFromId(String id) {

        Utilisateur u = null;
        this.open();
        Cursor c = db.rawQuery("SELECT " + ID_UTILISATEUR
                + " , " + PASSWORD
                + " , " + MAIL
                + " , " + DateHeure_INSCRIPTION
                + " FROM " + tableName + " WHERE " +
                ID_UTILISATEUR + "=\"" + id + "\";", null);


        if (c.moveToFirst()) {
            u = new Utilisateur(c.getString(c.getColumnIndex(ID_UTILISATEUR)),
                    c.getString(c.getColumnIndex(PASSWORD)), c.getString(c.getColumnIndex(MAIL)), c.getLong(c.getColumnIndex(DateHeure_INSCRIPTION)));


        }
        this.close();
        c.close();
        return u;

    }

    public boolean isPseudoInDb(String pseudo) {
        this.open();
        Cursor c = db.rawQuery(
                "SELECT " + ID_UTILISATEUR + " FROM " + tableName + " WHERE " +
                        ID_UTILISATEUR + "=\"" + pseudo + "\"", null);

        if (c.getCount() != 0) {
            this.close();
            c.close();
            return true;
        } else {
            this.close();
            c.close();
            return false;
        }

    }

    public boolean isMailInDb(String mailInput) {
        this.open();
        Cursor c = db.rawQuery(
                "SELECT " + ID_UTILISATEUR + " FROM " + tableName + " WHERE " +
                        MAIL + "=\"" + mailInput + "\"", null);

        if (c.getCount() != 0) {
            this.close();
            c.close();
            return true;
        } else {
            this.close();
            c.close();
            return false;
        }

    }

    public Set<Utilisateur> getUtilisateurs() {
        HashSet<Utilisateur> r = new HashSet<>();
        this.open();
        Cursor c = db.rawQuery(
                "SELECT " + ID_UTILISATEUR + ", " + PASSWORD + ", " + MAIL + " , " + DateHeure_INSCRIPTION + " FROM " + tableName, null);

        while (c.moveToNext()) {


            r.add(new Utilisateur(c.getString(c.getColumnIndex(ID_UTILISATEUR)),
                    c.getString(c.getColumnIndex(PASSWORD)), c.getString(c.getColumnIndex(MAIL)), c.getInt(c.getColumnIndex(DateHeure_INSCRIPTION))));

        }

        this.close();
        c.close();
        return r;
    }
}
