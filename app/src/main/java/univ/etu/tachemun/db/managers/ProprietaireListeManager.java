package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.ProprietaireListe;

public class ProprietaireListeManager extends TableManager {

    /*


    CREATE TABLE ProprietaireListe (
      ID_ProprietaireListe int(11) NOT NULL,
      pseudo_Utilisateur_Proprietaire varchar(32) COLLATE utf8_unicode_ci NOT NULL,
      ID_ListeTache int(11) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


    ALTER TABLE ProprietaireListe
      ADD PRIMARY KEY (ID_ProprietaireListe),
      ADD KEY fk_proprietaireListe_Liste_idx (ID_ListeTache),
      ADD KEY fk_ProprietaireListe_Utilisateur_idx (pseudo_Utilisateur_Proprietaire);


    ALTER TABLE ProprietaireListe
      MODIFY ID_ProprietaireListe int(11) NOT NULL AUTO_INCREMENT;


    ALTER TABLE ProprietaireListe
      ADD CONSTRAINT fk_ProprietaireListe_ListeTache FOREIGN KEY (ID_ListeTache) REFERENCES ListeTache (ID_ListeTache) ON DELETE CASCADE ON UPDATE CASCADE,
      ADD CONSTRAINT fk_ProprietaireListe_Utilisateur FOREIGN KEY (pseudo_Utilisateur_Proprietaire) REFERENCES Utilisateur (pseudo_Utilisateur) ON DELETE CASCADE ON UPDATE CASCADE;
    COMMIT;

     */
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
