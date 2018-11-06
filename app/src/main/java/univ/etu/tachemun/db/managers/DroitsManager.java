package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.Droits;

public class DroitsManager extends TableManager {
    static final String tableName = "Droits";
    static final String ID_DROIT = "ID_Droits";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_DROIT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ");";


    public DroitsManager(Context context) {
        super(context);


    }

    public long insert(Droits d) {
        return 0;
    }

    public int update(Droits d) {
        return 0;
    }

    public int delete(Droits d) {
        return 0;
    }

    public Droits getFromId(int id) {
        return null;
    }
}
