package tn.esprit.myhealthyfoodapp.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.model.Saison;

public class SaisonAdapter extends RecyclerView.Adapter<SaisonAdapter.SaisonViewHolder> {

    /* start of inner ViewHolder class */
    public static class SaisonViewHolder extends RecyclerView.ViewHolder {
        // declare views

        TextView saisonNameText;
        ImageView saisonImage;
        public CardView cardView;
        LinearLayout layout;
        // Constructor to this inner class
        public SaisonViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            saisonNameText = itemView.findViewById(R.id.textView_saisonName);
            saisonImage = itemView.findViewById(R.id.image_saison);
            cardView = (CardView) itemView.findViewById(R.id.cardSaisonView);
            layout = itemView.findViewById(R.id.layout_saison);
        }
    }

    private Context mContext;
    private List<Saison> mData;

    public SaisonAdapter(Context mContext, List<Saison> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public SaisonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_saison_adapter, parent, false);
        return new SaisonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SaisonViewHolder holder, int position) {
        Saison currentSaison = mData.get(position);
        if (currentSaison.getSaison_image().isEmpty()) {
            // No image
            holder.saisonImage.setImageResource(R.drawable.nopicture);
        } else {

            Picasso.get().load(currentSaison.getSaison_image())
                    .into(holder.saisonImage);
        }
        holder.saisonNameText.setText(currentSaison.getSaison_name());

        // Ensure CardView is clickable
       holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (holder.saisonImage.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(holder.layout, new AutoTransition());
                holder.saisonImage.setVisibility(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public SaisonAdapter() {
    }
}