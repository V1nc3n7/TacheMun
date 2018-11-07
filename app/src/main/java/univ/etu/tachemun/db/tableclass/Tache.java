package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Tache {
    /*
    `ID_Tache` int(11) NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_createurTache` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `libelle_Tache` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `description_Tache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateHeureCreation_Tache` timestamp NULL DEFAULT NULL,
  `numero_Tache` int(11) NOT NULL,
  `prioriteTache` int(11) NOT NULL,
  `echeance_Tache` datetime DEFAULT NULL
    */
    private int ID;
    private int idLsteTache;
    private String createur;
    private String libelle;
    private String description;
    private Date dateHeureCreation;
    private int numero;
    private int priorite;
    private Date dateHeureEcheance;
    private boolean echeance;

    /**
     * @param ID
     * @param idLsteTache
     * @param createur
     * @param description
     * @param dateHeureCreation
     * @param numero
     * @param priorite
     * @param dateHeureEcheance si = -1 alors pas d'echeance
     */
    public Tache(int ID, int idLsteTache, String createur, String libelle, String description, long dateHeureCreation, int numero, int priorite, long dateHeureEcheance) {
        this.ID = ID;
        this.idLsteTache = idLsteTache;
        this.createur = createur;
        this.libelle = libelle;
        this.description = description;
        this.dateHeureCreation = new Date(dateHeureCreation);
        this.numero = numero;
        this.priorite = priorite;
        if (dateHeureEcheance == -1) {
            this.dateHeureEcheance = null;
            this.echeance = false;
        } else {
            this.dateHeureEcheance = new Date(dateHeureEcheance);
            this.echeance = true;
        }

    }

    public Tache() {

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdLsteTache() {
        return idLsteTache;
    }

    public void setIdLsteTache(int idLsteTache) {
        this.idLsteTache = idLsteTache;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public void setDateHeureCreation(int dateHeureCreation) {

        this.dateHeureCreation = new Date(dateHeureCreation);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Date getDateHeureEcheance() {
        return dateHeureEcheance;
    }

    public void setDateHeureEcheance(Date dateHeureEcheance) {
        this.dateHeureEcheance = dateHeureEcheance;
    }

    public void setDateHeureEcheance(int dateHeureEcheance) {
        this.dateHeureEcheance = new Date(dateHeureEcheance);
    }

    public boolean hasEcheance() {
        return echeance;
    }

    public void setEcheance(boolean echeance) {
        this.echeance = echeance;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} " + idLsteTache +
                ", " + createur +
                ", " + libelle +
                ", " + description +
                ", " + dateHeureCreation +
                ", " + numero +
                ", " + priorite +
                ", " + dateHeureEcheance +
                ", " + echeance;
    }
}
