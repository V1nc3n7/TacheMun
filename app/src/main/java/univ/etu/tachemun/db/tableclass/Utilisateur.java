package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class Utilisateur {
/*
    `pseudo_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateInscription_Utilisateur` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `motDePasse_Utilisateur` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `mail_Utilisateur` varchar(255) COLLATE utf8_unicode_ci NOT NULL
  */

    private String pseudo;
    private Date dateInscription;
    private String motDePasse;
    private String mail;

    public Utilisateur(String pseudo, long dateInscription, String motDePasse, String mail) {
        this.pseudo = pseudo;
        this.dateInscription = new Date(dateInscription);
        this.motDePasse = motDePasse;
        this.mail = mail;
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


}
