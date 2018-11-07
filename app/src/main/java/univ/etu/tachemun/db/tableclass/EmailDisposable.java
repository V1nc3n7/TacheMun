package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class EmailDisposable {
    /*
 `id` int(10) UNSIGNED NOT NULL,
  `domaine` varchar(255) DEFAULT NULL


*/

    private int ID;
    private String domaine;
    private Date dateHeureAjout;

    public EmailDisposable(int ID, String domaine, long dateHeureAjout) {
        this.ID = ID;
        this.domaine = domaine;
        this.dateHeureAjout = new Date(dateHeureAjout);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Date getDateHeureAjout() {
        return dateHeureAjout;
    }

    public void setDateHeureAjout(Date dateHeureAjout) {
        this.dateHeureAjout = dateHeureAjout;
    }

    public void setDateHeureAjout(int dateHeureAjout) {
        this.dateHeureAjout = new Date(dateHeureAjout);
    }
}
