package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.ProprietaireListe;

public class ProprietaireListeManager extends TableManager {


    static final String tableName = "ProprietaireListe";
    static final String ID_ProprietaireListe = "ID_ProprietaireListe";
    static final String PSEUDO = "pseudo";
    static final String ID_ListeTache = "ID_ListeTache";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_ProprietaireListe + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PSEUDO + " TEXT NOT NULL,"
            + ID_ListeTache + " INTEGER NOT NULL"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_LISTETACHE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + PSEUDO + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public ProprietaireListeManager(Context context) {
        super(context);
    }


        /*
     ContentValues v = putInContent(e);

      // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
      return db.insert(tableName, null, v);


      ContentValues values = putInContent(e);
      String where = ID_Tache + " = ?";
      String[] whereArgs = {e.getID() + ""};
      return db.update(tableName, values, where, whereArgs);


      String where = ID_Tache + " = ?";
      String[] whereArgs = {e.getID() + ""};
      return db.delete(tableName, where, whereArgs);
  */


    public long insert(ProprietaireListe e) {
        return 0;
    }

    public int update(ProprietaireListe e) {
        return 0;
    }

    public int delete(ProprietaireListe e) {
        return 0;
    }

    public ProprietaireListe getFromId(int id) {
        return null;
    }


}
