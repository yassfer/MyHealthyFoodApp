package tn.esprit.myhealthyfoodapp.model;

import java.io.Serializable;
import java.util.List;

public class Recipes implements Serializable {
    private final List<Recipe> recepies;

    public Recipes(List<Recipe> recepies) {
        this.recepies = recepies;
    }

    public List<Recipe> getRecepies() {
        return recepies;
    }
}
