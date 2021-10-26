package tn.esprit.myhealthyfoodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.controller.HomeActivity;
import tn.esprit.myhealthyfoodapp.controller.RecepiesActivity;
import tn.esprit.myhealthyfoodapp.model.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    /* start of inner ViewHolder class */
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        // declare views
        public ImageView recipeImage;
        public TextView recipeTitle;
        public CardView cardView;

        // Constructor to this inner class
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            System.out.println("title of recipe"+ R.id.title_of_recipe  );
            recipeImage = itemView.findViewById(R.id.image_of_recipe);
            recipeTitle = itemView.findViewById(R.id.title_of_recipe);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
    /* out of inner class : ViewHolder */

    private final String TAG = "RecipesAdapter";
    private Context mContext;
    private List<Category> mData;

    public CategoriesAdapter(Context mContext, List<Category> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_categories_adapter, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category currentCategory = mData.get(position);
        System.out.println(currentCategory);
        // Bind Recipe Data to the Card Views
        if (currentCategory.getImage().isEmpty()) {
            // No image
            holder.recipeImage.setImageResource(R.drawable.nopicture);
        } else {

            System.out.println("entered else"+currentCategory.getImage());
            /*Bitmap bitmap = BitmapFactory.decodeFile(currentRecipe.getRecipeImage());
            holder.recipeImage.setImageBitmap(bitmap);*/
            Picasso.get().load(currentCategory.getImage())
                    .into(holder.recipeImage);
        }
        holder.recipeTitle.setText(currentCategory.getCategory_name());
        // Ensure CardView is clickable
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), RecepiesActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public CategoriesAdapter() {
    }
}
