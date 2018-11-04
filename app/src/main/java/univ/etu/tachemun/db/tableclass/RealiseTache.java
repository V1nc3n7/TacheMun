package univ.etu.tachemun.db.tableclass;

import java.util.Date;

public class RealiseTache {
    /*
    `ID_RealiseTache` int(11) NOT NULL,
  `ID_realiseurTache` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Tache` int(11) NOT NULL,
  `dateHeureRealisation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `details_RealiseTache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
     */
    private int ID;
    private String realiseurTache;
    private int idTache;
    private Date dateHeurRealisation;
    private String details;

    public RealiseTache(int ID, String realiseurTache, int idTache, long dateHeurRealisation, String details) {
        this.ID = ID;
        this.realiseurTache = realiseurTache;
        this.idTache = idTache;
        this.dateHeurRealisation = new Date(dateHeurRealisation);
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

    public void setDateHeurRealisation(Date dateHeurRealisation) {
        this.dateHeurRealisation = dateHeurRealisation;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
