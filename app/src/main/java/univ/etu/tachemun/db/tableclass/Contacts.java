package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Contacts {

    private int ID;

    private String pseudoProprietaire;
    private String peudoContact;

    private Date dateHeureContact;

    Contacts(int ID, String pseudoProprietaire, String peudoContact, long dateHeureContact) {
        this.ID = ID;
        this.pseudoProprietaire = pseudoProprietaire;
        this.peudoContact = peudoContact;
        this.dateHeureContact = new Date(dateHeureContact);
    }

    public Contacts(String pseudoProprietaire, String peudoContact) {
        this.ID = -1;
        this.pseudoProprietaire = pseudoProprietaire;
        this.peudoContact = peudoContact;
        this.dateHeureContact = new Date(System.currentTimeMillis());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPseudoProprietaire() {
        return pseudoProprietaire;
    }

    public void setPseudoProprietaire(String pseudoProprietaire) {
        this.pseudoProprietaire = pseudoProprietaire;
    }

    public String getPeudoContact() {
        return peudoContact;
    }

    public void setPeudoContact(String peudoContact) {
        this.peudoContact = peudoContact;
    }

    public Date getDateHeureContact() {
        return dateHeureContact;
    }

    public void setDateHeureContact(Date dateHeureContact) {
        this.dateHeureContact = dateHeureContact;
    }

    public void setDateHeureContact(long dateHeureContact) {
        this.dateHeureContact = new Date(dateHeureContact);

    }

    @Override
    public String toString() {
        return "{" +
                ID +
                "} " + pseudoProprietaire +
                ", " + peudoContact +
                ", " + dateHeureContact;
    }
}
