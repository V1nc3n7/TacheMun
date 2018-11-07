package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Contacts {
    /*
     `ID_Contact` int(11) NOT NULL,
  `ID_Proprietaire` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `DateHeure_Contact` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */

    private int ID;

    private String pseudoProprietaire;
    private String peudoContact;

    private Date dateHeureContact;

    public Contacts(int ID, String pseudoProprietaire, String peudoContact, long dateHeureContact) {
        this.ID = ID;
        this.pseudoProprietaire = pseudoProprietaire;
        this.peudoContact = peudoContact;
        this.dateHeureContact = new Date(dateHeureContact);
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

    public void setDateHeureContact(int dateHeureContact) {
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
