package jrichardson.snhu.myrecipebox.exception;

/**
 *
 * @author jhrichardson SNHU
 */
public class RecipeException extends Exception {
    
    /*RecipeExceptionType*/
    private final RecipeExceptionType type;
    
    /**
     *
     * @param type  The type of error for the MyRecipeBox Application
     *              annotated as (@link #RecipeExceptionType)
     * @param message The detailed message for this exception.
     */
    public RecipeException(RecipeExceptionType type, String message){
        super(message);
        this.type = type;      
    }
   
    /**
     *
     * @return  The (@link #RecipeExceptionType) for this exception
     */
    public RecipeExceptionType getType(){
        return this.type;
    }
     
}
