package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Membre {
    /*
     `ID_Membre` int(11) NOT NULL,
  `pseudoUtilisateur_Membre` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateAdhesion_Membre` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ID_Groupe` varchar(16) COLLATE utf8_unicode_ci NOT NULL
     */
    private int ID;
    private String pseudoUtilisteur;
    private Date dateHeureAdhesion;
    private int idGroupe;

    public Membre(int ID, String pseudoUtilisteur, long dateHeureAdhesion, int idGroupe) {
        this.ID = ID;
        this.pseudoUtilisteur = pseudoUtilisteur;
        this.dateHeureAdhesion = new Date(dateHeureAdhesion);
        this.idGroupe = idGroupe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPseudoUtilisteur() {
        return pseudoUtilisteur;
    }

    public void setPseudoUtilisteur(String pseudoUtilisteur) {
        this.pseudoUtilisteur = pseudoUtilisteur;
    }

    public Date getDateHeureAdhesion() {
        return dateHeureAdhesion;
    }

    public void setDateHeureAdhesion(Date dateHeureAdhesion) {
        this.dateHeureAdhesion = dateHeureAdhesion;
    }

    public void setDateHeureAdhesion(int dateHeureAdhesion) {
        this.dateHeureAdhesion = new Date(dateHeureAdhesion);
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} " + pseudoUtilisteur +
                ", " + dateHeureAdhesion +
                ", " + idGroupe;
    }
}
