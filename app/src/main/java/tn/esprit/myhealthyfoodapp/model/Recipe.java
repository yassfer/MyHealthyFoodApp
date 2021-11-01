package tn.esprit.myhealthyfoodapp.model;

import java.util.ArrayList;

public class Recipe {
    public static ArrayList<Recipe> recipeList = new ArrayList<>();
    public static ArrayList<Recipe> recipeListT = new ArrayList<>();

    private int id;
    private String recipe_title;
    private String recipe_image;
    private int num_servings;
    private int ready_in_mins;
    private float calories;
    private int id_category;
    private int favorite;
    private String ingredients;
    private String instructions;

    public Recipe(int id, String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float calories) {
        this.id = id;
        this.recipe_title = recipe_title;
        this.recipe_image = recipe_image;
        this.num_servings = num_servings;
        this.ready_in_mins = ready_in_mins;
        this.calories = calories;
    }

    public Recipe(int id, String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float calories, int id_category, int favorite) {
        this.id = id;
        this.recipe_title = recipe_title;
        this.recipe_image = recipe_image;
        this.num_servings = num_servings;
        this.ready_in_mins = ready_in_mins;
        this.calories = calories;
        this.id_category = id_category;
        this.favorite = favorite;
    }

    public Recipe(int id, String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float calories, int id_category, int favorite, String ingredients) {
        this.id = id;
        this.recipe_title = recipe_title;
        this.recipe_image = recipe_image;
        this.num_servings = num_servings;
        this.ready_in_mins = ready_in_mins;
        this.calories = calories;
        this.ingredients = ingredients;
        this.id_category = id_category;
        this.favorite = favorite;
    }

    public Recipe(int id, String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float calories, int id_category, int favorite, String ingredients, String instructions) {
        this.id = id;
        this.recipe_title = recipe_title;
        this.recipe_image = recipe_image;
        this.num_servings = num_servings;
        this.ready_in_mins = ready_in_mins;
        this.calories = calories;
        this.id_category = id_category;
        this.favorite = favorite;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title) {
        this.recipe_title = recipe_title;
    }

    public String getRecipe_image() {
        return recipe_image;
    }

    public void setRecipe_image(String recipe_image) {
        this.recipe_image = recipe_image;
    }

    public int getNum_servings() {
        return num_servings;
    }

    public void setNum_servings(int num_servings) {
        this.num_servings = num_servings;
    }

    public int getReady_in_mins() {
        return ready_in_mins;
    }

    public void setReady_in_mins(int ready_in_mins) {
        this.ready_in_mins = ready_in_mins;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public static ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public static void setRecipeList(ArrayList<Recipe> recipeList) {
        Recipe.recipeList = recipeList;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
