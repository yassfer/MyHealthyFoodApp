package tn.esprit.myhealthyfoodapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {

    public static ArrayList<Category> categoryList = new ArrayList<>();

    private int id;
    private String category_name;
    private String category_description;
    private String image;
    private List<Recipe> recipes;

    public Category(int id, String category_name, String category_description, String image) {
        this.id = id;
        this.category_name = category_name;
        this.category_description = category_description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
