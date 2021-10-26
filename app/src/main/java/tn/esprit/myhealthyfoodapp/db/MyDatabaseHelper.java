package tn.esprit.myhealthyfoodapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import tn.esprit.myhealthyfoodapp.model.Category;

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
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE CATEGORY (_id INTEGER PRIMARY KEY AUTOINCREMENT,category_name TEXT,category_description TEXT, image TEXT)";
        String sql2 = "CREATE TABLE RECIPE (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipe_title TEXT,recipe_image TEXT,num_servings INTEGER,ready_in_mins INTEGER,health_score REAL, food_fit_score REAL)";

        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        onCreate(db);
    }

    // Add Category
    public void addCategory(String category_name, String category_description, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("category_name", category_name);
        cv.put("category_description", category_description);
        cv.put("image", image);
        db.insert("Category", null, cv);
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
    public void addRecipe(String recipe_title, String recipe_image, int num_servings, int ready_in_mins, float health_score, float food_fit_score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("recipe_title", recipe_title);
        cv.put("recipe_image", recipe_image);
        cv.put("num_servings", num_servings);
        cv.put("ready_in_mins", ready_in_mins);
        cv.put("health_score", health_score);
        cv.put("food_fit_score", food_fit_score);
        db.insert("Recipe", null, cv);
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

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE1, null)) {
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

    //-----------------Display
    public static MyDatabaseHelper instanceOfDatabase(Context context) {
        if (myDatabaseHelper == null)
            myDatabaseHelper = new MyDatabaseHelper(context);
        return myDatabaseHelper;
    }



}
