package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Membre {

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

    public Membre(String pseudoUtilisteur, int idGroupe) {
        this.ID = -1;
        this.pseudoUtilisteur = pseudoUtilisteur;
        this.dateHeureAdhesion = new Date(System.currentTimeMillis());
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

    public void setDateHeureAdhesion(long dateHeureAdhesion) {
        this.dateHeureAdhesion = new Date(dateHeureAdhesion);
    }

    public void setDateHeureAdhesion(Date dateHeureAdhesion) {
        this.dateHeureAdhesion = dateHeureAdhesion;
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
