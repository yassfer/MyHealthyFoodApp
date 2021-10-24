package tn.esprit.myhealthyfoodapp.model;

import java.io.Serializable;
import java.util.List;


public class Category implements Serializable {
    private int idCategory;
    private String categoryName;
    private String categoryDescription;
    private String image;
    private List<Recipe> recipes;

    public Category(int idCategory, String categoryName, String categoryDescription, String image) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.image = image;
    }

    public int getidCategory() {
        return idCategory;
    }

    public void setidCategory(int dCategory) {
        this.idCategory = dCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
