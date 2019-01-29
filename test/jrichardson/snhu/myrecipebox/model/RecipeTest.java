package jrichardson.snhu.myrecipebox.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jrichardson.snhu.myrecipebox.exception.RecipeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jrichardson SNHU
 */
public class RecipeTest {
    
    private Recipe recipe;
    private List<Ingredient> ingredients = new ArrayList<>();
    
    public RecipeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try{
            ingredients.add(new Ingredient("Apple Spice", 0.25F, "Cup", 100));
            ingredients.add(new Ingredient("Sugar", 3.0F, "Cup", 773));
            ingredients.add(new Ingredient("Apple Slices", 3.0F, "Cup", 250));
            ingredients.add(new Ingredient("Cinnamon", 4.0F, "TbleSpoon", 2));
            ingredients.add(new Ingredient("Butter", 10.0F, "TbleSpoon", 50));
            ingredients.add(new Ingredient("Flour", 4.0F, "Cup", 80));
            ingredients.add(new Ingredient("Salt", 1.0F, "teaspoon", 1));
            ingredients.add(new Ingredient("Milk", 1.5F, "cup", 40));
            
            recipe = new Recipe("Apple Pie", ingredients,
                                                RecipeType.DESERT, 8, true);
            
        }catch(RecipeException re){
            
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIngredientList method, of class Recipe.
     */
    @Test
    public void testGetIngredientList() {
        System.out.println("getIngredientList");
        List<Ingredient> result = recipe.getIngredientList();
        assertArrayEquals(ingredients.toArray(), result.toArray());
    }

    /**
     * Test of setIngredientList method, of class Recipe.
     */
    @Test
    public void testSetIngredientList() {
        System.out.println("setIngredientList");
        List<Ingredient> IngredientList = recipe.getIngredientList();
        Recipe instance = new Recipe();
        instance.setIngredientList(IngredientList);
    }

    /**
     * Test of getType method, of class Recipe.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        RecipeType result = recipe.getType();
        assertEquals(RecipeType.DESERT, result);
    }

    /**
     * Test of getUid method, of class Recipe.
     */
    @Test
    public void testGetUid() {
        System.out.println("getUid");
        UUID result = recipe.getUid();
        assertNotNull(result);
    }

    /**
     * Test of getTotalCalories method, of class Recipe.
     */
    @Test
    public void testGetTotalCalories() {
        System.out.println("getTotalCalories");
        double result = recipe.getTotalCalories();
        assertEquals(3983.0, result, 0.0);
    }

    /**
     * Test of getNumServings method, of class Recipe.
     */
    @Test
    public void testGetNumServings() {
        System.out.println("getNumServings");
        int result = recipe.getNumServings();
        assertEquals(8, result);
    }

    /**
     * Test of setType method, of class Recipe.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        recipe.setType(RecipeType.OTHER);
        assertEquals(RecipeType.OTHER, recipe.getType());
        recipe.setType(RecipeType.DESERT);
        assertEquals(RecipeType.DESERT, recipe.getType());
    }

    /**
     * Test of isFavorite method, of class Recipe.
     */
    @Test
    public void testIsFavorite() {
        System.out.println("isFavorite");
        boolean expResult = true;
        boolean result = recipe.isFavorite();
        assertEquals(expResult, result);
        assertTrue(recipe.isFavorite());
    }

    /**
     * Test of setFavorite method, of class Recipe.
     */
    @Test
    public void testSetFavorite() {
        System.out.println("setFavorite");
        boolean favorite = false;
        Recipe instance = new Recipe();
        recipe.setFavorite(favorite);
        assertFalse(recipe.isFavorite());
        recipe.setFavorite(true);
        assertTrue(recipe.isFavorite());
    }

    /**
     * Test of getRecipeName method, of class Recipe.
     */
    @Test
    public void testGetRecipeName() {
        System.out.println("getRecipeName");
        
        String expResult = "Apple Pie";
        String result = recipe.getRecipeName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRecipeName method, of class Recipe.
     */
    @Test
    public void testSetRecipeName() {
        System.out.println("setRecipeName");
        String recipeName = "Ice Cream";
    
        recipe.setRecipeName(recipeName);
        assertEquals(recipeName, recipe.getRecipeName());
        recipe.setRecipeName("Apple Pie");
        assertEquals("Apple Pie", recipe.getRecipeName());
    }

    /**
     * Test of setNumServings method, of class Recipe.
     */
    @Test
    public void testSetNumServings() {
        System.out.println("setNumServings");
        int numServings = 1;
        
        recipe.setNumServings(numServings);
        assertEquals(numServings, recipe.getNumServings());
        recipe.setNumServings(8);
        assertEquals(8, recipe.getNumServings());
        
    }

    /**
     * Test of toString method, of class Recipe.
     */
    @Test
    public void testToString() {
        System.out.println("toString");    
        assertNotNull(recipe.toString()); 
    }

    

    /**
     * Test of equals method, of class Recipe.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Recipe obj = new Recipe();
        boolean expResult = false;
        assertFalse(recipe.equals(obj));
    }
    
}
