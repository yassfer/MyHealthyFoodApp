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
import tn.esprit.myhealthyfoodapp.model.Category;
import tn.esprit.myhealthyfoodapp.model.Extrat;

public class ExtratAdapter extends RecyclerView.Adapter<ExtratAdapter.ExtratViewHolder> {

    public static class ExtratViewHolder extends RecyclerView.ViewHolder {
        public ImageView extratImage;
        public TextView extratTitle;
        public CardView cardView;

        public ExtratViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            System.out.println("title of recipe"+ R.id.title_of_recipe  );
            extratImage = itemView.findViewById(R.id.image_extrat);
            extratTitle = itemView.findViewById(R.id.title_extrat);
            cardView = (CardView) itemView.findViewById(R.id.cardView_extrat);
        }
    }
    /* out of inner class : ViewHolder */

    private final String TAG = "ExtratActivitiesAdapter";
    private Context mContext;
    private List<Extrat> mData;

    public ExtratAdapter(Context mContext, List<Extrat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ExtratViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_extrat_adapter, parent, false);
        return new ExtratViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtratViewHolder holder, int position) {
        Extrat currentExtrat = mData.get(position);
        System.out.println(currentExtrat);
        // Bind Recipe Data to the Card Views
        if (currentExtrat.getImgExtrat().isEmpty()) {
            // No image
            holder.extratImage.setImageResource(R.drawable.nopicture);
        } else {

            System.out.println("entered else"+currentExtrat.getImgExtrat());
            /*Bitmap bitmap = BitmapFactory.decodeFile(currentRecipe.getRecipeImage());
            holder.recipeImage.setImageBitmap(bitmap);*/
           Picasso.get().load(currentExtrat.getImgExtrat())
                    .into(holder.extratImage);
        }
        holder.extratTitle.setText(currentExtrat.getNameExtrat());
        // Ensure CardView is clickable
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), RecepiesActivity.class));
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ExtratAdapter() {
    }
}

