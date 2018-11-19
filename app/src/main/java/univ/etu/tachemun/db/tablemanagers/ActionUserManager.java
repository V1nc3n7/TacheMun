package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.ActionUser;

public class ActionUserManager extends TableManager {

    static final String tableName = "ActionUser";

    private static final String ID_ActionUser = "ID_ActionUser";
    private static final String ID_UTILISATEUR = "ID_UTILISATEUR";
    private static final String DateHeure_ACTION = "dateHeureAction";
    private static final String LIBELLE_ACTION = "NomAction";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" + ID_ActionUser + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_UTILISATEUR + " TEXT NOT NULL ,"
            + DateHeure_ACTION + " INTEGER NOT NULL ,"
            + LIBELLE_ACTION + " TEXT NOT NULL "
            + ", FOREIGN KEY (" + ID_UTILISATEUR + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";


    public ActionUserManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(ActionUser action) {

        ContentValues values = new ContentValues();


        values.put(ID_ActionUser, action.getID());
        values.put(ID_UTILISATEUR, action.getUtilisateur());
        values.put(LIBELLE_ACTION, action.getAction());
        values.put(DateHeure_ACTION, action.getDateAction().getTime());


        return values;
    }


    public long insertNew(ActionUser a) {
        ContentValues v = putInContent(a);
        v.remove(ID_ActionUser);

        this.open();
        long r = db.insert(tableName, null, v);
        this.close();
        return r;
    }


}
