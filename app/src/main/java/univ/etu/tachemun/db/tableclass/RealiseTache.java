package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class RealiseTache {
    private int ID;
    private String realiseurTache;
    private int idTache;
    private Date dateHeurRealisation;
    private String details;

    RealiseTache(int ID, String realiseurTache, int idTache, long dateHeurRealisation, String details) {
        this.ID = ID;
        this.realiseurTache = realiseurTache;
        this.idTache = idTache;
        this.dateHeurRealisation = new Date(dateHeurRealisation);
        this.details = details;
    }

    public RealiseTache(String realiseurTache, int idTache, String details) {
        this.ID = -1;
        this.realiseurTache = realiseurTache;
        this.idTache = idTache;
        this.dateHeurRealisation = new Date(System.currentTimeMillis());
        this.details = details;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRealiseurTache() {
        return realiseurTache;
    }

    public void setRealiseurTache(String realiseurTache) {
        this.realiseurTache = realiseurTache;
    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public Date getDateHeurRealisation() {
        return dateHeurRealisation;
    }

    public void setDateHeurRealisation(long dateHeurRealisation) {
        this.dateHeurRealisation = new Date(dateHeurRealisation);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "{" + ID +
                "} " + realiseurTache +
                ", " + idTache +
                ", " + dateHeurRealisation +
                ", " + details;
    }
}
