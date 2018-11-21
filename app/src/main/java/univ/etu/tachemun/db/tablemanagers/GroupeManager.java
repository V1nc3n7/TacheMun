package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import univ.etu.tachemun.db.tableclass.Groupe;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.ListeTacheGroupe;
import univ.etu.tachemun.db.tableclass.Membre;

public class GroupeManager extends TableManager {

    static final String tableName = "Groupe";
    static final String ID_GROUPE = "ID_Groupe";
    private static final String ID_createur = "ID_createur";
    private static final String nom_Groupe = "nom";
    private static final String DateHeure_Creation = "dateCreation";
    private static final String DESCRIPTION = "description";
    private static final String IS_PRIVE = "is_prive";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            " " + ID_GROUPE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
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
        v.remove(ID_GROUPE);
        this.open();
        long l = db.insert(tableName, null, v);
        this.close();
        ListeTacheManager ltm = new ListeTacheManager(context);
        ListeTacheGroupeManager ltgm = new ListeTacheGroupeManager(context);
        MembreManager mm = new MembreManager(context);
        int idl = (int) ltm.insertNew(new ListeTache(-1, "Liste principale", false, groupe.getCreateur(), "Liste contenant les tâches à réaliser par les membres du groupe", groupe.getDateHeureCreation().getTime(), false, -1, 0));
        ltgm.insert(new ListeTacheGroupe((int) l, idl));
        mm.insert(new Membre(groupe.getCreateur(), (int) l));
        return l;

    }

    public long update(Groupe groupe) {


        ContentValues v = putInContent(groupe);
        String where = ID_GROUPE + " = ?";
        String[] whereArgs = {groupe.getID() + ""};
        this.open();
        long l = db.update(tableName, v, where, whereArgs);
        this.close();
        return l;

    }

    public long delete(Groupe groupe) {


        String where = ID_GROUPE + " = ?";
        String[] whereArgs = {groupe.getID() + ""};


        this.open();
        int idL = -1;
        Cursor c = db.rawQuery(

                "SELECT " + ListeTacheGroupeManager.tableName + "." + ListeTacheGroupeManager.ID_ListeTacheGroupe + " FROM " + ListeTacheGroupeManager.tableName +
                        " INNER JOIN " + tableName + " ON " + tableName + "." + ID_GROUPE
                        + " = " + ListeTacheGroupeManager.tableName + "." + ListeTacheGroupeManager.ID_GROUPE + " WHERE "
                        + ListeTacheGroupeManager.tableName + "." + ListeTacheGroupeManager.ID_GROUPE + "=" + groupe.getID(), null);

        if (c.moveToFirst()) {
            idL = c.getInt(c.getColumnIndex(ListeTacheGroupeManager.ID_ListeTacheGroupe));

        }
        this.close();
        c.close();
        ListeTacheGroupeManager listeTacheGroupeManager = new ListeTacheGroupeManager(context);
        listeTacheGroupeManager.delete(idL);



        this.open();
        long l = db.delete(tableName, where, whereArgs);
        this.close();
        return l;
    }

    public Groupe getFromId(int id) {
        return null;
    }


}
