package jrichardson.snhu.myrecipebox.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/**
 * RecipeBoxModel is the main data model for the MyRecipeBox Application.
 * 
 * RecipeBoxModel is a singleton (There can be only one!) based on 
 the Bill Pugh implementation
 
 This singleton is completely thread safe based on the the use of a static 
 inner class for the lazy initialization and containerization of the 
 static final INSTANCE of the class. Static Inner classes are only 
 instantiated when accessed and so thereby thread safe based on 
 thread safety guarantees for static initialization.
 
 The first call to getInstance() will create the INSTANCE, with each subsequent
 call only returning the static final INSTANCE.`
 * 
 * @author jrichardson SNHU
 */
public class RecipeBoxModel {
    /**
     * RecipeBox Model main container
     */
    HashMap<UUID,Recipe> recipeMap;
   
    
    /**
     * Private constructor hidden from external access.  
     * Used to create the TheRecipeBoxModel INSTANCE.
     */
    private RecipeBoxModel() {
        this.recipeMap = new HashMap<>();
    }
    
    /**
     * Used to create and retrieve the RecipeBoxModel INSTANCE
     * @return The "RecipeBoxModel" INSTANCE singleton
     */
    public static RecipeBoxModel getInstance() {
        return TheRecipeBoxModelHolder.INSTANCE;
    }
    
    /**
     * Add a Recipe to the RecipeBox
     * @param recipe  The Recipe to add
     */
    public void add(Recipe recipe){
       if(recipe != null){
        this.recipeMap.put(recipe.getUid(), recipe);
       }
   }
    
    /**
     * Request a Recipe for the RecipeBox Model
     * @param recipeUID  The unique ID of the requested Recipe
     * @return The requested Recipe
     */
    public Recipe get(UUID recipeUID){
        return this.recipeMap.get(recipeUID);
    }
    
    /**
     * Request a Recipe for the RecipeBox Model
     * @param recipeName The name of the requested Recipe
     * @return The requested Recipe
     */
    public Recipe get(String recipeName){
        
        Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        
        for(Recipe recipe : recipeList){
            if (recipe.getRecipeName().equals(recipeName)){
                return recipe;
            }
        }
        return null;
    }
    
    /**
     * Get a particular Ingredient for a recipe based on the Ingredient's name
     * @param recipeName The name of the recipe to retrieve the Ingredient
     * @param ingredientName The name of the Ingredient to return
     * @return The named Ingredient
     */
    public Ingredient getRecipeIngredient(String recipeName, String ingredientName){
        
        if(recipeName != null && ingredientName != null){
            Collection<Recipe> recipes = this.recipeMap.values();
            ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        
            for(Recipe recipe : recipeList){
                if (recipe.getRecipeName().equals(recipeName)){
                    return recipe.getIngredient(ingredientName);       
                }
            }
            return null;
        }
        else
            return null;
        
    }
    
    /**
     * Request an Ingredient from a recipe with a particular UID
     * @param recipeUID The unique ID for the recipe that contains the requested
     * Ingredient
     * @param ingredientUID The unique identifier to the requested Ingredient
     * @return The requested Ingredient matching the requested Ingredient UID
     */
    public Ingredient getRecipeIngredient(UUID recipeUID, UUID ingredientUID){
      
        Recipe recipe = this.recipeMap.get(recipeUID);        
        return recipe == null ? null : recipe.getIngredient(ingredientUID);    
    }
    
    /**
     * Request an Ingredient from a recipe with a particular UID
     * @param recipeUID unique identifier for a recipe in the model
     * @param ingredientName the requested ingredient name
     * @return The requested ingredient
     */
    public Ingredient getRecipeIngredient(UUID recipeUID, String ingredientName){
       
        Recipe recipe;  
        recipe = this.recipeMap.get(recipeUID); 
        return recipe == null ? null : recipe.getIngredient(ingredientName);   
    }
    
    /**
     * Request the Ingredient List for a recipe in the model
     * @param recipeUID unique identifier for a recipe in the model
     * @return The requested Ingredient List
     */
    public ArrayList<Ingredient> getRecipeIndredients(UUID recipeUID){
      
        Recipe recipe;
        recipe = this.recipeMap.get(recipeUID);
        return recipe == null ? null : (ArrayList)recipe.getIngredientList();
        
    }
    
    /**
     * Request the Ingredient List for a recipe in the model
     * @param recipeName common name for a recipe in the model
     * @return The requested Ingredient List
     */
    public ArrayList<Ingredient> getRecipeIndredients(String recipeName){
        
        Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        
        for(Recipe recipe : recipeList){
            if (recipe.getRecipeName().equals(recipeName)){
                return (ArrayList)recipe.getIngredientList();
            }
        }
        return null;       
    }
    
    /**
     * Returns a list of Recipes contained in this RecipeBox 
     * @return List of all Recipes for this RecipeBox
     */
    public ArrayList<Recipe> getAllRecipes(){
        Collection<Recipe> recipes = this.recipeMap.values();
        return new ArrayList<>(recipes);
    }
    
    
    
    /**
     * Get an ordered list representation of the model
     * @return the requested ordered list
     */
    public HashMap<Integer,String> getOrderedRecipeList(){
        
        HashMap<Integer, String> orderedList = new HashMap<>();
        Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        int i = 1;
        
        for(Recipe recipe : recipeList){
            orderedList.put(i, recipe.getRecipeName());
            i++;
        }
        
        return orderedList;
    }

    @Override
    public String toString() {
        
        Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        String recipeBoxData= "MyRecipeBox [\n\t";
        
        for (Recipe recipe : recipeList){
            recipeBoxData = recipeBoxData + recipe.toString();
        }
        
        recipeBoxData = recipeBoxData + "\n]";
        
        return recipeBoxData;
    }
    
    
    /**
     * The private inner class of RecipeBoxModel.  This class is required as
     * part of the the Bill Pugh singleton design and implementation.  It serves as 
     * the thread safe isolation container for the singleton instance.
     * 
     * @author jhrichardson SNHU
     */
    private static class TheRecipeBoxModelHolder {

        /**
         * The thread safe singleton instance of "RecipeBoxModel"
         */
        private static final RecipeBoxModel INSTANCE = new RecipeBoxModel();
    }
}
