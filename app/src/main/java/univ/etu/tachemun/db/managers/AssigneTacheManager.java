package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.AssigneTache;

public class AssigneTacheManager extends TableManager {

    private static final String ID_ASSIGNE = "ID_AssigneTache";
    private static final String ID_ASSIGNEUR = "ID_MembreAssigneur";
    private static final String ID_TACHE = "ID_Tache";
    private static final String MESSAGE = "message";

    private static final String ID_ESCLAVE = "ID_tacheur";
    private static final String TIMESTAMP_ASSIGNATION = "dateHeure_AssigneTache";
    private static final String tableName = "AssigneTache";
    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_ASSIGNE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_ASSIGNEUR + " TEXT NOT NULL,"
            + ID_TACHE + " INTEGER NOT NULL,"
            + ID_ESCLAVE + " TEXT NOT NULL,"
            + MESSAGE + " TEXT ,"
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
        this.open();
        long l = db.insert(tableName, null, v);
        this.close();
        return l;
    }


    public long update(AssigneTache a) {
        ContentValues v = putInContent(a);
        String where = ID_ASSIGNE + " = ?";
        String[] whereArgs = {a.getID() + ""};
        this.open();
        long l = db.update(tableName, v, where, whereArgs);
        this.close();
        return l;
    }

    public long delete(AssigneTache a) {

        String where = ID_ASSIGNE + " = ?";
        String[] whereArgs = {a.getID() + ""};
        this.open();
        long l = db.delete(tableName, where, whereArgs);
        this.close();
        return l;
    }

    public AssigneTache getFromId(int id) {
        return null;
    }
}
