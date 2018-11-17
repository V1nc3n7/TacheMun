package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class ActionUser {
    private int ID;

    private String utilisateur;
    private Date dateAction;
    private String action;

    public ActionUser(String utilisateur, String action) {
        this.ID = -1;
        this.action = action;
        this.utilisateur = utilisateur;
        this.dateAction = new Date(System.currentTimeMillis());
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "{ " + ID + " }" +
                " " + action +
                ", " + utilisateur +
                ", " + dateAction;
    }
}
