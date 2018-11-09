package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.AssigneTache;

public class AssigneTacheManager extends TableManager {

    static final String ID_ASSIGNE = "ID_AssigneTache";
    static final String ID_ASSIGNEUR = "ID_MembreAssigneur";
    static final String ID_TACHE = "ID_Tache";
    static final String MESSAGE = "message";

    static final String ID_ESCLAVE = "ID_tacheur";
    static final String TIMESTAMP_ASSIGNATION = "dateHeure_AssigneTache";
    static final String tableName = "AssigneTache";
    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_ASSIGNE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_ASSIGNEUR + " TEXT NOT NULL,"
            + ID_TACHE + " INTEGER NOT NULL,"
            + ID_ESCLAVE + " TEXT NOT NULL,"
            + " " + MESSAGE + " TEXT ,"
            + TIMESTAMP_ASSIGNATION + " INTEGER ,"
            + "FOREIGN KEY (" + ID_ESCLAVE + ") REFERENCES " + MembreManager.tableName + " (" + MembreManager.PSEUDO + ")" +
            "  ON UPDATE CASCADE ON DELETE SET NULL ,"
            + "FOREIGN KEY (" + ID_ASSIGNEUR + ") REFERENCES " + MembreManager.tableName + " (" + MembreManager.PSEUDO + ")" +
            "  ON UPDATE CASCADE ON DELETE SET NULL ,"
            + "FOREIGN KEY (" + ID_TACHE + ") REFERENCES " + TacheManager.tableName + " (" + TacheManager.ID_Tache + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public AssigneTacheManager(Context context) {
        super(context);


    }

    private ContentValues putInContent(AssigneTache a) {
        ContentValues c = new ContentValues();
        c.put(ID_ASSIGNE, a.getID());
        c.put(ID_ASSIGNEUR, a.getAssigneur());
        c.put(ID_TACHE, a.getIdTache());
        c.put(ID_ESCLAVE, a.getEsclave());
        c.put(MESSAGE, a.getMessage());
        c.put(TIMESTAMP_ASSIGNATION, a.getDateHeureAssignation().getTime());
        return c;
    }

    public long insert(AssigneTache a) {
        ContentValues v = putInContent(a);
        v.remove(ID_ASSIGNE);
        return db.insert(tableName, null, v);
    }


    public int update(AssigneTache a) {
        ContentValues v = putInContent(a);
        String where = ID_ASSIGNE + " = ?";
        String[] whereArgs = {a.getID() + ""};
        return db.update(tableName, v, where, whereArgs);
    }

    public int delete(AssigneTache a) {

        String where = ID_ASSIGNE + " = ?";
        String[] whereArgs = {a.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public AssigneTache getFromId(int id) {
        return null;
    }
}
