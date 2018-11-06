package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.ListeTache;

public class ListeTacheManager extends TableManager {

    /*

CREATE TABLE ListeTache (
  ID_ListeTache int(11) NOT NULL,
  nom_ListeTache varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  boolPerso_ListeTache tinyint(1) NOT NULL COMMENT '1= liste Perso ,0=liste groupe',
  description_ListeTache varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  dateHeureCreation_ListeTache timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  echeanceTotale_ListeTache datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE ListeTache
  ADD PRIMARY KEY (ID_ListeTache);


ALTER TABLE ListeTache
  MODIFY ID_ListeTache int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
     */
    static final String tableName = "ListeTache";
    static final String ID_LISTETACHE = "ID_ListeTache";
    static final String nom_ListeTache = "nom";
    static final String IS_PRIVE = "is_prive";
    static final String DESCRIPTION = "description";
    static final String DateHeure_Creation = "dateCreation";
    static final String HAS_ECHEANCE = "has_echeance";
    static final String ECHEANCE = "echeance";
    static final String COULEUR = "couleur";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_LISTETACHE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + nom_ListeTache + " TEXT NOT NULL,"
            + IS_PRIVE + " INTEGER NOT NULL,"
            + DESCRIPTION + " TEXT,"
            + DateHeure_Creation + " INTEGER ,"
            + HAS_ECHEANCE + " INTEGER ,"
            + ECHEANCE + " INTEGER ,"
            + COULEUR + " INTEGER "

            + ");";

    public ListeTacheManager(Context context) {
        super(context);
    }

    public long insert(ListeTache l) {
        return 0;
    }

    public int update(ListeTache l) {
        return 0;
    }

    public int delete(ListeTache l) {
        return 0;
    }

    public ListeTache getFromId(int id) {
        return null;
    }
}
