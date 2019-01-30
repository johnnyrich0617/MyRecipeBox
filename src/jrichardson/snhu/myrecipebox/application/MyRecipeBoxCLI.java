package jrichardson.snhu.myrecipebox.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jrichardson.snhu.myrecipebox.model.*;
import jrichardson.snhu.myrecipebox.exception.*;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;

/**
 * The My Recipe Box Command Line Interface (CLI) launcher
 * @author jhrichardson SNHU
 */
public class MyRecipeBoxCLI {
    private static final Logger LOG = Logger.getLogger(Recipe.class.getName());
    
    /**
     * Main CLI Method for the MyRecipeBox Application
     * @param args the command line arguments "no_user = static test"
     * 
     */
    public static void main(String[] args){
        //does the user want to run the static test or dynamic (User Driven)
        //dynamic is the default no arg run
        if(args.length > 0 && args[0].equalsIgnoreCase("no_user")){
            NoUserInput();
        }
        else{
            UserInput();
        }
    }
    
    private static void NoUserInput(){
        MyRecipeBoxCLI.LOG.log(Level.INFO, "Using Static Recipe for Test");
        //Testing a single Recipe Class based on static data
        //Lets Test the Recipe Class and Print to console
      Recipe applePie;
      List<Ingredient> ingredients = new ArrayList<>();
      
      //setup for the test....
      //create a set of ingredients and add them to the
      //ingredientlist for this recipe
      try{
          
           ingredients.add(new Ingredient("Apple Spice", 0.25F, "Cup", 100));
           ingredients.add(new Ingredient("Sugar", 3.0F, "Cup", 773));
           ingredients.add(new Ingredient("Apple Slices", 3.0F, "Cup", 250));
           ingredients.add(new Ingredient("Cinnamon", 4.0F, "TbleSpoon", 2));
           ingredients.add(new Ingredient("Butter", 10.0F, "TbleSpoon", 50));
           ingredients.add(new Ingredient("Flour", 4.0F, "Cup", 80));
           ingredients.add(new Ingredient("Salt", 1.0F, "teaspoon", 1));
           ingredients.add(new Ingredient("Milk", 1.5F, "cup", 40));
           
           //create the new Recipe using the static test content
            applePie = new Recipe("Apple Pie", ingredients,
                                                RecipeType.DESERT, 8, true);
            
            //print the Recipe to the console and visually verify
            System.out.print(applePie.toString());
           
      }catch(RecipeException re){
          //something may have gone wrong
          //print and exit error
          System.out.println(re.getType().toString());
          System.out.println();
          System.out.println(re.getMessage());
          System.exit(1);
      }  
    }
    
    private static void UserInput(){
        MyRecipeBoxCLI.LOG.log(Level.INFO, "Taking User Input for Test");
        
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
             RecipeUtils.clearScreen(os, TempDir, file, MyRecipeBoxCLI.class);
          
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
                          MyRecipeBoxCLI.LOG.log(Level.SEVERE,
                                                    re.getType().toString(), re);

                       }
                       else{
                           System.out.println();
                           System.out.println("SYSTEM ERROR");
                           System.out.println();
                           System.out.println(re.getMessage());
                           MyRecipeBoxCLI.LOG.log(Level.SEVERE,
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
       
       // flow control for printing of Recipe
       boolean more;
       
       do{//waiting on correct user input 
           RecipeUtils.clearScreen(os, TempDir, file, MyRecipeBoxCLI.class);
           //Ask the user if they would like to print the recipe just created
           System.out.println();
           System.out.println("Would you like to print your new Recipe? "
                                                                + "Yes or No");
           String shouldprint = sc.nextLine();
           System.out.println();

           shouldprint = shouldprint.toLowerCase();
            
            switch (shouldprint){
                case "yes":
                    more = false;
                    printRecipe(theRecipe);
                    break;

                case "no":
                    more = false;
                    System.out.println();
                    System.out.println();
                    System.out.println("Thank you, Goodbye!");
                    break;

                default:
                    more = true;
                    break;

            }
       } while(more);
        
       //clean up before we exit
       cleanup(os, file, TempDir);
    }
    
    private static void createRecipe(Recipe theRecipe, Scanner sc){
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
        
    private static Ingredient fillIngredient(Scanner sc) 
                                                throws RecipeException{
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
    
    private static void printRecipe(Recipe recipe){
        //print the Recipe to the console
        System.out.print(recipe.toString());
    }
    
    private static void cleanup(String os, File file, File dir){
        //if we are running in a windows runtime
        //remove tha windows script file if its there
        if(os.contains("Windows") && file.exists()){
            //remove the file first
            file.delete();
            //then the temp dir
            dir.delete();
        }
    }
    
}
