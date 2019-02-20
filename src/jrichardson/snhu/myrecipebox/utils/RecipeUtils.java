package jrichardson.snhu.myrecipebox.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import jrichardson.snhu.myrecipebox.model.Ingredient;


/**
 * Utility class for the MyRecipeBox Application
 * @author jhrichardson SNHU
 */
public class RecipeUtils {
    private static final Logger LOG = Logger.getLogger(RecipeUtils.class.getName());
    //the script file name
    private static final String BAT_FILE = "cls.bat";
    //the temp dir
    private static final String TEMP_DIR = "C:/StepTestDir";
    
    private static final String OS = System.getProperty("os.name");
       
    /**
     *
     * @param caloriesPerUOM Number of calories per Unit Of Measure
     * @param totalAmount Total amount of this Ingredient for this recipe in UOM
     * @return Total number of calories for this Ingredient based on UOM
     */
    public static double calcTotalCalories(int caloriesPerUOM, 
                                            float totalAmount){
        
        return (double)(totalAmount * caloriesPerUOM);
    }
    
    /**
     * Calculates the total number of a calories for a recipe from 
     * an Ingredients list
     * @param ingredients A map of Ingredients from a Recipe
     * @return Calculated total number of calories for a Recipe
     */
    public static double calcRecipeTotalCalories(List<Ingredient> ingredients){
       
       double totalCalories = 0.0;
        
        if (ingredients != null && !ingredients.isEmpty()) {
            for(Ingredient ingredient : ingredients){
                totalCalories = totalCalories + ingredient.getTotalCalories();
            }
            return totalCalories;
        } else
            return totalCalories;
    }
    
    /**
     * Generates a random Unique Identifier
     * @return A new random java.util.UUID (Type 4)
     * @see UUID
     */
    public static UUID generateUID(){
        //get a ramdom UUID (Type 4)
        return UUID.randomUUID();
    }
    
    /**
     * Clear the CLI Application Screen(console)
     * Consumers of this method should call cleanUp before program exit
     */
    public static void clearScreen(){
        File TempDir = new File(TEMP_DIR);
        File file = new File(TempDir.getPath()+"/"+BAT_FILE);
        try{
            // if the file does not exit on the file system &&
            // we are running on a Windows platform
            if(!file.exists() && OS.contains("Windows")){
                //no, then pull it from the jar file and push to the file system
                InputStream is = RecipeUtils.class.getClassLoader()
                                      .getResourceAsStream("resources/"+BAT_FILE);
                //the small buffer for the file read
                byte[] buf = new byte[20];
                //read in the file contents to the buffer
                int numread = is.read(buf);
                //close the stream, not needed anymore
                is.close();

                //If we have data then write it out to a temp file for use
                if(numread > 0){
                    //is the temp dir there, if not create it
                    if(!TempDir.exists()){
                        TempDir.mkdir();
                    }

                    //write out the buffer from the inputstream
                    //create the new file on the file system
                    OutputStream os = new FileOutputStream(file);
                    os.write(buf,0,buf.length);
                    os.close();
                }
            }

            //Are we running in a windows environment?
            if(OS.contains("Windows")){
                //yes, exec the bat file to clear the screen
                new ProcessBuilder(file.getPath()).inheritIO().start().waitFor();
            }
            //no, then it must be a unix varrient or x86 linux
            //this will not work for all other platforms though
            else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch(IOException | InterruptedException ioe){
            //log any issues we encountered, but dont kill, it will just not 
            //clear the screen.  We are eating this exception
            RecipeUtils.LOG.log(Level.SEVERE, ioe.getMessage(), ioe);
        }
    }
    
    /**
     * Clean up temp Files used by the utility.
     * Consuming programs should call this if the ClearScrean method 
     * has been invoked.
     */
    public static void cleanup(){
        File TempDir = new File(TEMP_DIR);
        File file = new File(TempDir.getPath()+"/"+BAT_FILE);
        //if we are running in a windows runtime
        //remove tha windows script file if its there
        if(OS.contains("Windows") && file.exists()){
            //remove the file first
            file.delete();
            //then the temp dir
            TempDir.delete();
        }
    }
    
}
