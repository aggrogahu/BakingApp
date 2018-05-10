package leonard.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CardViewHolder> {
    private List<String> mRecipeList;
    private Context mContext;

    RecipeAdapter(List<String> recipes, Context context){
        if (recipes != null) {
            mRecipeList = recipes;
        }
        else {
            //TODO remove dummy recipe array
            mRecipeList = new ArrayList<>();
            mRecipeList.add("Nutella Pie");
            mRecipeList.add("Brownies");
            mRecipeList.add("Yellow Cake");
//            mRecipeList.add("Cheesecake");
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
        holder.mRecipeName.setText(mRecipeList.get(position));

        final Intent intent = new Intent(mContext, RecipeDetailActivity.class);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mOnRecipeCardClickListener.onCardSelected(position);
//                Toast.makeText(mContext, "Item Clicked: " + mRecipeList.get(position), Toast.LENGTH_LONG).show();
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
