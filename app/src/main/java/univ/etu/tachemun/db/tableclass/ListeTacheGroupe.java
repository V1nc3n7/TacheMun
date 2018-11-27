package univ.etu.tachemun.db.tableclass;

public class ListeTacheGroupe {

    private int ID;
    private int idGroupe;
    private int idListeTache;

    ListeTacheGroupe(int ID, int idGroupe, int idListeTache) {
        this.ID = ID;
        this.idGroupe = idGroupe;
        this.idListeTache = idListeTache;
    }

    public ListeTacheGroupe(int idGroupe, int idListeTache) {
        this.ID = -1;
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
