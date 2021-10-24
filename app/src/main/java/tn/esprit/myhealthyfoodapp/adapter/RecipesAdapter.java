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
import tn.esprit.myhealthyfoodapp.controller.RecepiesActivity;
import tn.esprit.myhealthyfoodapp.controller.RecipeDetails;
import tn.esprit.myhealthyfoodapp.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    /* start of inner ViewHolder class */
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        // declare views
        ImageView recipeView;
        TextView recipeNameText;
        TextView readyInText;
        TextView numServingText;
        public CardView cardView;
        // Constructor to this inner class
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            System.out.println("title of recipe" + R.id.title_of_recipe);
            recipeView = itemView.findViewById(R.id.imageView_recipe);
            recipeNameText = itemView.findViewById(R.id.textView_recipeName);
            cardView = (CardView) itemView.findViewById(R.id.cardRecipeView);
            readyInText = (TextView) itemView.findViewById(R.id.recipe_ready_in_min);
            numServingText = (TextView) itemView.findViewById(R.id.num_of_serving);
        }
    }
    /* out of inner class : ViewHolder */

    private final String TAG = "RecipesAdapter";
    private Context mContext;
    private List<Recipe> mData;

    public RecipesAdapter(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipes_adapter, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = mData.get(position);
        System.out.println(currentRecipe);
        // Bind Recipe Data to the Card Views
        if (currentRecipe.getRecipeImage().isEmpty()) {
            // No image
            holder.recipeView.setImageResource(R.drawable.nopicture);
        } else {

            System.out.println("entered else" + currentRecipe.getRecipeImage());
            /*Bitmap bitmap = BitmapFactory.decodeFile(currentRecipe.getRecipeImage());
            holder.recipeImage.setImageBitmap(bitmap);*/
            Picasso.get().load(currentRecipe.getRecipeImage())
                    .into(holder.recipeView);
        }
        holder.recipeNameText.setText(currentRecipe.getRecipeTitle());
        holder.numServingText.setText(String.valueOf(currentRecipe.getNumServings()));
        holder.readyInText.setText(String.valueOf(currentRecipe.getReadyInMins()));
        // Ensure CardView is clickable
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), RecipeDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public RecipesAdapter() {
    }
}