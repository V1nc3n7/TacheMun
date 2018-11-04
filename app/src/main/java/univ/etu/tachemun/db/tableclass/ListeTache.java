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
    private String description;
    private Date dateHeureCreation;
    private boolean hasEcheance;
    private Date dateHeureEcheance;

    public ListeTache(int ID, String nom, int isPerso, String description, long dateHeureCreation, int hasEcheance, long dateHeureEcheance) {
        this.ID = ID;
        this.nom = nom;
        this.isPerso = isPerso == 1;
        this.description = description;
        this.dateHeureCreation = new Date(dateHeureCreation);
        this.hasEcheance = hasEcheance == 1;

        if (this.hasEcheance) {
            this.dateHeureEcheance = new Date(dateHeureEcheance);
        } else {
            this.dateHeureEcheance = null;
        }
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

    public boolean isHasEcheance() {
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
}
