package jrichardson.snhu.myrecipebox.model;

import java.util.ArrayList;
import java.util.Collection;
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
    /**
     * RecipeBox Model main container
     */
    HashMap<UUID,Recipe> recipeMap;
   
    
    /**
     * Private constructor hidden from external access.  
     * Used to create the TheRecipeBoxModel INSTANCE.
     */
    private TheRecipeBoxModel() {
        this.recipeMap = new HashMap<>();
    }
    
    /**
     * Used to create and retrieve the TheRecipeBoxModel INSTANCE
     * @return The "TheRecipeBoxModel" INSTANCE singleton
     */
    public static TheRecipeBoxModel getInstance() {
        return TheRecipeBoxModelHolder.INSTANCE;
    }
    
    /**
     *
     * @param recipe
     */
    public void add(Recipe recipe){
       if(recipe != null){
        this.recipeMap.put(recipe.getUid(), recipe);
       }
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
     *
     */
    public void printAllRecipeNames(){
        Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        int i = 1;
        
        System.out.println("Your Recipe Box Recipes......");
        System.out.println();
        for(Recipe recipe : recipeList){
            System.out.println(i + ". " + recipe.getRecipeName());
            i++;
        }
    }
    
    /**
     *
     * @param recipeName
     */
    public void printRecipe(String recipeName){
       Collection<Recipe> recipes = this.recipeMap.values();
        ArrayList<Recipe> recipeList = new ArrayList<>(recipes);
        
        recipeList.stream().filter((recipe) -> (recipe.getRecipeName()
                                                .equals(recipeName)))
                                                .forEachOrdered((recipe) -> {
         System.out.println(recipe.toString());
        });
    }
    
    /**
     *
     */
    public void printRecipeBox(){
        System.out.println(this.toString());
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
