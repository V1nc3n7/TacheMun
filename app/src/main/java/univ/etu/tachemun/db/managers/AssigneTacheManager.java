package univ.etu.tachemun.db.managers;

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
            + TIMESTAMP_ASSIGNATION + " INTEGER"
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

    public long insert(AssigneTache a) {
        return 0;
    }

    public int update(AssigneTache a) {
        return 0;
    }

    public int delete(AssigneTache a) {
        return 0;
    }

    public AssigneTache getFromId(int id) {
        return null;
    }
}
