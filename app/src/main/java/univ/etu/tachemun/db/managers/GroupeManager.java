package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.Groupe;

public class GroupeManager extends TableManager {

    static final String tableName = "Groupe";
    static final String ID_GROUPE = "ID_Groupe";
    static final String ID_createur = "ID_createur";
    static final String nom_Groupe = "nom";
    static final String DateHeure_Creation = "dateCreation";
    static final String DESCRIPTION = "description";
    static final String IS_PRIVE = "is_prive";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_GROUPE + " TEXT PRIMARY KEY,"
            + ID_createur + " TEXT NOT NULL,"
            + nom_Groupe + " TEXT NOT NULL,"
            + DateHeure_Creation + " INTEGER ,"
            + DESCRIPTION + " TEXT,"
            + IS_PRIVE + " INTEGER NOT NULL"
            + ",FOREIGN KEY (" + ID_createur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"

            + ");";


    public GroupeManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(Groupe groupe) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_GROUPE, groupe.getID());
        contentValues.put(ID_createur, groupe.getCreateur());
        contentValues.put(nom_Groupe, groupe.getNom());
        contentValues.put(DateHeure_Creation, groupe.getDateHeureCreation().getTime());
        contentValues.put(DESCRIPTION, groupe.getDescription());
        contentValues.put(IS_PRIVE, (groupe.isPrivate() ? 1 : 0));

        return contentValues;


    }

    public long insert(Groupe groupe) {
        ContentValues v = putInContent(groupe);

        return db.insert(tableName, null, v);

    }

    public int update(Groupe groupe) {


        ContentValues v = putInContent(groupe);
        String where = ID_GROUPE + " = ?";
        String[] whereArgs = {groupe.getID() + ""};


        return db.update(tableName, v, where, whereArgs);
    }

    public int delete(Groupe groupe) {


        String where = ID_GROUPE + " = ?";
        String[] whereArgs = {groupe.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public Groupe getFromId(int id) {
        return null;
    }


}
