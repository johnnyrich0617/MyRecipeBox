package jrichardson.snhu.myrecipebox.exception;

/**
 *  Enumeration of error types for the MyRecipeBox Application
 * @author jhrichardson SNHU
 */
public enum RecipeExceptionType {
    
    /**
     * Defines an error in the runtime execution 
     */
    SYSTEM_ERROR,

    /**
     * Defines a validation error in an Ingredient
     */
    INVALID_INGREDIENT
}