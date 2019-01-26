package jrichardson.snhu.myrecipebox.exception;

/**
 *
 * @author jhrichardson SNHU
 */
public class RecipeException extends Exception {
    
    /*RecipeExceptionType*/
    private final RecipeExceptionType type;
    
    /**
     * Create a new RecipeException
     * 
     * @param type  The type of error for the MyRecipeBox Application
     *              annotated as (@link #RecipeExceptionType)
     * @param message The detailed message for this exception.
     * 
     * @see RecipeExceptionType
     * @see String
     */
    public RecipeException(RecipeExceptionType type, String message){
        super(message);
        this.type = type;      
    }
   
    /**
     * Get the RecipeExceptionType for this exception
     * 
     * @return  The RecipeExceptionType for this exception
     * @see RecipeExceptionType
     */
    public RecipeExceptionType getType(){
        return this.type;
    }
     
}
