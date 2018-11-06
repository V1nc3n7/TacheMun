package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.Contacts;

public class ContactsManager extends TableManager {

 /*
     `ID_Contact` int(11) NOT NULL,
  `ID_Proprietaire` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `DateHeure_Contact` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */

    static final String tableName = "Contacts";
    static final String ID_CONTACT = "ID_Contact";
    static final String ID_Proprietaire = "ID_Proprietaire";
    static final String ID_Utilisateur = "ID_Utilisateur";
    static final String DateHeure_Contact = "DateHeure_Contact";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_CONTACT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_Proprietaire + " TEXT NOT NULL,"
            + ID_Utilisateur + " TEXT NOT NULL,"
            + DateHeure_Contact + " INTEGER"
            + ",FOREIGN KEY (" + ID_Proprietaire + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Utilisateur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public ContactsManager(Context context) {
        super(context);


    }

    public long insert(Contacts c) {
        return 0;
    }

    public int update(Contacts c) {
        return 0;
    }

    public int delete(Contacts c) {
        return 0;
    }

    public Contacts getFromId(int id) {
        return null;
    }
}
