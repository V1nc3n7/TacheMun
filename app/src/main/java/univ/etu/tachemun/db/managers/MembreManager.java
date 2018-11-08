package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.Membre;

public class MembreManager extends TableManager {
    static final String tableName = "Membre";
    static final String ID_MEMBRE = "ID_Membre";
    static final String PSEUDO = "pseudo";
    static final String DateHeure_Adhesion = "dateAdhesion";
    static final String ID_GROUPE = "ID_GROUPE";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_MEMBRE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PSEUDO + " TEXT NOT NULL,"

            + DateHeure_Adhesion + " INTEGER ,"
            + ID_GROUPE + " TEXT "
            + ",FOREIGN KEY (" + ID_GROUPE + ") REFERENCES " + GroupeManager.tableName + " (" + GroupeManager.ID_GROUPE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + PSEUDO + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";


    public MembreManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(Membre m) {
        ContentValues c = new ContentValues();
        c.put(ID_MEMBRE, m.getID());
        c.put(PSEUDO, m.getPseudoUtilisteur());
        c.put(DateHeure_Adhesion, m.getDateHeureAdhesion().getTime());
        c.put(ID_GROUPE, m.getIdGroupe());

        return c;
    }


    public long insert(Membre m) {
        ContentValues v = putInContent(m);
        v.remove(ID_MEMBRE);
        return db.insert(tableName, null, v);


    }


    public int update(Membre m) {

        ContentValues values = putInContent(m);
        String where = ID_MEMBRE + " = ?";
        String[] whereArgs = {m.getID() + ""};


        return db.update(tableName, values, where, whereArgs);


    }

    public int delete(Membre m) {


        String where = ID_MEMBRE + " = ?";
        String[] whereArgs = {m.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public Membre getFromId(int id) {
        return null;
    }


}
