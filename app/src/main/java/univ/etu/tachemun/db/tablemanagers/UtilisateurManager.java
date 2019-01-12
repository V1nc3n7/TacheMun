package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import univ.etu.tachemun.db.tableclass.ActionUser;
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


    private String genMdpViren(Utilisateur u, String input) {
        String m0, m1, m2, m3, m4, m5, m6, m7;
        m0 = getSHA256("motherlode");
        m1 = getSHA256("Crypto");
        m2 = getSHA256("absolument");
        m3 = getSHA256("vis ta vie bro");
        m4 = getSHA256("Android");
        m5 = getSHA256("Klapocius");
        m6 = getSHA256("Rosebud");
        m7 = getSHA256("Primary");
        String seed = null;
        switch ((int) u.getDateInscription().getTime() % 8) {
            case 0:
                seed = m0;
                break;
            case 1:
                seed = m1;
                break;
            case 2:
                seed = m2;
                break;
            case 3:
                seed = m3;
                break;
            case 4:
                seed = m4;
                break;
            case 5:
                seed = m5;
                break;
            case 6:
                seed = m6;
                break;
            case 7:
                seed = m7;
                break;
        }
        return (getSHA256(input.length() + getSHA256("" + u.getDateInscription().getTime()) + input + getSHA256("" + (seed))));
    }


    private String getSHA256(String input) {

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

        v.put(PASSWORD, genMdpViren(u, u.getMotDePasse()));
        this.open();
        long r = db.insert(tableName, null, v);
        this.close();
        ActionUserManager actionUserManager = new ActionUserManager(context);
        actionUserManager.insertNew(new ActionUser(u.getPseudo(), "INSCRIPTION"));
        ListeTacheManager listeTacheManager = new ListeTacheManager(context);
        Random random = new Random();
        ListeTache listeTache = new ListeTache(-1, "Liste principale", true, u.getPseudo()
                , "Liste principale contenant les tâches à réaliser", System.currentTimeMillis()
                , false, 0, random.nextInt(10));

        int idListe = (int) listeTacheManager.insertNew(listeTache);
        TacheManager tm = new TacheManager(context);
        int n = 1;
        int p = 1;
        tm.insert(new Tache(idListe, u.getPseudo(), "Tache n°" + n++, "Tache auto", p++, -1));
        tm.insert(new Tache(idListe, u.getPseudo(), "Tache n°" + n++, "Tache auto", p++, -1));
        tm.insert(new Tache(idListe, u.getPseudo(), "Tache n°" + n++, "Tache auto", p++, -1));
        tm.insert(new Tache(idListe, u.getPseudo(), "Tache n°" + n++, "Tache auto", p++, -1));
        tm.insert(new Tache(idListe, u.getPseudo(), "Tache n°" + n, "Tache auto", p, -1));

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
     * @return
     */
    public long updatePseudoUser(String ancienPseudo, String nouveauPseduo, String clearMdp) {
        //TODO verifier AVANT d'appeler ici que le nouveauPseduo n'est pas dans la db
        //TODO verifier bon mdp   if (connectUser(ancienPseudo,clearMdp))


        Utilisateur u = getFromId(ancienPseudo);
        ContentValues values = putInContent(u);
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {ancienPseudo + ""};

        values.remove(ID_UTILISATEUR);
        values.put(ID_UTILISATEUR, nouveauPseduo);

        values.remove(PASSWORD);
        values.put(PASSWORD, genMdpViren(u, clearMdp));
        ActionUserManager actionUserManager = new ActionUserManager(context);
        actionUserManager.insertNew(new ActionUser(ancienPseudo, "CHANGEMENT PSEUDO"));


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
        Utilisateur u = getFromId(userName);
        if (!isPseudoInDb(userName)) return false;

        if (genMdpViren(u, password).equals(u.getMotDePasse())) {
            ActionUserManager actionUserManager = new ActionUserManager(context);
            actionUserManager.insertNew(new ActionUser(userName, "CONNEXION"));
            return true;
        } else {
            ActionUserManager actionUserManager = new ActionUserManager(context);
            actionUserManager.insertNew(new ActionUser(userName, "BAD PASSWORD"));
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
            System.out.println("u : " + u.toString());
        }
        this.close();
        c.close();
        return u;

    }

    public boolean isPseudoInDb(String pseudo) {
        if (pseudo.equals("")) return false;
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
