package jrichardson.snhu.myrecipebox.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jrichardson.snhu.myrecipebox.application.MyRecipeBoxCLI;
import jrichardson.snhu.myrecipebox.exception.RecipeException;
import jrichardson.snhu.myrecipebox.exception.RecipeExceptionType;
import jrichardson.snhu.myrecipebox.model.Ingredient;
import jrichardson.snhu.myrecipebox.model.Recipe;
import jrichardson.snhu.myrecipebox.model.RecipeType;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;

/**
 * The CLI controller for the MyRecipeBox Application
 * @author richardson SNHU
 */
public class RecipeBoxCLIController {
    private static final Logger LOG = Logger.getLogger(RecipeBoxCLIController.class.getName());
    
    public RecipeBoxCLIController(){}
    
    public Recipe newRecipe(){
        
        RecipeBoxCLIController.LOG.log(Level.INFO, "Taking User Input for Test");
        
        //What operating runtime are we in
        final String os = System.getProperty("os.name");
        /**
            Windows script file to clear the screen, its a 
            resource file on the classpath (cls.bat). This is required on Windows 
            due to issues execing "cmd /c cls" from a processBuilder
        */
        //the script file name
        final String batFile = "cls.bat";
        //the temp dir
        final String tempDir = "C:/StepTestDir";
        File TempDir = new File(tempDir);
        File file = new File(TempDir.getPath()+"/"+batFile);
        
        //The ArrayList for the Recipe Ingredients collection
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        
        //The blank Recipe to be created from user input, 
        //using default constructor
        Recipe theRecipe =  new Recipe();
        
        
        //flow controll for ingredients collection
        boolean addMoreIngredients = true;
        
        //get a scanner to recieve user input
        Scanner sc = new Scanner(System.in); 
        
        //user input for ingredients
        String ingredientAnswer;
        
        //.......................................................
        // Start the session.........
        //.......................................................
        
        //Clear the Screen to begin the session
        RecipeUtils.clearScreen(os, TempDir, file, MyRecipeBoxCLI.class);
        
        //create the new Recipe with user input
        createRecipe(theRecipe, sc);
               
       do{ //while we still want to create ingredients for this recipe
           
             //Clear the Screen to begin the session
             RecipeUtils.clearScreen(os, TempDir, file, RecipeBoxCLIController.class);
          
            //Start the collection for the ingedients
            System.out.println("Would you like to create a new ingredient? " 
                                                                + "Yes or No: ");
            ingredientAnswer = sc.nextLine();
            
            //does the user want to add ingredients tot this recipe
            if(ingredientAnswer.equalsIgnoreCase("Yes") || 
                                    ingredientAnswer.equalsIgnoreCase("Y" )){
                //we know what the answer is, so flush the string
                ingredientAnswer = "";
                try{
                    //yes, lets add ingreients
                    addMoreIngredients = true;
                    //collect ingredient data and add to the 
                    //IngredientList for this Recipe
                    ingredientList.add(fillIngredient(sc));
                    
                 //were there any issues while creating the ingredients   
                }catch(RecipeException re){
                    if(re.getType() == RecipeExceptionType.INVALID_INGREDIENT){
                           System.out.println();
                           System.out.println("INGREDIENT CREATION ERROR");        
                           System.out.println(RecipeExceptionType.INVALID_INGREDIENT
                                                                     .toString());
                           System.out.println();
                           System.out.println(re.getMessage());
                          RecipeBoxCLIController.LOG.log(Level.SEVERE,
                                                    re.getType().toString(), re);

                       }
                       else{
                           System.out.println();
                           System.out.println("SYSTEM ERROR");
                           System.out.println();
                           System.out.println(re.getMessage());
                           RecipeBoxCLIController.LOG.log(Level.SEVERE,
                                                    re.getType().toString(), re);
                       }
                }
            }
            else if(ingredientAnswer.equalsIgnoreCase("No") || 
                                        ingredientAnswer.equalsIgnoreCase("N")){
                //we know what the answer is, so flush the string
                ingredientAnswer = "";
                //the user is done adding ingredients
                addMoreIngredients = false;   
                if(ingredientList.isEmpty()){
                     System.out.println("The Recipe " + theRecipe
                                                        .getRecipeName()
                                                        .toUpperCase()
                                                    + " contains no ingedients");
                }
            }
            else{
                //flush the string, were going to keep going
                ingredientAnswer = "";
                addMoreIngredients = true;
            }
        } while(addMoreIngredients);
       
       //we collected all the ingredients that the user wants to addd
       //so add them to the recipe
       theRecipe.setIngredientList(ingredientList);
       return theRecipe;
       
       
    }
    
