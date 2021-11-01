package tn.esprit.myhealthyfoodapp.model;

import java.util.ArrayList;

public class Saison {
    public static ArrayList<Saison> saisonListT = new ArrayList<>();

    private int id;
    private String saison_name;
    private String saison_image;

    public Saison(int id, String saison_name, String saison_image) {
        this.id = id;
        this.saison_name = saison_name;
        this.saison_image = saison_image;
    }

    public static ArrayList<Saison> getSaisonListT() {
        return saisonListT;
    }

    public static void setSaisonListT(ArrayList<Saison> saisonListT) {
        Saison.saisonListT = saisonListT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaison_name() {
        return saison_name;
    }

    public void setSaison_name(String saison_name) {
        this.saison_name = saison_name;
    }

    public String getSaison_image() {
        return saison_image;
    }

    public void setSaison_image(String saison_image) {
        this.saison_image = saison_image;
    }
}
