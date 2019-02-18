package jrichardson.snhu.myrecipebox.application;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import jrichardson.snhu.myrecipebox.model.*;
import jrichardson.snhu.myrecipebox.controller.RecipeBoxCLIController;
import jrichardson.snhu.myrecipebox.utils.RecipeUtils;

/**
 *
 * @author richardson SNHU
 */
public class TheRecipeBox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        TheRecipeBoxModel myRecipeBox = TheRecipeBoxModel.getInstance();
        RecipeBoxCLIController controller = new RecipeBoxCLIController();
        Scanner sc = new Scanner(System.in); 
        boolean addAnother;
        Recipe aRecipe;
        
        //What operating runtime are we in
        final String os = System.getProperty("os.name");
        /**
            Windows script file to clear the screen, its a 
            resource file on the classpath (cls.bat). This is required on Windows 
            due to issues exec-ing "cmd /c cls" from a processBuilder
        */
        //the script file name
        final String batFile = "cls.bat";
        //the temp dir
        final String tempDir = "C:/StepTestDir";
        File TempDir = new File(tempDir);
        File file = new File(TempDir.getPath()+"/"+batFile);
        
        RecipeUtils.clearScreen(os, TempDir, file, TheRecipeBox.class);
        
        System.out.println();
        System.out.println("Welcome to your Recipe box!.............. ");
        System.out.println();
        System.out.println("Would you like to add a new Recipe to your box? Y or N");
        String answer = sc.nextLine();
        if(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("Y" )){
             exitApp();
        }
        
        // Get all the recipes from the user
        do{
            aRecipe = controller.newRecipe();
            myRecipeBox.add(aRecipe);
            System.out.println();
            System.out.println("Would you like to add another Recipe? Y or N");
            answer = sc.nextLine();
            addAnother = answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Y" );

        }while(addAnother);
       
        RecipeUtils.clearScreen(os, TempDir, file, TheRecipeBox.class);
        
        //see if the user wants to print any of the recipes
        printOptions(os, TempDir, file, TheRecipeBox.class );
        
        //clean up the application before exit
        RecipeUtils.cleanup(os, TempDir, file);
        
        // exit the application
        exitApp();
    }

    private static void exitApp() {
        System.out.println();
        System.out.println();
        System.out.println("Thank you, hope you enjoyed the MyRecipeBox Application");
        System.exit(0);
    }

    private static void printOptions(String os, File TempDir, File file, Class rtc) {
        
        TheRecipeBoxModel model = TheRecipeBoxModel.getInstance();
        String answer;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Would you like to see print options for your recipes? Y or N");
            answer = sc.nextLine();
            if(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("Y" )){
                 exitApp();
            }
       
        do{
            int item = 0;

          
           while(item <= 0 || item > 4){
                
                System.out.println();
                System.out.println();
                System.out.println("1. Print your Recipe Box");
                System.out.println("2. List all Your Recipes in your Recipe Box");
                System.out.println("3. Print a Recipe from your Recipe Box");
                System.out.println("4. Exit");
                item = sc.nextInt();
           }

           switch(item){
               case 1:
                   model.printRecipeBox();                
                   break;
               case 2:                  
                   model.printAllRecipeNames();                   
                   break;
               case 3:                  
                   model.printRecipe(selectRecipe());                  
                   break;
               case 4:                   
                    exitApp();
                    break;
           }
           
           System.out.println();
           System.out.println();
           
        }while(true);
    }
    
    private static String selectRecipe(){
        
        Scanner sc = new Scanner(System.in); 
        TheRecipeBoxModel model = TheRecipeBoxModel.getInstance();
        HashMap<Integer, String> recipes = model.getOrderedRecipeList();
        int selection;
        String selectedRecipe;
        int max = recipes.size();
        
        recipes.entrySet().stream().map((entry) -> {
            System.out.println();
            return entry;
        }).forEachOrdered((entry) -> {
            System.out.println(entry.getKey()+ ". " + entry.getValue());
        });
        
        System.out.println();
        System.out.println("Please select the Recipe you would like to print.");
        selection = sc.nextInt();
        
        return selectedRecipe = recipes.get(selection);    
        
    } 
}
