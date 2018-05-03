package leonard.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    Context mContext;
    RecipeDetailFragment.OnStepClickListener mCallback;
//
//    public interface OnStepClickListener{
//        void onStepSelected(int position);
//    }

    public RecipeDetailAdapter(List<Object> objects, Context context, RecipeDetailFragment.OnStepClickListener listener){
        mDetailList = objects;
        mContext = context;
        mCallback = listener;
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
                View stepView = inflater.inflate(R.layout.detail_step_item,parent,false);
                viewHolder = new StepHolder(stepView);
            default:
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()){
            case INGREDIENT:
                IngredientHolder ingredientHolder = (IngredientHolder) holder;
                buildIngredientHolder(ingredientHolder,position);
                break;
            case HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                buildHeaderHolder(headerHolder,position);
                break;
            case SUBHEADER:
                SubheaderHolder subheaderHolder = (SubheaderHolder) holder;
                buildSubheaderHolder(subheaderHolder,position);
                break;
            case STEP:
                StepHolder stepHolder = (StepHolder) holder;
                buildStepHolder(stepHolder,position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCallback.onStepSelected(position);
                    }
                });
            default:
                break;

        }
    }

    private void buildIngredientHolder(IngredientHolder ingredientHolder, int position){
        Ingredient ingredient = (Ingredient) mDetailList.get(position);
        if (ingredient != null){
            ingredientHolder.getQuantity().setText(ingredient.quantity);
            ingredientHolder.getIngredient().setText(ingredient.ingredient);
        }
    }

    private void buildStepHolder(StepHolder stepHolder, int position){
        Step step = (Step) mDetailList.get(position);
        stepHolder.getShortDescription().setText(step.shortDes);
    }

    private void buildSubheaderHolder(SubheaderHolder subheaderHolder, int position){
        String subheader = (String) mDetailList.get(position);
        subheaderHolder.getSubheader().setText(subheader);
    }

    private void buildHeaderHolder(HeaderHolder headerHolder, int position){
        String recipeName = (String) mDetailList.get(position);
        headerHolder.getRecipeName().setText(recipeName);
        headerHolder.getRecipeImage().setImageResource(R.drawable.default_recipe_back);
    }

    @Override
    public int getItemCount() {
        return mDetailList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mDetailList.get(position);
        if(position==0 && item instanceof String){
            return HEADER;
        } else if (item instanceof String){
            return SUBHEADER;
        } else if (item instanceof Ingredient){
            return INGREDIENT;
        } else if (item instanceof Step){
            return STEP;
        }
        return -1;
    }
}
