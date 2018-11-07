package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;

import univ.etu.tachemun.db.tableclass.Membre;

public class MembreManager extends TableManager {
    /*

    CREATE TABLE Membre (
      ID_Membre int(11) NOT NULL,
      pseudoUtilisateur_Membre varchar(32) COLLATE utf8_unicode_ci NOT NULL,
      dateAdhesion_Membre timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      ID_Groupe varchar(16) COLLATE utf8_unicode_ci NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


    ALTER TABLE Membre
      ADD PRIMARY KEY (ID_Membre),
      ADD KEY fk_Menmbre_Groupe_idx (ID_Groupe),
      ADD KEY fk_Membre_Utilisateur_idx (pseudoUtilisateur_Membre);


    ALTER TABLE Membre
      MODIFY ID_Membre int(11) NOT NULL AUTO_INCREMENT;


    ALTER TABLE Membre
      ADD CONSTRAINT fk_Membre_Groupe FOREIGN KEY (ID_Groupe) REFERENCES `Groupe` (ID_Groupe) ON DELETE CASCADE ON UPDATE CASCADE,
      ADD CONSTRAINT fk_Membre_Utilisateur FOREIGN KEY (pseudoUtilisateur_Membre) REFERENCES Utilisateur (pseudo_Utilisateur) ON DELETE CASCADE ON UPDATE CASCADE;
     */
    static final String tableName = "Membre";
    static final String ID_MEMBRE = "ID_Membre";
    static final String PSEUDO = "pseudo";
    static final String DateHeure_Adhesion = "dateAdhesion";
    static final String ID_GROUPE = "ID_GROUPE";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_MEMBRE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PSEUDO + " TEXT NOT NULL,"

            + DateHeure_Adhesion + " INTEGER ,"
            + ID_GROUPE + " TEXT "
            + ",FOREIGN KEY (" + ID_GROUPE + ") REFERENCES " + GroupeManager.tableName + " (" + GroupeManager.ID_GROUPE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + PSEUDO + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";


    public MembreManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(Membre m) {
        ContentValues c = new ContentValues();
        c.put(ID_MEMBRE, m.getID());
        c.put(PSEUDO, m.getPseudoUtilisteur());
        c.put(DateHeure_Adhesion, m.getDateHeureAdhesion().getTime());
        c.put(ID_GROUPE, m.getIdGroupe());

        return c;
    }


    public long insert(Membre m) {
        ContentValues v = putInContent(m);

        return db.insert(tableName, null, v);


    }


    public int update(Membre m) {

        ContentValues values = putInContent(m);
        String where = ID_MEMBRE + " = ?";
        String[] whereArgs = {m.getID() + ""};


        return db.update(tableName, values, where, whereArgs);


    }

    public int delete(Membre m) {


        String where = ID_MEMBRE + " = ?";
        String[] whereArgs = {m.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public Membre getFromId(int id) {
        return null;
    }


}
