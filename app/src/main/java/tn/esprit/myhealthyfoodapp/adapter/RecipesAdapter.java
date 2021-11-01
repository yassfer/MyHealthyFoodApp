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
import tn.esprit.myhealthyfoodapp.controller.ProfilActivity;
import tn.esprit.myhealthyfoodapp.controller.RecepiesActivity;
import tn.esprit.myhealthyfoodapp.controller.RecipeDetails;
import tn.esprit.myhealthyfoodapp.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeView;
        TextView recipeNameText;
        TextView readyInText;
        TextView numServingText;
        ImageView favImg;
        public CardView cardView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeView = itemView.findViewById(R.id.imageView_recipe);
            recipeNameText = itemView.findViewById(R.id.textView_recipeName);
            cardView = (CardView) itemView.findViewById(R.id.cardRecipeView);
            readyInText = (TextView) itemView.findViewById(R.id.recipe_ready_in_min);
            numServingText = (TextView) itemView.findViewById(R.id.num_of_serving);
            favImg = itemView.findViewById(R.id.fav_image);
        }
    }

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

        if (currentRecipe.getRecipe_image().isEmpty()) {
            holder.recipeView.setImageResource(R.drawable.nopicture);
        } else {
            Picasso.get().load(currentRecipe.getRecipe_image())
                    .into(holder.recipeView);
        }
        holder.recipeNameText.setText(currentRecipe.getRecipe_title());
        holder.numServingText.setText(String.valueOf(currentRecipe.getNum_servings()));
        holder.readyInText.setText(String.valueOf(currentRecipe.getReady_in_mins()));
        if(currentRecipe.getFavorite() == 1){
            holder.favImg.setImageResource(R.drawable.ic_star_full);
        }

        holder.favImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRecipe.setFavorite(1);
                Recipe.recipeList.set(currentRecipe.getId()-1, currentRecipe);
                Intent i = new Intent(view.getContext(), HomeActivity.class);
                view.getContext().startActivity(i);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RecipeDetails.class);
                i.putExtra("ID_RECIPE", currentRecipe.getId());
                v.getContext().startActivity(i);
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