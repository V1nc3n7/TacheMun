package univ.etu.tachemun.FluxListeTache;

public class FluxTaches {

    private String Text;
    //private Boolean isCheck;


/*
    public FluxTaches(String t, Boolean i) {
        Text = t;
        isCheck = i;
    }
*/
    public FluxTaches(String t) {
        Text = t;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
/*
    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
*/
}