    private void createRecipe(Recipe theRecipe, Scanner sc){
        String recipeName;
        int type;
        int numServ;
        int favorite;
        
        //start the interface, start collecting the recipe data from the user
        System.out.println();
        System.out.println("What is the name of your new recipe? ");
        recipeName = sc.nextLine();
        theRecipe.setRecipeName(recipeName);
        System.out.println();
        System.out.println("What type of recipe is this?");
        System.out.println("1. BREAKFAST");
        System.out.println("2. DINNER");
        System.out.println("3. DESERT");
        System.out.println("4. APPETIZER");
        System.out.println("5. SIDE DISH");
        System.out.println("6. COCKTAIL");
        System.out.println("7. OTHER (Default)");
        System.out.println();
        System.out.println("Please select the corresponding number of your type ");
        type = sc.nextInt();
        
        if(type <=0 || type > 7){
            theRecipe.setType(RecipeType.OTHER);
        }
        else{
            switch(type){
                case 1:
                    theRecipe.setType(RecipeType.BREAKFAST);
                    break;
                case 2:
                    theRecipe.setType(RecipeType.DINNER);
                    break;
                case 3:
                    theRecipe.setType(RecipeType.DESERT);
                    break;
                case 4:
                    theRecipe.setType(RecipeType.APPETIZER);
                    break;
                case 5:
                    theRecipe.setType(RecipeType.SIDE_DISH);
                    break;
                case 6:
                    theRecipe.setType(RecipeType.COCKTAIL);
                    break;
                case 7:
                    theRecipe.setType(RecipeType.OTHER);
                    break;
            }
        }
        
        System.out.println();
        System.out.println("How many servings are in this recipe? ");
        numServ = sc.nextInt();
        System.out.println();
        theRecipe.setNumServings(numServ);
        
        System.out.println("Is this one of your favorite recipes? "
                                               + "1. True, 2. False (default)");
        favorite = sc.nextInt();
        switch(favorite){
            case 1:
                theRecipe.setFavorite(true);
                break;
            case 2: 
                theRecipe.setFavorite(false);
                break;
            default:
                theRecipe.setFavorite(false);
                break;
        }
    }
        
    private Ingredient fillIngredient(Scanner sc) throws RecipeException{
        /*
        There is no default constructor for the Ingredient Class
        Collect the data and return the new Ingredient
        */
        
        //The name of this Ingredient
        String ingredientName;
        
        //The Unit of Measure for this Ingredient
        String ingredientUOM;
        
        //The Number of calories per UOM
        int numCalsPerUOM;
        
        //The amount required for this Ingredient
        float ingredientAmount;
        
        //The Ingredient to be constrcuted
        Ingredient theIngredient;
        
        // Start the collection on this Ingredient
        System.out.println();
        System.out.println("What is the name of your ingredient? ");
        ingredientName = sc.nextLine();
        
        System.out.println();
        System.out.println("What is the Unit of Measure (UoM) for this ingredient? ");
        ingredientUOM = sc.nextLine();
        
        System.out.println();
        System.out.println("What amount is required of this ingredient/UoM? ");
        ingredientAmount = sc.nextFloat();
        
        System.out.println();
        System.out.println("What is the calorie count per UOM for this ingredient? ");
        numCalsPerUOM = sc.nextInt();
                
        theIngredient =  new Ingredient(ingredientName, ingredientAmount,
                                        ingredientUOM, numCalsPerUOM);
        
        //return this new Ingredient
        return theIngredient;
        
    }
    
    private void printRecipe(Recipe recipe){
        //print the Recipe to the console
        System.out.print(recipe.toString());
    }
}
