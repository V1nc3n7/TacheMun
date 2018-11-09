package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import univ.etu.tachemun.db.tableclass.Utilisateur;

public class UtilisateurManager extends TableManager {





    /*
    `pseudo_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateInscription_Utilisateur` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `motDePasse_Utilisateur` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `mail_Utilisateur` varchar(255) COLLATE utf8_unicode_ci NOT NULL
  */

    static final String tableName = "Utilisateur";
    static final String ID_UTILISATEUR = "pseudo_Utilisateur";
    static final String PASSWORD = "motDePasse_Utilisateur";
    static final String MAIL = "mail_Utilisateur";
    static final String DateHeure_INSCRIPTION = "dateInscription_Utilisateur";


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
        return db.insert(tableName, null, v);
    }

    public long insertNew(Utilisateur u) {
        ContentValues v = putInContent(u);
        v.remove(PASSWORD);
        v.put(PASSWORD, getSHA256(u.getMotDePasse()));
        return db.insert(tableName, null, v);
    }

    public int update(Utilisateur u) {
        ContentValues v = putInContent(u);
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {u.getPseudo() + ""};
        return db.update(tableName, v, where, whereArgs);
    }

    /**
     * permet de changer le pseudo d'un utilisateur
     *
     * @param ancienPseudo
     * @param u
     * @return
     */
    public int updatePseudoUser(String ancienPseudo, Utilisateur u) {


        ContentValues values = putInContent(u);
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {ancienPseudo + ""};
        return db.update(tableName, values, where, whereArgs);
    }


    public int delete(Utilisateur u) {

        return delete(u.getPseudo());
    }

    private int delete(String pseudo) {
        String where = ID_UTILISATEUR + " = ?";
        String[] whereArgs = {pseudo + ""};
        return db.delete(tableName, where, whereArgs);
    }


    public boolean connectUser(String userName, String password) {


        Utilisateur u = new Utilisateur();

        Cursor c = db.rawQuery(
                    "SELECT " + ID_UTILISATEUR + " FROM " + tableName + " WHERE " +
                            ID_UTILISATEUR + "=" + userName + " AND " + PASSWORD + "=" + getSHA256(password), null);
        if (c.moveToFirst()) {

            c.close();
            return true;
        } else {
            return false;
        }

    }
    public Utilisateur getFromId(String id) {


        Utilisateur u = new Utilisateur();

        Cursor c = db.rawQuery(
                "SELECT " + ID_UTILISATEUR + ", " + PASSWORD + ", " + MAIL + " , " + DateHeure_INSCRIPTION + " FROM " + tableName + " WHERE " +
                        ID_UTILISATEUR + "=" + id, null);
        if (c.moveToFirst()) {
            u.setPseudo(c.getString(c.getColumnIndex(ID_UTILISATEUR)));
            u.setMotDePasse(c.getString(c.getColumnIndex(PASSWORD)));
            u.setMail(c.getString(c.getColumnIndex(MAIL)));
            u.setDateInscription(c.getInt(c.getColumnIndex(DateHeure_INSCRIPTION)));
            c.close();
            return u;
        } else {
            return null;
        }

    }

    public boolean isPseudoInDb(String pseudo) {

        Cursor c = null;
        if(db.isOpen()) {
            c = db.rawQuery(
                    "SELECT " + ID_UTILISATEUR + " FROM " + tableName + " WHERE " +
                            ID_UTILISATEUR + "= \"" + pseudo +"\"", null);
        }
        if (c.moveToFirst()) {

            c.close();
            return true;
        } else {
            return false;
        }

    }
}
