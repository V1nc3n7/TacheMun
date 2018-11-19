package univ.etu.tachemun.FluxListeTache;

public class FluxTaches {

    private String titre;
    private boolean isCheck;
    private int prio;
    private String desc;

    public FluxTaches(String titre, boolean i) {
        this.titre = titre;
        this.isCheck = i;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
