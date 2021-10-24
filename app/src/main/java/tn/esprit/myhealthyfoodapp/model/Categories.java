package tn.esprit.myhealthyfoodapp.model;

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable {
    private final List<Category> CategoryList;

    public Categories(List<Category> categoryList) {
        CategoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return CategoryList;
    }
}
