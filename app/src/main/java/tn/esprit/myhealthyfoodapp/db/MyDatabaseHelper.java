package tn.esprit.myhealthyfoodapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tn.esprit.myhealthyfoodapp.model.Category;
import tn.esprit.myhealthyfoodapp.model.Recipe;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    Context context;
    private static final String DATABASE_NAME = "HealthyRecipe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE1 = "CATEGORY";
    private static final String TABLE2 = "RECIPE";
    private static final String KEY_ROWID = "_id";
    private static MyDatabaseHelper myDatabaseHelper;

    public MyDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE CATEGORY (_id INTEGER PRIMARY KEY AUTOINCREMENT,category_name TEXT,category_description TEXT, image TEXT)";
        String sql2 = "CREATE TABLE RECIPE (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipe_title TEXT,recipe_image TEXT,num_servings INTEGER,ready_in_mins INTEGER,calories REAL, id_category INTEGER, favorite INTEGER )";

        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    public void tryDB1(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        System.out.println("Table 1 dropped succ !!!");
        String sql1 = "CREATE TABLE CATEGORY (_id INTEGER PRIMARY KEY AUTOINCREMENT,category_name TEXT,category_description TEXT, image TEXT)";
        db.execSQL(sql1);
    }
    public void tryDB2(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        System.out.println("Table 2 dropped succ !!!");
        String sql2 = "CREATE TABLE RECIPE (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipe_title TEXT,recipe_image TEXT,num_servings INTEGER,ready_in_mins INTEGER,calories REAL, id_category INTEGER, favorite INTEGER)";
        db.execSQL(sql2);
    }
    // Add Category
    public void addCategory(String category_name, String category_description, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("category_name", category_name);
        cv.put("category_description", category_description);
        cv.put("image", image);
        db.insert("CATEGORY", null, cv);
    }

    // Read all categories
    public Cursor readAllCategories() {
        String query = "SELECT * FROM CATEGORY";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Add Recipe
    public void addRecipe(String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float calories, int id_category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("recipe_title", recipe_title);
        cv.put("recipe_image", recipe_image);
        cv.put("num_servings", num_servings);
        cv.put("ready_in_mins", ready_in_mins);
        cv.put("calories", calories);
        cv.put("id_category", id_category);
        cv.put("favorite", 0);
        db.insert("RECIPE", null, cv);
    }

    // Read all Recipes
    public Cursor readAllRecipes() {
        String query = "SELECT * FROM RECIPE";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void getCategoriesListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE1 , null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String category_name = result.getString(1);
                    String category_description = result.getString(2);
                    String image = result.getString(3);
                    Category category = new Category(id, category_name, category_description, image);
                    Category.categoryList.add(category);
                }
            }
        }
    }

    public void getRecipesListArrayByCategoryId(int idCategory) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE2 + " WHERE id_category = " + idCategory, null)) {
            if (result.getCount() != 0) {
                Recipe.recipeList.clear();
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String recipe_title = result.getString(1);
                    String recipe_image = result.getString(2);
                    int num_servings = result.getInt(3);
                    int ready_in_mins = result.getInt(4);
                    float calories = result.getFloat(5);
                    int id_category = result.getInt(6);
                    int favorite = result.getInt(7);
                    Recipe recipe = new Recipe(id, recipe_title, recipe_image, num_servings, ready_in_mins, calories, id_category, favorite);
                    Recipe.recipeList.add(recipe);
                    System.out.println("Siize: "+Recipe.recipeList.size());
                }
            }
        }
    }

    // Delete all categories rows
    public void deleteAllCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE1);
    }

    // Delete all categories rows
    public void deleteAllRecipes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE2);
    }

    //-----------------Display
    public static MyDatabaseHelper instanceOfDatabase(Context context) {
        if (myDatabaseHelper == null)
            myDatabaseHelper = new MyDatabaseHelper(context);
        return myDatabaseHelper;
    }



}
