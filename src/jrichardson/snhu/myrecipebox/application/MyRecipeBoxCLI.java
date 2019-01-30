package jrichardson.snhu.myrecipebox.application;

import java.util.ArrayList;
import java.util.List;
import jrichardson.snhu.myrecipebox.model.*;
import jrichardson.snhu.myrecipebox.exception.*;

/**
 * The My Recipe Box Command Line Interface (CLI) launcher
 * @author jhrichardson SNHU
 */
public class MyRecipeBoxCLI {
    
    
    /**
     * Main CLI Method for the MyRecipeBox Application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length > 0 && args[0].equalsIgnoreCase("no_user")){
            NoUserInput();
        }
        else{
            UserInput();
        }
    }
    
    private static void NoUserInput(){
        //Testing a single Recipe Class based on static data
        //Lets Test the Recipe Class and Print to console
      Recipe applePie;
      List<Ingredient> ingredients = new ArrayList<>();
      
      try{
          
           ingredients.add(new Ingredient("Apple Spice", 0.25F, "Cup", 100));
           ingredients.add(new Ingredient("Sugar", 3.0F, "Cup", 773));
           ingredients.add(new Ingredient("Apple Slices", 3.0F, "Cup", 250));
           ingredients.add(new Ingredient("Cinnamon", 4.0F, "TbleSpoon", 2));
           ingredients.add(new Ingredient("Butter", 10.0F, "TbleSpoon", 50));
           ingredients.add(new Ingredient("Flour", 4.0F, "Cup", 80));
           ingredients.add(new Ingredient("Salt", 1.0F, "teaspoon", 1));
           ingredients.add(new Ingredient("Milk", 1.5F, "cup", 40));
           
            applePie = new Recipe("Apple Pie", ingredients,
                                                RecipeType.DESERT, 8, true);
            
            System.out.print(applePie.toString());
           
      }catch(RecipeException re){
          System.out.println(re.getType().toString());
          System.out.println();
          System.out.println(re.getMessage());
          System.exit(1);
      }  
    }
    
    private static void UserInput(){
        System.out.println(">>>>>>>>> LOOKING FOR USER INPUT >>>>>>>>>>>>>>>");
    }
}
