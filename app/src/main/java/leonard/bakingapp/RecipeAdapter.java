package leonard.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import leonard.bakingapp.classes.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CardViewHolder> {
    private Context mContext;
    private List<Recipe> mRecipes;

    RecipeAdapter(List<Recipe> recipes, Context context){
        //  set up recipe list or initialize an empty list
        if (recipes != null) {
            mRecipes = recipes;
        }
        else {
            mRecipes = new ArrayList<>();
        }
        mContext = context;
    }

    static class CardViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        TextView mRecipeName;
        CardViewHolder(CardView itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.recipe_card_view);
            mRecipeName = itemView.findViewById(R.id.recipe_text_view);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent,false);

        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        holder.mRecipeName.setText(mRecipes.get(position).name);

        // set on click listener to launch the recipe detail activity
        final Intent intent = new Intent(mContext, RecipeDetailActivity.class);
        intent.putExtra("recipe", mRecipes.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
