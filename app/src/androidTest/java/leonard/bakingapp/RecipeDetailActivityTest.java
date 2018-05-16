package leonard.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import leonard.bakingapp.classes.viewholders.StepHolder;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {

    private static Matcher<RecyclerView.ViewHolder> isTheFirstStepHolder() {
        return new TypeSafeMatcher<RecyclerView.ViewHolder>() {
            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder customHolder) {
                boolean isStepHolder = customHolder instanceof StepHolder;
                boolean isRecipeIntro = false;
                if (isStepHolder) {
                    StepHolder stepHolder = (StepHolder) customHolder;
                    isRecipeIntro = stepHolder.getShortDescription().getText().equals("Recipe Introduction");
                }
                return isStepHolder && isRecipeIntro;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is a StepHolder");
            }
        };
    }



    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule = new ActivityTestRule<>(RecipeActivity.class);


    @Test
    public void clickFirstRecipeStep_OpenFirstStepDetail(){
        onView(withId(R.id.recipe_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.recipe_detail_recycler_view))
                .perform(RecyclerViewActions.actionOnHolderItem(isTheFirstStepHolder(),click()));

        onView(allOf(isDescendantOfA(withId(R.id.action_bar)), withText("Recipe Introduction")))
                .check(matches(isDisplayed()));

//        onView()


    }
}
