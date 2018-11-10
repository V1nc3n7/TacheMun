package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class AssigneTache {
    /*
      `ID_AssigneTache` int(11) NOT NULL,
  `ID_MembreAssigneur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Tache` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_tacheur` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'qui realisera la tache',
  `dateHeure_AssigneTache` timestamp NULL DEFAULT NULL
     */


    private int ID;
    private int idTache;
    private String assigneur;
    private String esclave;
    private String message;
    private Date dateHeureAssignation;


    public AssigneTache(int ID, int idTache, String assigneur, String esclave, String message, long dateHeureAssignation) {
        this.ID = ID;
        this.idTache = idTache;
        this.assigneur = assigneur;
        this.esclave = esclave;
        this.message = message;
        this.dateHeureAssignation = new Date(dateHeureAssignation / 1000);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public String getAssigneur() {
        return assigneur;
    }

    public void setAssigneur(String assigneur) {
        this.assigneur = assigneur;
    }

    public String getEsclave() {
        return esclave;
    }

    public void setEsclave(String esclave) {
        this.esclave = esclave;
    }

    /**
     * @return null si pas de message
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasMessage() {
        return this.getMessage() == null;
    }

    public Date getDateHeureAssignation() {
        return dateHeureAssignation;
    }

    public void setDateHeureAssignation(Date dateHeureAssignation) {
        this.dateHeureAssignation = dateHeureAssignation;
    }

    public void setDateHeureAssignation(int dateHeureAssignation) {
        this.dateHeureAssignation = new Date(dateHeureAssignation);
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} " + idTache +
                ", '" + assigneur +
                ", '" + esclave +
                ", '" + message +
                ", " + dateHeureAssignation;
    }
}
