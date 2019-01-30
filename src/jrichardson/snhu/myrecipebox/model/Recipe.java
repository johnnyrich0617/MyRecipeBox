package jrichardson.snhu.myrecipebox.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;


/**
 *
 * @author jhrichardson SNHU
 */
public class Recipe {

    private static final Logger LOG = Logger.getLogger(Recipe.class.getName());
    
    private List<Ingredient>IngredientList;
    private RecipeType type;
    private boolean favorite;
    private String recipeName;
    private UUID uid;
    private double totalCalories;
    private int numServings;
    
    private final String defaultRecipeName = "NEW RECIPE";
    
    /**
     *
     */
    public Recipe(){
        this.favorite = false;
        this.type = RecipeType.OTHER;
        this.IngredientList = new ArrayList<>();
        this.recipeName = defaultRecipeName;
        this.uid = RecipeUtils.generateUID();
        this.numServings = 0;
        this.totalCalories = RecipeUtils.calcRecipeTotalCalories(IngredientList);
        Recipe.LOG.exiting("Recipe Class", "Defualt Constructor::Recipe()");
    }

    /**
     *
     * @param name
     * @param IngredientList
     * @param type
     * @param numServings
     * @param favorite
     */
    public Recipe(String name, List<Ingredient> IngredientList, 
                                                        RecipeType type,
                                                        int numServings,
                                                        boolean favorite) {
        this.recipeName = name;
        this.IngredientList = IngredientList;
        this.type = type;
        this.favorite = favorite;
        this.uid = this.uid = RecipeUtils.generateUID();
        this.numServings = numServings;
        this.totalCalories = RecipeUtils.calcRecipeTotalCalories(IngredientList);
        //TODO:: Add Logging of constructor
    }

    /**
     *
     * @param recipeName
     * @param type
     * @param numServings
     * @param favorite
     */
    public Recipe(String recipeName, RecipeType type, int numServings, 
                                                            boolean favorite) {
        this.type = type;
        this.favorite = favorite;
        this.recipeName = recipeName;
        this.IngredientList = new ArrayList<>();
        this.uid = RecipeUtils.generateUID();
        this.numServings = numServings;
        this.totalCalories = RecipeUtils.calcRecipeTotalCalories(IngredientList);
          //TODO:: Add Logging of constructor
    }

    /**
     *
     * @param type
     * @param recipeName
     */
    public Recipe(RecipeType type, String recipeName) {
        this.type = type;
        this.recipeName = recipeName;
        this.favorite = false;
        this.IngredientList = new ArrayList<>();
        this.uid = RecipeUtils.generateUID();
        this.numServings = 0;
        this.totalCalories = RecipeUtils.calcRecipeTotalCalories(IngredientList);
        //TODO:: Add Logging of constructor
    }

    /**
     * Get the list of Ingredients for this Recipe
     * @return IngredientList :: List of Ingredients
     */
    public List<Ingredient> getIngredientList() {
        return IngredientList;
    }

    /**
     * Set the list of Ingredients for this Recipe
     * @param IngredientList An ArrayList of Ingredients
     */
    public void setIngredientList(List<Ingredient> IngredientList) {
        this.IngredientList = IngredientList;
    }

    /**
     * Get the RecipeType for this Recipe
     * @return jrichardson.snhu.myrecipebox.model.component.RecipeType
     */
    public RecipeType getType() {
        return type;
    }
    
    /**
     * Get the Unique Identifier for this Recipe
     * @return java.util.UUID
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * Get the total number of Calories for this Recipe
     * @return totalCalories
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    /**
     * Get the number of servings for this Recipe
     * @return numServings
     */
    public int getNumServings() {
        return numServings;
    }

    /**
     * Get the type for this Recipe
     * @param type The RecipeType for this Recipe
     */
    public void setType(RecipeType type) {
        this.type = type;
    }

    /**
     * Is this a favorite Recipe
     * @return true if favorite
     *         false if not
     */
    public boolean isFavorite() {
        return favorite;
    }

    /**
     * Sets if Recipe is favorite
     * @param favorite true if favorite, false if not
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Get the Common Name (CN) for this Recipe
     * @return String recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Set the Common Name (CN) for this Recipe
     * @param recipeName
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Set the number of servings for this Recipe
     * @param numServings
     */
    public void setNumServings(int numServings) {
        this.numServings = numServings;
    }

    @Override
    public String toString() {
        
        String allIngredients = "Ingredients [\n\t\t";
        
        if(!IngredientList.isEmpty()){
            for(Ingredient ingredient : IngredientList){
                allIngredients = (allIngredients + "\t"
                                  + ingredient.toString()+"\n\t\t");        
            }
        
            allIngredients = allIngredients + "\n]";
        }
        else{
            allIngredients = "Ingredients []\n";
        }
        
        return ("Recipe{\n\t"
                +"recipeName      = " +this.recipeName+ ",\n\t"
                +"type            = " +this.type+ ",\n\t"
                +"favorite        = " +this.favorite+ ",\n\t"
                +"totalCalories   = " +this.totalCalories+ ",\n\t"
                +"numServings     = " +this.numServings+ ",\n\t"
                +"Unique ID       = " +this.getUid().toString()+ ",\n\t"
                +"HashCode        = " +this.hashCode()+ "\n\t"
                + allIngredients);    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.recipeName);
        hash = 71 * hash + Objects.hashCode(this.uid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(this.getClass() == obj.getClass()){
            final Recipe other = (Recipe) obj;
        
            return Objects.equals(this.uid, other.uid )
                    && Objects.equals(this.hashCode(), other.hashCode());
        }
        return false;
    }
   
}
