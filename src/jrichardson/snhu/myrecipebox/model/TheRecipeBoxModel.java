package jrichardson.snhu.myrecipebox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * TheRecipeBoxModel is the main data model for the MyRecipeBox Application.
 * 
 * TheRecipeBoxModel is a singleton (There can be only one!) based on 
 * the Bill Pugh implementation
 * 
 * This singleton is completely thread safe based on the the use of a static 
 * inner class for the lazy initialization and containerization of the 
 * static final INSTANCE of the class. Static Inner classes are only 
 * instantiated when accessed and so thereby thread safe based on 
 * thread safety guarantees for static initialization.
 * 
 * The first call to getInstance() will create the INSTANCE, with each subsequent
 * call only returning the static final INSTANCE.`
 * 
 * @author jrichardson SNHU
 */
public class TheRecipeBoxModel {
    
    ArrayList<Recipe> recipeList;
    HashMap<UUID,ArrayList> ingredientMap;
    private String test;
    
    /**
     * Private constructor hidden from external access.  
     * Used to create the TheRecipeBoxModel INSTANCE.
     */
    private TheRecipeBoxModel() {
        this.recipeList = new ArrayList<>();
        this.ingredientMap = new HashMap<>();
    }
    
    /**
     * Used to create and retrieve the TheRecipeBoxModel INSTANCE
     * @return The "TheRecipeBoxModel" INSTANCE singleton
     */
    public static TheRecipeBoxModel getInstance() {
        return TheRecipeBoxModelHolder.INSTANCE;
    }
    
    public String getTest(){
        return this.test;
    }
    
    public void setTest(String testdata){
        this.test = testdata;
    }
    
    /**
     * Get a particular Ingredient for a recipe based on the Ingredient's name
     * @param recipeName The name of the recipe to retrieve the Ingredient
     * @param ingredientName The name of the Ingredient to return
     * @return The named Ingredient
     */
    public Ingredient getRecipeIngredient(String recipeName, String ingredientName){
        return null;
    }
    
    /**
     * Request an Ingredient from a recipe with a particular UID
     * @param recipeUID The unique ID for the recipe that contains the requested
     * Ingredient
     * @param ingredientUID The unique identifier to the requested Ingredient
     * @return The requested Ingredient matching the requested Ingredient UID
     */
    public Ingredient getRecipeIngredient(Recipe recipeUID, UUID ingredientUID){
        return null;
    }
    
    /**
     * Request a Recipe for the RecipeBox Model
     * @param recipeUID  The unique ID of the requested Recipe
     * @return The requested Recipe
     */
    public Recipe getRecipe(UUID recipeUID){
        return null;
    }
    
    /**
     * Request a Recipe for the RecipeBox Model
     * @param recipeName The name of the requested Recipe
     * @return The requested Recipe
     */
    public Recipe getRecipe(String recipeName){
        return null;
    }
    
    /**
     * The private inner class of TheRecipeBoxModel.  This class is required as
     * part of the the Bill Pugh singleton design and implementation.  It serves as 
     * the thread safe isolation container for the singleton instance.
     * 
     * @author jhrichardson SNHU
     */
    private static class TheRecipeBoxModelHolder {

        /**
         * The thread safe singleton instance of "TheRecipeBoxModel"
         */
        private static final TheRecipeBoxModel INSTANCE = new TheRecipeBoxModel();
    }
}
