package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.PartageListe;

public class PartageListeManager extends TableManager {

    private static final String tableName = "PartageListe";
    private static final String ID_PartageListe = "ID_PartageListe";
    private static final String ID_Proprietaire = "ID_Proprietaire";
    private static final String ID_ListeTache = "ID_ListeTache";
    private static final String ID_Invite = "ID_Invite";
    private static final String DateHeure_Partage = "DateHeure_Partage";
    private static final String MESSAGE = "message";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_PartageListe + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_Proprietaire + " TEXT NOT NULL,"
            + ID_ListeTache + " INTEGER NOT NULL,"
            + ID_Invite + " TEXT NOT NULL,"
            + DateHeure_Partage + " INTEGER ,"
            + MESSAGE + " TEXT "
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_LISTETACHE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Invite + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Proprietaire + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_ProprioListe + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public PartageListeManager(Context context) {
        super(context);
    }


    private ContentValues putInContent(PartageListe p) {
        ContentValues c = new ContentValues();
        c.put(ID_PartageListe, p.getID());
        c.put(ID_Proprietaire, p.getID());
        c.put(ID_ListeTache, p.getID());
        c.put(ID_Invite, p.getID());
        c.put(DateHeure_Partage, p.getID());
        c.put(MESSAGE, p.getID());

        return c;
    }

    public long insert(PartageListe p) {

        ContentValues v = putInContent(p);
        v.remove(ID_PartageListe);
        this.open();
        long l = db.insert(tableName, null, v);
        this.close();
        return l;
    }


    public long update(PartageListe p) {
        ContentValues values = putInContent(p);
        String where = ID_PartageListe + " = ?";
        String[] whereArgs = {p.getID() + ""};
        this.open();
        long l = db.update(tableName, values, where, whereArgs);
        this.close();
        return l;

    }

    public long delete(PartageListe p) {
        String where = ID_PartageListe + " = ?";
        String[] whereArgs = {p.getID() + ""};
        this.open();
        long l = db.delete(tableName, where, whereArgs);
        this.close();
        return l;
    }

    public PartageListe getFromId(int id) {
        return null;
    }


}
