package univ.etu.tachemun.FluxListeListe;

public class Flux {
    private int color;
    private String pseudo;
    private String text;
    private String ttext;

    public Flux(int color, String pseudo, String text,String t) {
        this.color = color;
        this.pseudo = pseudo;
        this.text = text;
        this.ttext = t;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getttext() {
        return ttext;
    }

    public void setttext(String t) {
        this.ttext = t;
    }
}
