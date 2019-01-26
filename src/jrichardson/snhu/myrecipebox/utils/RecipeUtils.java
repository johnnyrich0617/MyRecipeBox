package jrichardson.snhu.myrecipebox.utils;

import java.util.List;
import java.util.UUID;
import jrichardson.snhu.myrecipebox.model.Ingredient;


/**
 * Utility class for the MyRecipeBox Application
 * @author jhrichardson SNHU
 */
public class RecipeUtils {

       
    /**
     *
     * @param caloriesPerUOM Number of calories per Unit Of Measure
     * @param totalAmount Total amount of this Ingredient for this recipe in UOM
     * @return Total number of calories for this Ingredient based on UOM
     */
    public static double calcTotalCalories(int caloriesPerUOM, 
                                            float totalAmount){
        
        return (double)(totalAmount * caloriesPerUOM);
    }
    
    /**
     * Calculates the total number of a calories for a recipe from 
     * an Ingredients list
     * @param ingredients A map of Ingredients from a Recipe
     * @return Calculated total number of calories for a Recipe
     */
    public static double calcRecipeTotalCalories(List<Ingredient> ingredients){
       
       double totalCalories = 0.0;
        
        if (ingredients != null && !ingredients.isEmpty()) {
            for(Ingredient ingredient : ingredients){
                totalCalories = totalCalories + ingredient.getTotalCalories();
            }
            return totalCalories;
        } else
            return totalCalories;
    }
    
    /**
     * Generates a random Unique Identifier
     * @return A new random java.util.UUID (Type 4)
     * @see UUID
     */
    public static UUID generateUID(){
        return UUID.randomUUID();
    }
    
}
