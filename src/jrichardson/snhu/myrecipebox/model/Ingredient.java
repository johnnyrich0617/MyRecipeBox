package jrichardson.snhu.myrecipebox.model;

import java.util.Objects;
import java.util.UUID;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;
import jrichardson.snhu.myrecipebox.exception.RecipeException;
import jrichardson.snhu.myrecipebox.exception.RecipeExceptionType;

/**
 * The main Ingredient class for the MyRecipeBox Application
 * @author jhrichardson SNHU
 */
public class Ingredient {
    
    /**
     * The Common Name (CN) for this ingredient
     * */
    private String ingredientName;
    
    /**
     * The amount needed for this Ingredient
     * */
    private float amountOfIngredient;
    
    /**
     * The unit Of Measure used for this Ingredient
     * */
    private String unitOfMeasure;
    
    /**
     * Number of calories per UOM (Cup, Tablespoon, Teaspoon, oz, lbs..)
     * */
    private int caloriesPerUOM;
    
    /**
     * Total number of calories for this Ingredient as calculated by
     * totalCalories = (amountOfIngredient * caloriesPerUOM)
     * */
    private double totalCalories;
    
    /**
     * The unique identifier for this Ingredient
     */
    private final UUID uid;
    
    /**
     * A Recipe Ingredient
     * Will not except null values, a valid ingredient require all values 
     * 
     * @param ingredientName The name of this Ingredient
     * @param amountOfIngredient The amount of this Ingredient
     * @param unitOfMeasure The unit of measure for this Ingredient
     * @param caloriesPerUOM The number of calories for this Ingredient 
     *                       based on Unit of Measure
     * @throws jrichardson.snhu.myrecipebox.exception.RecipeException
     */
    public Ingredient(String ingredientName, float amountOfIngredient, 
                            String unitOfMeasure, int caloriesPerUOM) 
                            throws RecipeException{
        
        //validate input params, no nulls, empty strings or negitive values
        if ( ingredientName == null || ingredientName.isEmpty())
            throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
                        "Ingredient Name must exist, cannot be NULL or empty");
        else if( amountOfIngredient <= 0.0)
            throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
                        "Ingredient Amount must exist, cannot be 0.0");
        else if( unitOfMeasure == null || unitOfMeasure.isEmpty())
             throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
               "Ingredient Unit Of Measure must exist, cannot be NULL or empty");
        else if ( caloriesPerUOM < 0)
            throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
            "Ingredient Calories Per Unit Of Measure must exist and cannot be negitive");
        
        //Params passed validation, set the params for this Ingredient
        
        this.ingredientName = ingredientName;
        this.amountOfIngredient = amountOfIngredient;
        this.unitOfMeasure = unitOfMeasure;
        this.caloriesPerUOM = caloriesPerUOM;
        
        //calculate these based on method params above
        this.totalCalories = this.calcTotalCalories(this.caloriesPerUOM, 
                                                    this.amountOfIngredient);
        this.uid = RecipeUtils.generateUID();
    }

    /**
     *
     * @return The name of the Ingredient
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     *
     * @return The amount of this Ingredient
     */
    public float getAmountOfIngredient() {
        return amountOfIngredient;
    }

    /**
     *
     * @return The unit of measure used for this Ingredient
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     *
     * @return Number of calories per unit of measure
     */
    public int getCaloriesPerUOM() {
        return caloriesPerUOM;
    }
    
    /**
     *
     * @return The total number of calories for this ingredient
     */
    public double getTotalCalories(){
        return this.totalCalories;
    }
    
    /**
     * 
     * @return The String value for the Ingredient UID
     * @see String
     */
    public String getIngredientUIDAsString(){
        return String.valueOf(this.uid);
    }
    
    /**
     * 
     * @return The Ingredient UID
     * @see java.util.UUID
     */
    public UUID getIngredientUID(){
        return this.uid;
    }
    
    /**
     * Formatted print out of this Ingredient
     * to System.out
     */
    public void printIngredient(){
        System.out.print(this.toString());
    }

    /**
     *
     * @param ingredientName Name of this Ingredient
     * @throws jrichardson.snhu.myrecipebox.exception.RecipeException
     */
    public void setIngredientName (String ingredientName) 
                                                    throws RecipeException {
        
        if( ingredientName == null || ingredientName.isEmpty())
             throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
                        "Ingredient Name must exist, cannot be NULL or empty");
        
        this.ingredientName = ingredientName;
    }

    /**
     *
     * @param amountOfIngredient Amount of this Ingredient
     * @throws jrichardson.snhu.myrecipebox.exception.RecipeException
     */
    public void setAmountOfIngredient(float amountOfIngredient) 
                                                    throws RecipeException {
        
        if( amountOfIngredient <= 0.0)
            throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
                        "Ingredient Amount must exist, cannot be 0.0");
        
        this.amountOfIngredient = amountOfIngredient;
        //recalculate total calories if amountOfIngredient changes
        this.totalCalories = this.calcTotalCalories(this.caloriesPerUOM, 
                                                    this.amountOfIngredient);
    }

    /**
     *
     * @param unitOfMeasure  the unit of measure for this Ingredient
     * @throws jrichardson.snhu.myrecipebox.exception.RecipeException
     */
    public void setUnitOfMeasure(String unitOfMeasure) throws RecipeException {
        
        if( unitOfMeasure == null || unitOfMeasure.isEmpty())
             throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
               "Ingredient Unit Of Measure must exist, annot be NULL or empty");
        
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     *
     * @param caloriesPerUOM Number of calories per unit of measure
     * @throws jrichardson.snhu.myrecipebox.exception.RecipeException
     */
    public void setCaloriesPerUOM(int caloriesPerUOM) throws RecipeException {
        
        if ( caloriesPerUOM < 0)
            throw new RecipeException(RecipeExceptionType.INVALID_INGREDIENT,
      "Ingredient Calories Per Unit Of Measure must exist, cannot be negitive");
        
        this.caloriesPerUOM = caloriesPerUOM;
        //recalculate total calories if caloriesPerUOM changes
        this.totalCalories = this.calcTotalCalories(this.caloriesPerUOM, 
                                                    this.amountOfIngredient);
    }
    
    /**
     *
     *  @return The String representation of this object
     */
    @Override
    public String toString() {
        return "Ingredient{\n" 
                + "\tIngredientName       =" + this.ingredientName + ",\n" 
                + "\tamountOfIngredient   =" + this.amountOfIngredient + ",\n"
                + "\tunitOfMeasure        =" + this.unitOfMeasure + ",\n" 
                + "\tcaloriesPerUOM       =" + this.caloriesPerUOM + ",\n" 
                + "\ttotalCalories        =" + this.totalCalories + ",\n"
                + "\tIngredient UID       =" + this.uid + ",\n"
                + "\tIngredient HashCode  =" + this.hashCode() + "\n\t},";
    }
    
    /**
     * Produces the Hashed representation of this object (32bit signed int)
     * and can be used by HashTables, HashMaps, and equals() methods
     * 
     * @return hash The hash for this ingredient
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.ingredientName);
        hash = 41 * hash + Float.floatToIntBits(this.amountOfIngredient);
        hash = 41 * hash + Objects.hashCode(this.unitOfMeasure);
        hash = 41 * hash + this.caloriesPerUOM;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.totalCalories) ^ 
                            (Double.doubleToLongBits(this.totalCalories) >>> 32));
        return hash;
    }
    
    /**
    * 
    * Deep Compare of this Ingredient to another Ingredient 
    *
    * @param obj The object of comparison
    * @return True if Object equals this Object
    *         False if this Object NOT equal to Object
    **/
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (Float.floatToIntBits(this.amountOfIngredient) != 
                               Float.floatToIntBits(other.amountOfIngredient)) {
            return false;
        }
        if (this.caloriesPerUOM != other.caloriesPerUOM) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalCalories) != 
                                Double.doubleToLongBits(other.totalCalories)) {
            return false;
        }
        if (!Objects.equals(this.ingredientName, other.ingredientName)) {
            return false;
        }
        if (!Objects.equals(this.unitOfMeasure, other.unitOfMeasure)) {
            return false;
        }
        return true;
    }
    
    /**
     * Computes the total calories for this Ingredient
     * 
     * @param caloriesPerUOM Number of calories per Unit Of Measure
     * @param totalAmount Total amount of this Ingredient required in 
     *                      Units of Measure
     * @return The total computed calories for this Ingredient
     */ 
    private double calcTotalCalories(int caloriesPerUOM, float totalAmount){
        
        return RecipeUtils.calcTotalCalories(caloriesPerUOM, totalAmount);
    }
}
