package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.Contacts;

public class ContactsManager extends TableManager {


    private static final String tableName = "Contacts";
    private static final String ID_CONTACT = "ID_Contact";
    private static final String ID_Proprietaire = "ID_Proprietaire";
    private static final String ID_Utilisateur = "ID_Utilisateur";
    private static final String DateHeure_Contact = "DateHeure_Contact";


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

    private ContentValues putInContent(Contacts c) {
        ContentValues content = new ContentValues();
        content.put(ID_CONTACT, c.getID());
        content.put(ID_Proprietaire, c.getPseudoProprietaire());
        content.put(ID_Utilisateur, c.getPeudoContact());
        content.put(DateHeure_Contact, c.getDateHeureContact().getTime());
        return content;
    }
    public long insert(Contacts c) {
        ContentValues v = putInContent(c);
        v.remove(ID_CONTACT);
        return db.insert(tableName, null, v);
    }

    public int update(Contacts c) {
        ContentValues values = putInContent(c);
        String where = ID_CONTACT + " = ?";
        String[] whereArgs = {c.getID() + ""};
        return db.update(tableName, values, where, whereArgs);

    }

    public int delete(Contacts c) {

        String where = ID_CONTACT + " = ?";
        String[] whereArgs = {c.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public Contacts getFromId(int id) {
        return null;
    }
}
