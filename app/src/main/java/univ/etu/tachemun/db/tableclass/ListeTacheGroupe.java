package univ.etu.tachemun.db.tableclass;

public class ListeTacheGroupe {
    /*
    `ID_ListeTacheGroupe` int(11) NOT NULL,
  `ID_Groupe` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_CreateurMembre` varchar(32) COLLATE utf8_unicode_ci NOT NULL
     */
    private int ID;
    private String idGroupe;
    private int idListeTache;
    private String createurListe;

    public ListeTacheGroupe(int ID, String idGroupe, int idListeTache, String createurListe) {
        this.ID = ID;
        this.idGroupe = idGroupe;
        this.idListeTache = idListeTache;
        this.createurListe = createurListe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(String idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdListeTache() {
        return idListeTache;
    }

    public void setIdListeTache(int idListeTache) {
        this.idListeTache = idListeTache;
    }

    public String getCreateurListe() {
        return createurListe;
    }

    public void setCreateurListe(String createurListe) {
        this.createurListe = createurListe;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} , " + idGroupe +
                ", " + idListeTache +
                ", " + createurListe;
    }
}
