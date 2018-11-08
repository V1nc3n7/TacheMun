package univ.etu.tachemun.db.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import univ.etu.tachemun.db.tableclass.Tache;

public class TacheManager extends TableManager {

    /*
`ID_Tache` int(11) NOT NULL,
`ID_ListeTache` int(11) NOT NULL,
`ID_createurTache` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
`libelle_Tache` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
`description_Tache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`dateHeureCreation_Tache` timestamp NULL DEFAULT NULL,
`numero_Tache` int(11) NOT NULL,
`prioriteTache` int(11) NOT NULL,
`echeance_Tache` datetime DEFAULT NULL
*/
    /*

CREATE TABLE Tache (
  ID_Tache int(11) NOT NULL,
  ID_ListeTache int(11) NOT NULL,
  ID_createurTache varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  libelle_Tache varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  description_Tache varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  dateHeureCreation_Tache timestamp NULL DEFAULT NULL,
  numero_Tache int(11) NOT NULL,
  prioriteTache int(11) NOT NULL,
  echeance_Tache datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE Tache
  ADD PRIMARY KEY (ID_Tache),
  ADD KEY fk_Tache_ListeTache_idx (ID_ListeTache),
  ADD KEY fk_Tache_Utilisateur (ID_createurTache);


ALTER TABLE Tache
  ADD CONSTRAINT fk_Tache_ListeTache FOREIGN KEY (ID_ListeTache) REFERENCES ListeTache (ID_ListeTache),
  ADD CONSTRAINT fk_Tache_Utilisateur FOREIGN KEY (ID_createurTache) REFERENCES Utilisateur (pseudo_Utilisateur);
COMMIT;

    */
    static final String tableName = "Tache";
    static final String ID_Tache = "ID_Tache";
    static final String ID_ListeTache = "ID_ListeTache";
    static final String ID_Createur = "ID_Createur";
    static final String LIBELLE = "libelle";
    static final String DESCRIPTION = "description";
    static final String DateHeure_Creation = "DateHeure_Creation";
    static final String NUMERO = "numero";
    static final String PRIORITE = "priorite";
    static final String HAS_ECHEANCE = "has_echeance";
    static final String ECHEANCE = "echeance";


    public static final String createTableScript = "CREATE TABLE " + tableName +
            " (" +
            ID_Tache + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_ListeTache + " TEXT NOT NULL,"
            + ID_Createur + " TEXT NOT NULL,"
            + LIBELLE + " TEXT ,"
            + DESCRIPTION + " TEXT,"
            + DateHeure_Creation + " INTEGER ,"

            + NUMERO + " INTEGER ,"
            + PRIORITE + " INTEGER ,"

            + HAS_ECHEANCE + " INTEGER ,"
            + ECHEANCE + " INTEGER "
            + ",FOREIGN KEY (" + ID_Createur + ") REFERENCES " + UtilisateurManager.tableName + " (" + UtilisateurManager.ID_UTILISATEUR + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ",FOREIGN KEY (" + ID_ListeTache + ") REFERENCES " + ListeTacheManager.tableName + " (" + ListeTacheManager.ID_LISTETACHE + ")" +
            "  ON UPDATE CASCADE ON DELETE CASCADE"
            + ");";


    public TacheManager(Context context) {
        super(context);
    }

    private ContentValues putInContent(Tache tache) {

        ContentValues values = new ContentValues();


        values.put(ID_Tache, tache.getID());
        values.put(ID_ListeTache, tache.getIdLsteTache());
        values.put(ID_Createur, tache.getCreateur());
        values.put(LIBELLE, tache.getLibelle());
        values.put(DESCRIPTION, tache.getDescription());
        values.put(DateHeure_Creation, tache.getDateHeureCreation().getTime());
        values.put(NUMERO, tache.getNumero());
        values.put(PRIORITE, tache.getPriorite());
        values.put(HAS_ECHEANCE, (tache.hasEcheance() ? 1 : 0));
        values.put(ECHEANCE, tache.getDateHeureEcheance().getTime());

        return values;
    }

    public long insert(Tache t) {
        ContentValues v = putInContent(t);
        v.remove(ID_Tache);
        return db.insert(tableName, null, v);
    }

    public int update(Tache t) {
        ContentValues values = putInContent(t);
        String where = ID_Tache + " = ?";
        String[] whereArgs = {t.getID() + ""};
        return db.update(tableName, values, where, whereArgs);
    }

    public int delete(Tache t) {
        String where = ID_Tache + " = ?";
        String[] whereArgs = {t.getID() + ""};
        return db.delete(tableName, where, whereArgs);
    }

    public Tache getFromId(int id) {

        Tache t = new Tache();

        Cursor c = db.rawQuery(
                "SELECT " + ID_Tache + ", " + ID_ListeTache + ", " + ID_Createur
                        + " , " + LIBELLE + " , " + DESCRIPTION + " , "
                        + DateHeure_Creation + " , " + NUMERO + " , " + PRIORITE + " , "
                        + HAS_ECHEANCE + " , "
                        + ECHEANCE + " FROM " + tableName
                        + " WHERE " +
                        ID_Tache + "=" + id, null);
        if (c.moveToFirst()) {
            t.setID(c.getInt(c.getColumnIndex(ID_Tache)));
            t.setIdLsteTache(c.getInt(c.getColumnIndex(ID_ListeTache)));
            t.setCreateur(c.getString(c.getColumnIndex(ID_Createur)));
            t.setLibelle(c.getString(c.getColumnIndex(LIBELLE)));
            t.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));
            t.setDateHeureCreation(c.getInt(c.getColumnIndex(DateHeure_Creation)));
            t.setNumero(c.getInt(c.getColumnIndex(NUMERO)));
            t.setPriorite(c.getInt(c.getColumnIndex(PRIORITE)));
            if (c.getInt(c.getColumnIndex(HAS_ECHEANCE)) == 0)
                t.setDateHeureEcheance(null);
            else
                t.setDateHeureEcheance(c.getInt(c.getColumnIndex(ECHEANCE)));


            c.close();
            return t;
        } else {
            return null;
        }


    }
}
