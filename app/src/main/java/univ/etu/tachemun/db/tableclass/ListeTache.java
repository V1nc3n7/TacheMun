package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class ListeTache {
    /*
    `ID_ListeTache` int(11) NOT NULL,
  `nom_ListeTache` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `boolPerso_ListeTache` tinyint(1) NOT NULL COMMENT '1= liste Perso ,0=liste groupe',
  `description_ListeTache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateHeureCreation_ListeTache` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `echeanceTotale_ListeTache` datetime DEFAULT NULL
    */
    private int ID;
    private String nom;
    private boolean isPerso;
    private String proprietaire;
    private String description;
    private Date dateHeureCreation;
    private boolean hasEcheance;
    private Date dateHeureEcheance;
    private int couleur;

    public ListeTache(int ID, String nom, boolean isPerso, String proprio, String description, long dateHeureCreation, boolean hasEcheance, long dateHeureEcheance, int couleur) {
        this.ID = ID;
        this.nom = nom;
        this.isPerso = isPerso;
        this.proprietaire = proprio;
        this.description = description;
        this.dateHeureCreation = new Date(dateHeureCreation);
        this.hasEcheance = hasEcheance;

        if (this.hasEcheance) {
            this.dateHeureEcheance = new Date(dateHeureEcheance);
        } else {
            this.dateHeureEcheance = null;
        }
        this.couleur = couleur;
    }

    public ListeTache(String nom, boolean isPerso, String proprio, String description, boolean hasEcheance, long dateHeureEcheance, int couleur) {
        this.ID = -1;
        this.nom = nom;
        this.isPerso = isPerso;
        this.proprietaire = proprio;
        this.description = description;
        this.dateHeureCreation = new Date(System.currentTimeMillis());
        this.hasEcheance = hasEcheance;

        if (this.hasEcheance) {
            this.dateHeureEcheance = new Date(dateHeureEcheance);
        } else {
            this.dateHeureEcheance = null;
        }
        this.couleur = couleur;
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

    public boolean isPerso() {
        return isPerso;
    }

    public void setPerso(boolean perso) {
        isPerso = perso;
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

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public boolean isHasEcheance() {
        return hasEcheance;
    }

    public boolean HasEcheance() {
        return hasEcheance;
    }

    public void setHasEcheance(boolean hasEcheance) {
        this.hasEcheance = hasEcheance;
    }

    /**
     * @return null si pas hasEcheance
     */
    public Date getDateHeureEcheance() {
        return dateHeureEcheance;
    }

    public void setDateHeureEcheance(Date dateHeureEcheance) {
        this.dateHeureEcheance = dateHeureEcheance;
    }

    public void setDateHeureEcheance(long dateHeureEcheance) {
        this.dateHeureEcheance = new Date(dateHeureEcheance);
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} " + nom +
                ", " + isPerso +
                ", " + proprietaire +
                ", " + description +
                ", " + dateHeureCreation +
                ", " + hasEcheance +
                ", " + dateHeureEcheance +
                ", " + couleur;
    }
}
