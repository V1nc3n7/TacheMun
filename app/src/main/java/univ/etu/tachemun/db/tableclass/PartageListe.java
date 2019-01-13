package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class PartageListe {
    private int ID;
    private String proprietaireListe;
    private int idListe;
    private String utilisateurInvite;
    private Date dateHeurePartage;
    private String message;

    PartageListe(int ID, String proprietaireListe, int idListe, String utilisateurInvite, long dateHeurePartage, String message) {
        this.ID = ID;
        this.proprietaireListe = proprietaireListe;
        this.idListe = idListe;
        this.utilisateurInvite = utilisateurInvite;
        this.dateHeurePartage = new Date(dateHeurePartage);
        this.message = message;
    }

    public PartageListe(String proprietaireListe, int idListe, String utilisateurInvite, String message) {
        this.ID = -1;
        this.proprietaireListe = proprietaireListe;
        this.idListe = idListe;
        this.utilisateurInvite = utilisateurInvite;
        this.dateHeurePartage = new Date(System.currentTimeMillis());
        this.message = message;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProprietaireListe() {
        return proprietaireListe;
    }

    public void setProprietaireListe(String proprietaireListe) {
        this.proprietaireListe = proprietaireListe;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    public String getUtilisateurInvite() {
        return utilisateurInvite;
    }

    public void setUtilisateurInvite(String utilisateurInvite) {
        this.utilisateurInvite = utilisateurInvite;
    }

    public Date getDateHeurePartage() {
        return dateHeurePartage;
    }

    public void setDateHeurePartage(Date dateHeurePartage) {
        this.dateHeurePartage = dateHeurePartage;
    }

    public void setDateHeurePartage(long dateHeurePartage) {
        this.dateHeurePartage = new Date(dateHeurePartage);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} ," + proprietaireListe +
                ", idListe=" + idListe +
                ", " + utilisateurInvite +
                ", " + dateHeurePartage +
                "," + message;
    }
}
