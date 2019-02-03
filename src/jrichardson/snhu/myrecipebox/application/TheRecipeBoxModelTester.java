package jrichardson.snhu.myrecipebox.application;

import jrichardson.snhu.myrecipebox.model.*;

/**
 *
 * @author richa
 */
public class TheRecipeBoxModelTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        TheRecipeBoxModel model = TheRecipeBoxModel.getInstance();
        model.setTest("TESTING.......");
        ModelTest();
        System.out.println("MODEL........................");
        System.out.println(model.getTest());
    }
    
    private static void ModelTest(){
        TheRecipeBoxModel model2 = TheRecipeBoxModel.getInstance();
        System.out.println("MODEL2........................");
        System.out.println(model2.getTest());
        
        model2.setTest("FINISHED TESTING.................");
        
    }
    
}
