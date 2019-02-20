package jrichardson.snhu.myrecipebox.controller;

import jrichardson.snhu.myrecipebox.model.RecipeBoxModel;

/**
 * Abstract Application Controller for the MyRecipeBox Application
 * @author jrichardson SNHU
 */
public interface AppController {
    
    /**
     * Entrance point into the an implementing controller
     * @param model The data Model for this controller
     */
    public void run(RecipeBoxModel model);
    
}
