package univ.etu.tachemun.db.tableclass;

public class ProprietaireListe {
    /*
      `ID_ProprietaireListe` int(11) NOT NULL,
  `pseudo_Utilisateur_Proprietaire` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_ListeTache` int(11) NOT NULL
  */


    private int ID;
    private String proprietaire;
    private int idListe;

    public ProprietaireListe(int ID, String proprietaire, int idListe) {
        this.ID = ID;
        this.proprietaire = proprietaire;
        this.idListe = idListe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "}, " + proprietaire +
                ", " + idListe;
    }
}
