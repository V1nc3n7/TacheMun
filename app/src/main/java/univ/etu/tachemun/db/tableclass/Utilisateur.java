package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Utilisateur {

    private String pseudo;
    private String motDePasse;
    private String mail;
    private Date dateInscription;

    public Utilisateur(String pseudo, String motDePasse, String mail, long dateInscription) {
        this.pseudo = pseudo;
        this.dateInscription = new Date(dateInscription);
        this.motDePasse = motDePasse;
        this.mail = mail;
    }

    public Utilisateur(String pseudo, String motDePasse, String mail) {
        this.pseudo = pseudo;
        this.dateInscription = new Date(System.currentTimeMillis());
        this.motDePasse = motDePasse;
        this.mail = mail;
    }

    public Utilisateur() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(long dateInscription) {
        this.dateInscription = new Date(dateInscription);
    }
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "{" + pseudo +
                "}, " + dateInscription.toString() +
                ", " + motDePasse +
                ", " + mail;
    }
}
