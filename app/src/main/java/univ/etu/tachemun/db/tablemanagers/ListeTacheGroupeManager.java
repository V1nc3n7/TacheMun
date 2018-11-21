package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.ListeTacheGroupe;

public class ListeTacheGroupeManager extends TableManager {


    static final String tableName = "ListeTacheGroupe";
    static final String ID_ListeTacheGroupe = "ID_ListeTacheGroupe";
    static final String ID_GROUPE = "ID_Groupe";
    private static final String ID_ListeTache = "ID_ListeTache";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +

            " " + ID_ListeTacheGroupe + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ID_GROUPE + " INTEGER NOT NULL, "
            + ID_ListeTache + " INTEGER NOT NULL "
            + ",FOREIGN KEY (" + ID_GROUPE + ") REFERENCES " + GroupeManager.tableName + " (" + GroupeManager.ID_GROUPE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheGroupeManager.tableName + " (" + ListeTacheGroupeManager.ID_ListeTache + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public ListeTacheGroupeManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(ListeTacheGroupe l) {
        ContentValues v = new ContentValues();
        v.put(ID_ListeTacheGroupe, l.getID());
        v.put(ID_GROUPE, l.getIdGroupe());
        v.put(ID_ListeTache, l.getIdListeTache());
        return v;
    }


    public long insert(ListeTacheGroupe e) {

        ContentValues v = putInContent(e);
        v.remove(ID_ListeTacheGroupe);
        this.open();
        long l = db.insert(tableName, null, v);
        this.close();
        return l;

    }

    public long update(ListeTacheGroupe e) {


        ContentValues values = putInContent(e);
        String where = ID_ListeTacheGroupe + " = ?";
        String[] whereArgs = {e.getID() + ""};
        this.open();
        long l = db.update(tableName, values, where, whereArgs);
        this.close();
        return l;
    }

    public long delete(ListeTacheGroupe e) {

        return delete(e.getID());
    }

    public long delete(int id) {
        String where = ID_ListeTacheGroupe + " = ?";
        String[] whereArgs = {id + ""};
        this.open();
        long l = db.delete(tableName, where, whereArgs);
        this.close();
        return l;
    }




    public ListeTacheGroupe getFromId(int id) {
        return null;
    }


}
