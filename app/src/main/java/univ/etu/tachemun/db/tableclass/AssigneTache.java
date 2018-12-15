package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class AssigneTache {


    private int ID;
    private int idTache;
    private String assigneur;
    private String esclave;
    private String message;
    private Date dateHeureAssignation;


    AssigneTache(int ID, int idTache, String assigneur, String esclave, String message, long dateHeureAssignation) {
        this.ID = ID;
        this.idTache = idTache;
        this.assigneur = assigneur;
        this.esclave = esclave;
        this.message = message;
        this.dateHeureAssignation = new Date(dateHeureAssignation);
    }

    public AssigneTache(int idTache, String assigneur, String esclave, String message) {
        this.ID = -1;
        this.idTache = idTache;
        this.assigneur = assigneur;
        this.esclave = esclave;
        this.message = message;
        this.dateHeureAssignation = new Date(System.currentTimeMillis());
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

    public void setDateHeureAssignation(long dateHeureAssignation) {
        this.dateHeureAssignation = new Date(dateHeureAssignation);
    }

    public void setDateHeureAssignation(Date dateHeureAssignation) {
        this.dateHeureAssignation = dateHeureAssignation;
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
