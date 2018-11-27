package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Groupe {

    private int ID;
    private String nom;
    private String createur;
    private boolean isPrivate;
    private String description;
    private Date dateHeureCreation;

    public Groupe(int ID, String nom, String createur, boolean isPrivate, String description, long dateHeureCreation) {
        this.ID = ID;
        this.nom = nom;
        this.createur = createur;
        this.isPrivate = isPrivate;
        this.description = description;
        this.dateHeureCreation = new Date(dateHeureCreation);
    }

    public Groupe(String nom, String createur, boolean isPrivate, String description) {
        this.ID = -1;
        this.nom = nom;
        this.createur = createur;
        this.isPrivate = isPrivate;
        this.description = description;
        this.dateHeureCreation = new Date(System.currentTimeMillis());
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateHeureCreation() {
        return dateHeureCreation;
    }

    public void setDateHeureCreation(long dateHeureCreation) {
        this.dateHeureCreation = new Date(dateHeureCreation);
    }

    @Override
    public String toString() {
        return "{" + ID +
                "}, " + nom +
                ", " + createur +
                ", " + isPrivate +
                ", " + description +
                ", " + dateHeureCreation;
    }
}
