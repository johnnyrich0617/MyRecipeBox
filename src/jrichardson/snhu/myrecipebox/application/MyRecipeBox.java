package jrichardson.snhu.myrecipebox.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import jrichardson.snhu.myrecipebox.model.RecipeBoxModel;
import jrichardson.snhu.myrecipebox.controller.AppController;
import jrichardson.snhu.myrecipebox.controller.RecipeBoxCLIController;

/**
 * Main method for the MyRecipeBox Application
 * @author richardson SNHU
 */
public class MyRecipeBox {
     private static final Logger LOG = Logger.getLogger(MyRecipeBox.class.getName());
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       //Log the session start 
       MyRecipeBox.LOG.log(Level.INFO, "main():: Application Start ...");
       
       //create the controller for this applciation       
       AppController controller = new RecipeBoxCLIController();
       //execute its run method and pass in the data model
       controller.run(RecipeBoxModel.getInstance());
    }
}
