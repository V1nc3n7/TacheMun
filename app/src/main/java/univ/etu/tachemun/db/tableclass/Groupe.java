package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Groupe {
    /*
     `ID_Groupe` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `ID_createurGroupe` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `nom_Groupe` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `dateCreation_Groupe` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description_Groupe` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prive_Groupe` tinyint(4) DEFAULT '0'
  */


    private int ID;
    private String nom;
    private String createur;
    private boolean isPrivate;
    private String description;
    private Date dateHeureCreation;

    public Groupe(int ID, String nom, String createur, int isPrivate, String description, long dateHeureCreation) {
        this.ID = ID;
        this.nom = nom;
        this.createur = createur;
        this.isPrivate = isPrivate == 1;
        this.description = description;
        this.dateHeureCreation = new Date(dateHeureCreation);
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

    public void setDateHeureCreation(Date dateHeureCreation) {
        this.dateHeureCreation = dateHeureCreation;
    }
}
