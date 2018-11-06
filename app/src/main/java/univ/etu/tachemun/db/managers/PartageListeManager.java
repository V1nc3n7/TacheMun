package univ.etu.tachemun.db.managers;

import android.content.Context;

import univ.etu.tachemun.db.tableclass.PartageListe;

public class PartageListeManager extends TableManager {
/*

CREATE TABLE PartageListe (
  ID_PartageListe int(11) NOT NULL,
  ID_ProprietaireListe varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  ID_ListeTache int(11) NOT NULL,
  ID_UtilisateurInvite varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  dateHeurePartage timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  messagePartage varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE PartageListe
  ADD PRIMARY KEY (ID_PartageListe),
  ADD KEY fk_PartageListe_ListeTache (ID_ListeTache),
  ADD KEY fk_PartageListe_Utilisateur_Proprio (ID_ProprietaireListe),
  ADD KEY fk_PartageListe_Utilisateur_Invite (ID_UtilisateurInvite);


ALTER TABLE PartageListe
  MODIFY ID_PartageListe int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE PartageListe
  ADD CONSTRAINT fk_PartageListe_ListeTache FOREIGN KEY (ID_ListeTache) REFERENCES ListeTache (ID_ListeTache),
  ADD CONSTRAINT fk_PartageListe_Utilisateur_Invite FOREIGN KEY (ID_UtilisateurInvite) REFERENCES Utilisateur (pseudo_Utilisateur),
  ADD CONSTRAINT fk_PartageListe_Utilisateur_Proprio FOREIGN KEY (ID_ProprietaireListe) REFERENCES ProprietaireListe (pseudo_Utilisateur_Proprietaire);
COMMIT;
 */

    static final String tableName = "PartageListe";
    static final String ID_PartageListe = "ID_PartageListe";
    static final String ID_Proprietaire = "ID_Proprietaire";
    static final String ID_ListeTache = "ID_ListeTache";
    static final String ID_Invite = "ID_Invite";
    static final String DateHeure_Partage = "DateHeure_Partage";
    static final String MESSAGE = "message";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_PartageListe + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_Proprietaire + " TEXT NOT NULL,"
            + ID_ListeTache + " INTEGER NOT NULL,"
            + ID_Invite + " TEXT NOT NULL,"
            + DateHeure_Partage + " INTEGER ,"
            + MESSAGE + " TEXT "
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheGroupeManager.tableName + " (" + ListeTacheGroupeManager.ID_ListeTache + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Invite + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_Proprietaire + ") REFERENCES " + ProprietaireListeManager.tableName + " (" + ProprietaireListeManager.PSEUDO + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";

    public PartageListeManager(Context context) {
        super(context);
    }

    public long insert(PartageListe e) {
        return 0;
    }

    public int update(PartageListe e) {
        return 0;
    }

    public int delete(PartageListe e) {
        return 0;
    }

    public PartageListe getFromId(int id) {
        return null;
    }


}
