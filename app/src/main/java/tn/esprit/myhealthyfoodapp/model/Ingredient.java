package tn.esprit.myhealthyfoodapp.model;

public class Ingredient {
    private final String name;
    private final String thumbnail;
    private boolean selected;

    public Ingredient(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected() {
        selected = !selected;
    }
}
