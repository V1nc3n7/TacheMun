package univ.etu.tachemun.FluxListeListe;

public class Flux {
    private int color;
    private String pseudo;
    private String text;
    private String date;

    public Flux(int color, String pseudo, String text, String date) {
        this.color = color;
        this.pseudo = pseudo;
        this.text = text;
        this.date = date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
