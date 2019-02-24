package jrichardson.snhu.myrecipebox.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jrichardson.snhu.myrecipebox.exception.RecipeException;
import jrichardson.snhu.myrecipebox.exception.RecipeExceptionType;
import jrichardson.snhu.myrecipebox.model.Ingredient;
import jrichardson.snhu.myrecipebox.model.Recipe;
import jrichardson.snhu.myrecipebox.model.RecipeType;
import jrichardson.snhu.myrecipebox.model.RecipeBoxModel;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;

/**
 * The CLI controller for the MyRecipeBox Application.  
 * Implements the AppController Interface
 * @see AppController
 * @author richardson SNHU
 * 
 */
public class RecipeBoxCLIController implements AppController{
    private static final Logger LOG = Logger.getLogger(RecipeBoxCLIController.class.getName());
    private RecipeBoxModel model;
    //private final Scanner sc;
    private Recipe aRecipe;
    
    /**
     * RecipeBoxCLIController default constructor
     */
    public RecipeBoxCLIController(){
        //sc = new Scanner(System.in);
    }
    
    /**
     * This controllers run method.  Entrance point for program flow start
     * on this controller
     * @param model The data model for this controller
     * @see AppController
     */
    @Override
    public void run(RecipeBoxModel model){
        
        RecipeBoxCLIController.LOG.log(Level.INFO, "run():: Controller Start ...");
        Scanner sc;
            
        //set the model for this controller
        this.model = model;
        
        try{
            //start the welcome message
            System.out.println();
            System.out.println("Welcome to your Recipe box! ");
            System.out.println();
            System.out.println("This Application will allow you to create new recipes");
            System.out.println("and add them to a collection known as the MyRecipeBox");
            System.out.println("The Application will begin soon, hope you enjoy it!");
            Thread.sleep(4000);  //sleep for 4 secs, possible splash screen
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        

            do{ //continue until the user exits
                int item = 0;
                RecipeUtils.clearScreen();

               while(item <= 0 || item > 5){
                   sc = new Scanner(System.in);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("=============================================");
                    System.out.println("=============================================");
                    System.out.println("              MY RECIPE BOX                     ");
                    System.out.println("=============================================");
                    System.out.println("=============================================");
                    System.out.println();
                    System.out.println();
                    System.out.println("      1. Create New Recipe                   ");
                    System.out.println("      2. Print your entire Recipe Box        ");
                    System.out.println("      3. View a list of your Recipes in your Box");
                    System.out.println("      4. Print a Recipe from your Recipe Box ");
                    System.out.println("      5. Exit the MyRecipeBox Application    ");
                    System.out.println();
                    System.out.println();
                    System.out.println("=============================================");
                    System.out.println("=============================================");
                    System.out.println();
                    System.out.println();
                    item = sc.nextInt();
               }
               sc = new Scanner(System.in);
               //execute the selection
               switch(item){
                   case 1:
                       RecipeUtils.clearScreen();
                       createRecipe();
                       break;
                   case 2:
                       RecipeUtils.clearScreen();
                       System.out.println(model.toString());
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       System.out.println("Select 'g' and Enter to continue....");
                       sc.next();
                       break;
                   case 3:
                       RecipeUtils.clearScreen();
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       printAllRecipeNames();
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       System.out.println("Select 'g' and Enter to continue....");
                       sc.next();
                       break;
                   case 4:
                       RecipeUtils.clearScreen();
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       printRecipe(selectRecipe());
                       System.out.println();
                       System.out.println();
                       System.out.println();
                       System.out.println("Select 'g' and Enter to continue....");
                       sc.next();
                       break;
                   case 5:
                       fastExit();
                       break;
               }

               System.out.println();
               System.out.println();

            }while(true);
        }catch(InterruptedException ie){
            RecipeBoxCLIController.LOG.log(Level.WARNING, ie.getMessage());
            fastExit();
        }
        
    }
    
    public void createRecipe(){
        
        Scanner sc;
        boolean addAnother;
        String answer;
           
        // loop until the user has no more recipes to add
        do{
            sc = new Scanner(System.in);
            aRecipe = newRecipe();
            model.add(aRecipe);
            System.out.println();
            System.out.println("Would you like to add another Recipe? Y or N");
            answer = sc.nextLine();
            addAnother = answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Y" );

        }while(addAnother);

       RecipeUtils.clearScreen();  
    }
    
    
    
