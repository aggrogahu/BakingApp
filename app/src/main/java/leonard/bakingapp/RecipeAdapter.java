package leonard.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CardViewHolder> {

//    private Context mContext;
    private List<String> mRecipeList;


    public RecipeAdapter(List<String> recipes){
//        mContext = context;
        if (recipes != null) {
            mRecipeList = recipes;
        }
        else {
            //TODO remove dummy array
            mRecipeList = new ArrayList<>();
            mRecipeList.add("Nutella Pie");
            mRecipeList.add("Brownies");
            mRecipeList.add("Yellow Cake");
            mRecipeList.add("Cheesecake");
        }
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mRecipeName;
        public CardViewHolder(CardView itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.recipe_card_view);
            mRecipeName = itemView.findViewById(R.id.recipe_text_view);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent,false);
        CardViewHolder cardViewHolder = new CardViewHolder(cardView);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.mRecipeName.setText(mRecipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
