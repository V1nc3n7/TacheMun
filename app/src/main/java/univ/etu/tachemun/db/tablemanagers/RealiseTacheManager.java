package univ.etu.tachemun.db.tablemanagers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.RealiseTache;

public class RealiseTacheManager extends TableManager {


    /*


CREATE TABLE RealiseTache (
  ID_RealiseTache int(11) NOT NULL,
  ID_realiseurTache varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  ID_Tache int(11) NOT NULL,
  dateHeureRealisation timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  details_RealiseTache varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE RealiseTache
  ADD PRIMARY KEY (ID_RealiseTache),
  ADD KEY fk_RealiseTache_Utilisateur_idx (ID_realiseurTache),
  ADD KEY fk_RealiseTache_Tache_idx (ID_Tache);


ALTER TABLE RealiseTache
  MODIFY ID_RealiseTache int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
    */
    static final String tableName = "RealiseTache";
    private static final String ID_RealiseTache = "ID_RealiseTache";
    private static final String ID_Realisateur = "ID_Realisateur";
    static final String ID_Tache = "ID_Tache";
    private static final String DateHeure_Realisation = "DateHeure_Realisation";
    private static final String DETAILS = "details";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_RealiseTache + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_Realisateur + " TEXT NOT NULL,"
            + ID_Tache + " INTEGER NOT NULL,"
            + DateHeure_Realisation + " INTEGER ,"
            + DETAILS + " TEXT "
            + ",FOREIGN KEY (" + ID_Tache + ") REFERENCES " + TacheManager.tableName + " (" + TacheManager.ID_Tache + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Realisateur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public RealiseTacheManager(Context context) {
        super(context);
    }
    private ContentValues putInContent(RealiseTache r) {
        ContentValues c = new ContentValues();
        c.put(ID_RealiseTache, r.getID());
        c.put(ID_Realisateur, r.getRealiseurTache());
        c.put(ID_Tache, r.getIdTache());
        c.put(DateHeure_Realisation, r.getDateHeurRealisation().getTime());
        c.put(DETAILS, r.getDetails());

        return c;
    }


    public long insert(RealiseTache r) {
        ContentValues v = putInContent(r);
        v.remove(ID_RealiseTache);

        this.open();
        long m = db.insert(tableName, null, v);
        this.close();
        return m;
    }

    public long update(RealiseTache r) {
        ContentValues values = putInContent(r);
        String where = ID_Tache + " = ?";
        String[] whereArgs = {r.getID() + ""};
        this.open();
        long l = db.update(tableName, values, where, whereArgs);
        this.close();
        return l;
    }

    public long delete(RealiseTache r) {

        String where = ID_Tache + " = ?";
        String[] whereArgs = {r.getID() + ""};
        this.open();
        long m = db.delete(tableName, where, whereArgs);
        this.close();
        return m;
    }

    public RealiseTache getFromId(int id) {
        return null;
    }


}
