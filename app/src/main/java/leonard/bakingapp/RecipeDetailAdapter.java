package leonard.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import leonard.bakingapp.data.HeaderHolder;
import leonard.bakingapp.data.Ingredient;
import leonard.bakingapp.data.IngredientHolder;
import leonard.bakingapp.data.Step;
import leonard.bakingapp.data.StepHolder;
import leonard.bakingapp.data.SubheaderHolder;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = RecipeDetailAdapter.class.getSimpleName();
    private List<Object> mDetailList;
    private final int HEADER = 0;
    private final int SUBHEADER = 1;
    private final int INGREDIENT = 2;
    private final int STEP = 3;

    public RecipeDetailAdapter(List<Object> objects){
        mDetailList = objects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case HEADER:
                View headerView = inflater.inflate(R.layout.detail_header,parent,false);
                viewHolder = new HeaderHolder(headerView);
                break;
            case INGREDIENT:
                View ingredientView = inflater.inflate(R.layout.detail_ingredient,parent,false);
                viewHolder = new IngredientHolder(ingredientView);
                break;
            case SUBHEADER:
                View subheaderView = inflater.inflate(R.layout.detail_subheader,parent,false);
                viewHolder = new SubheaderHolder(subheaderView);
                break;
            case STEP:
                View stepView = inflater.inflate(R.layout.detail_step,parent,false);
                viewHolder = new StepHolder(stepView);
            default:
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder){
            Log.d(TAG, "onBindViewHolder HeaderHolder: " + position);
        }
        if (holder instanceof SubheaderHolder){
            Log.d(TAG, "onBindViewHolder SubheaderHolder: " + position);
        }


        switch (holder.getItemViewType()){
            case INGREDIENT:
                IngredientHolder ingredientHolder = (IngredientHolder) holder;
                configureIngredientHolder(ingredientHolder,position);
                break;
            case HEADER:
                Log.d(TAG, "onBindViewHolder: switch HEADER");
                HeaderHolder headerHolder = (HeaderHolder) holder;
                configureHeaderHolder(headerHolder,position);
                break;
            case SUBHEADER:
                Log.d(TAG, "onBindViewHolder: switch SUBHEADER");
                SubheaderHolder subheaderHolder = (SubheaderHolder) holder;
                configureSubheaderHolder(subheaderHolder,position);
                break;
            case STEP:
                StepHolder stepHolder = (StepHolder) holder;
                configureStepHolder(stepHolder,position);

            default:
                break;

        }
    }

    private void configureIngredientHolder(IngredientHolder ingredientHolder, int position){
        Ingredient ingredient = (Ingredient) mDetailList.get(position);
        if (ingredient != null){
            ingredientHolder.getQuantity().setText(ingredient.quantity);
            ingredientHolder.getIngredient().setText(ingredient.ingredient);
        }
    }

    private void configureStepHolder(StepHolder stepHolder, int position){
        Step step = (Step) mDetailList.get(position);
        stepHolder.getShortDescription().setText(step.shortDes);
    }

    private void configureSubheaderHolder(SubheaderHolder subheaderHolder, int position){
        String subheader = (String) mDetailList.get(position);
        subheaderHolder.getSubheader().setText(subheader);
    }

    private void configureHeaderHolder(HeaderHolder headerHolder, int position){
        String recipename = (String) mDetailList.get(position);
        headerHolder.getRecipeName().setText(recipename);
        headerHolder.getRecipeImage().setImageResource(R.drawable.default_recipe_back);
    }

    @Override
    public int getItemCount() {
        return mDetailList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        Object item = mDetailList.get(position);
        if(position==0 && item instanceof String){
            Log.d(TAG, "getItemViewType position: " + position + " HEADER");
            return HEADER;
        } else if (item instanceof String){
            Log.d(TAG, "getItemViewType position: " + position + " SUBHEADER");
            return SUBHEADER;
        } else if (item instanceof Ingredient){
            return INGREDIENT;
        } else if (item instanceof Step){
            return STEP;
        }
        return -1;
    }
}