    /**
     * Print all the names of the Recipes in the Model as a list
     */
    private void printAllRecipeNames(){
        
        ArrayList<Recipe> recipeList = model.getAllRecipes();
        
        int i = 1;
        
        System.out.println("Your Recipe Box Recipes......");
        System.out.println();
        System.out.println();
        for(Recipe recipe : recipeList){
            System.out.println();
            System.out.println(i + ". " + recipe.getRecipeName());
            i++;
        }
    }
    
    /**
     * Print a specified Recipe
     * @param recipeName The name of the Recipe to print
     */
    private void printRecipe(String recipeName){
         ArrayList<Recipe> recipeList = model.getAllRecipes();
         
         
        recipeList.stream().filter((recipe) -> (recipe.getRecipeName()
                                                .equals(recipeName)))
                                                .forEachOrdered((recipe) -> {
         System.out.println(recipe.toString());
        });
    }
    
    /**
     * Print a list of Recipes for the user to select from
     * @return The common name of the selected Recipe
     */
    private String selectRecipe(){
   
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> recipes = model.getOrderedRecipeList();
        int selection;
        
        recipes.entrySet().stream().map((entry) -> {
            System.out.println();
            return entry;
        }).forEachOrdered((entry) -> {
            System.out.println(entry.getKey()+ ". " + entry.getValue());
        });
        
        System.out.println();
        System.out.println("Please select the Recipe you would like to print.");
        selection = sc.nextInt();
        
        return recipes.get(selection);    
        
    } 
    
    /**
     * Create a new Recipe
     * @return The newly created Recipe
     */
    private Recipe newRecipe(){
        
        RecipeBoxCLIController.LOG.log(Level.INFO, "newRecipe():: Creating a new Recipe");
                
        Scanner sc = new Scanner(System.in);
        
        //The ArrayList for the Recipe Ingredients collection
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        
        //The blank Recipe to be created from user input, 
        //using default constructor
        Recipe theRecipe =  new Recipe();
        
        
        //flow controll for ingredients collection
        boolean addMoreIngredients = true;
        
        //user input for ingredients
        String ingredientAnswer;
        
        //.......................................................
        // Start the session.........
        //.......................................................
        
        
         //start the interface, start collecting the recipe data from the user
        System.out.println();
        System.out.println("What is the name of your new recipe? ");
        String recipeName = sc.nextLine();
        //Set the name of this recipe
        theRecipe.setRecipeName(recipeName);
        
        //create the new Recipe with user input
        createRecipe(theRecipe);
        //This is a bit of a hack to ensure the input buffer from the keyboard 
        //is flushed. Scannner has no way to flush the buffer
         sc = null;
         
         
       do{ //while we still want to create ingredients for this recipe
            //get a fresh scanner for ingredient collection for each ingredient
             sc = new Scanner(System.in);
             //Clear the Screen to begin the session
             RecipeUtils.clearScreen();
          
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
                    ingredientList.add(fillIngredient());
                    
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
    
    /**
     * Get the Recipe details from the user
     * @param theRecipe The Recipe Shell
     */
    private void createRecipe(Recipe theRecipe){
        String recipeName;
        int type;
        int numServ;
        int favorite;
        Scanner sc = new Scanner(System.in);
       
        
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
    
/**
 * Get an ingredient for a recipe
 * @return The new ingredient
 * @throws RecipeException 
 */    
    private Ingredient fillIngredient() throws RecipeException{
        /*
        There is no default constructor for the Ingredient Class
        Collect the data and return the new Ingredient
        */
        
        Scanner sc = new Scanner(System.in);
        
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
    
    /**
     * Exit the program
     */
    private void fastExit() {
        RecipeUtils.cleanup(); 
        sayThankYou();
        System.exit(0);
    }
    
    /**
     * Say Good Bye
     */
    private void sayThankYou(){
        System.out.println();
        System.out.println();
        System.out.println("Thank you, hope you enjoyed the MyRecipeBox Application");
    }
}
