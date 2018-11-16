package univ.etu.tachemun.db.tableclass;

public class ListeTacheGroupe {
    /*
    `ID_ListeTacheGroupe` int(11) NOT NULL,
  `ID_Groupe` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_CreateurMembre` varchar(32) COLLATE utf8_unicode_ci NOT NULL
     */
    private int ID;
    private int idGroupe;
    private int idListeTache;

    public ListeTacheGroupe(int ID, int idGroupe, int idListeTache) {
        this.ID = ID;
        this.idGroupe = idGroupe;
        this.idListeTache = idListeTache;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdListeTache() {
        return idListeTache;
    }

    public void setIdListeTache(int idListeTache) {
        this.idListeTache = idListeTache;
    }



    @Override
    public String toString() {
        return "{" + ID +
                "} , " + idGroupe +
                ", " + idListeTache;
    }
}
