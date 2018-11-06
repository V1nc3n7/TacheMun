package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.ListeTacheGroupe;

public class ListeTacheGroupeManager extends TableManager {
    /*



CREATE TABLE `ListeTacheGroupe` (
  ID_ListeTacheGroupe int(11) NOT NULL,
  ID_Groupe varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  ID_ListeTache int(11) NOT NULL,
  ID_CreateurMembre varchar(32) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE ListeTacheGroupe
  ADD PRIMARY KEY (ID_ListeTacheGroupe),
  ADD KEY fk_ListeTacheGroupe_Utilisateur_idx (ID_CreateurMembre),
  ADD KEY fk_ListeTacheGroupe_Groupe_idx (ID_Groupe),
  ADD KEY fk_ListeTacheGroupe_ListeTache_idx (ID_ListeTache);


ALTER TABLE ListeTacheGroupe
  MODIFY ID_ListeTacheGroupe int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE ListeTacheGroupe
  ADD CONSTRAINT fk_ListeTacheGroupe_Groupe FOREIGN KEY (ID_Groupe) REFERENCES `Groupe` (ID_Groupe) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT fk_ListeTacheGroupe_ListeTache FOREIGN KEY (ID_ListeTache) REFERENCES `ListeTache` (ID_ListeTache) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT fk_ListeTacheGroupe_Utilisateur FOREIGN KEY (ID_CreateurMembre) REFERENCES `Utilisateur` (pseudo_Utilisateur) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

     */

    static final String tableName = "ListeTacheGroupe";
    static final String ID_ListeTacheGroupe = "ID_ListeTacheGroupe";
    static final String ID_GROUPE = "ID_Groupe";
    static final String ID_createur = "ID_createur";
    static final String ID_ListeTache = "ID_ListeTache";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +

            " " + ID_ListeTacheGroupe + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ID_GROUPE + " TEXT NOT NULL, "
            + ID_ListeTache + " INTEGER, "
            + ID_createur + " TEXT NOT NULL"
            + ",FOREIGN KEY (" + ID_GROUPE + ") REFERENCES " + GroupeManager.tableName + " (" + GroupeManager.ID_GROUPE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_createur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheGroupeManager.tableName + " (" + ListeTacheGroupeManager.ID_ListeTache + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public ListeTacheGroupeManager(Context context) {
        super(context);
    }


    public long insert(ListeTacheGroupe e) {
        return 0;
    }

    public int update(ListeTacheGroupe e) {
        return 0;
    }

    public int delete(ListeTacheGroupe e) {
        return 0;
    }

    public ListeTacheGroupe getFromId(int id) {
        return null;
    }


}
