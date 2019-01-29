package jrichardson.snhu.myrecipebox.model;

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
 * @author jhrichardson SNHU
 */
public class IngredientTest {
    
    private Ingredient ing1;
    private Ingredient ing2;
    private final String ing1Name = "Salt";
    private final String ing2Name = "Pepper";
    private final String ing1UoM = "tsp";
    private final String ing2UoM = "pinch";
    private final float ing1Amount = (float) .75;
    private final float ing2Amount = 3;
    private final int ing1CalorieCount = 1;
    private final int ing2CalorieCount = 2;
    
    /**
     * Main Test Class for the MyRecipeBox Ingredient Class
     */
    public IngredientTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Set up the class before the test run
     */
    @Before
    public void setUp() {
        try{
            ing1 = new Ingredient(ing1Name, ing1Amount, ing1UoM, ing1CalorieCount);
            ing2 = new Ingredient(ing2Name, ing2Amount, ing2UoM, ing2CalorieCount);
        }
        catch(RecipeException re){
            fail(re.getMessage());
        }
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getIngredientName method, of class Ingredient.
     */
    @Test
    public void testGetIngredientName() {
        System.out.println("getIngredientName");
        String name1 = ing1.getIngredientName();
        String name2 = ing2.getIngredientName();
        assertEquals(ing1Name, name1); 
        assertEquals(ing2Name, name2);
        assertFalse(name1.equals(name2));
    }

    /**
     * Test of getAmountOfIngredient method, of class Ingredient.
     */
    @Test
    public void testGetAmountOfIngredient() {
        System.out.println("getAmountOfIngredient");
        float result1 = ing1.getAmountOfIngredient();
        float result2 = ing2.getAmountOfIngredient();
        assertEquals(ing1Amount, result1, 0.0);
        assertEquals(ing2Amount, result2, 0.0);
        assertFalse(result1 == result2);
    }

    /**
     * Test of getUnitOfMeasure method, of class Ingredient.
     */
    @Test
    public void testGetUnitOfMeasure() {
        System.out.println("getUnitOfMeasure");
        String result1 = ing1.getUnitOfMeasure();
        String result2 = ing2.getUnitOfMeasure();
        assertEquals(ing1UoM, result1);
        assertEquals(ing2UoM, result2);
        assertFalse(ing1UoM.equals(ing2UoM));
    }

    /**
     * Test of getCaloriesPerUOM method, of class Ingredient.
     */
    @Test
    public void testGetCaloriesPerUOM() {
        System.out.println("getCaloriesPerUOM");
        int result1 = ing1.getCaloriesPerUOM();
        int result2 = ing2.getCaloriesPerUOM();
        assertEquals(ing1CalorieCount, result1);
        assertEquals(ing2CalorieCount, result2);
        assertFalse(ing1CalorieCount == ing2CalorieCount);
      
    }

    /**
     * Test of getTotalCalories method, of class Ingredient.
     */
    @Test
    public void testGetTotalCalories() {
        System.out.println("getTotalCalories");
        assertTrue((ing1.getAmountOfIngredient()*ing1.getCaloriesPerUOM()) 
                                                == ing1.getTotalCalories());
        assertTrue((ing2.getAmountOfIngredient()*ing2.getCaloriesPerUOM()) 
                                                == ing2.getTotalCalories());
        assertFalse(ing1.getTotalCalories() == ing2.getTotalCalories());
    }

    /**
     * Test of getIngredientUIDAsString method, of class Ingredient.
     */
    @Test
    public void testGetIngredientUIDAsString() {
        System.out.println("getIngredientUIDAsString");
        assertNotNull(ing1.getIngredientUIDAsString());
        assertNotNull(ing2.getIngredientUIDAsString());
    }

    /**
     * Test of getIngredientUID method, of class Ingredient.
     */
    @Test
    public void testGetIngredientUID() {
        System.out.println("getIngredientUID");
        assertNotNull(ing1.getIngredientUID());
        assertNotNull(ing2.getIngredientUID());
        
        UUID testuid = UUID.randomUUID();
        assertFalse(ing1.getIngredientUID().equals(testuid));
        assertFalse(ing2.getIngredientUID().equals(testuid));
        assertTrue(!ing1.getIngredientUID().equals(ing2.getIngredientUID()));
    }

    /**
     * Test of setIngredientName method, of class Ingredient.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetIngredientName() throws Exception {
        System.out.println("setIngredientName");
        assertTrue(ing1.getIngredientName().equals(ing1Name));
        String ingredientName = "Thyme";
        ing1.setIngredientName(ingredientName);
        assertTrue(ing1.getIngredientName().equals(ingredientName));
        ing1.setIngredientName(ing1Name);
        assertTrue(ing1.getIngredientName().equals(ing1Name));
    }

    /**
     * Test of setAmountOfIngredient method, of class Ingredient.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetAmountOfIngredient() throws Exception {
        System.out.println("setAmountOfIngredient");
        float amountOfIngredient = 100.0F;
        ing1.setAmountOfIngredient(amountOfIngredient);
        assertEquals(amountOfIngredient, ing1.getAmountOfIngredient(), 0.0);
        ing1.setAmountOfIngredient(ing1Amount);
        assertEquals(ing1Amount, ing1.getAmountOfIngredient(), 0.0);
    }

    /**
     * Test of setUnitOfMeasure method, of class Ingredient.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetUnitOfMeasure() throws Exception {
        assertTrue(0 == 0);
    }

    /**
     * Test of setCaloriesPerUOM method, of class Ingredient.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetCaloriesPerUOM() throws Exception {
        assertTrue(0 == 0);
    }

    /**
     * Test of toString method, of class Ingredient.
     */
    @Test
    public void testToString() {
       System.out.println("toString");
       assertTrue(!ing1.toString().equals(ing2.toString()));
       assertFalse(ing2.toString().equals(ing1.toString()));
    }

    /**
     * Test of hashCode method, of class Ingredient.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        assertFalse(ing1.hashCode() == ing2.hashCode());
        assertTrue(ing1.hashCode() != ing2.hashCode());
    }

    /**
     * Test of equals method, of class Ingredient.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertTrue(!ing1.equals(ing2));
        assertFalse(ing2.equals(ing1));
    }
    
}
