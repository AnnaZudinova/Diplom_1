import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import praktikum.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    @Mock
    Bun bun;

    @Mock
    List<Ingredient> ingredients;

    @Mock
    Ingredient firstIngredient;

    @Mock
    Ingredient secondIngredient;

   public BurgerParameterizedTests (Ingredient firstIngredient, Ingredient secondIngredient, Bun bun) {
        this.firstIngredient=firstIngredient;
        this.secondIngredient=secondIngredient;
        this.bun=bun;
    }

    @Parameterized.Parameters(name="Тестовые данные:{0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {new Ingredient(IngredientType.SAUCE, "hot sauce", 100), new Ingredient(IngredientType.FILLING, "cutlet", 100),new Bun("black bun", 100)},
                {new Ingredient(IngredientType.SAUCE, "sour cream", 200), new Ingredient(IngredientType.FILLING, "dinosaur", 200),new Bun("white bun", 200)},
                {new Ingredient(IngredientType.SAUCE, "chili sauce", 300), new Ingredient(IngredientType.FILLING, "sausage", 300),new Bun("red bun", 300)},
        };
    }

    @Test
    public void addIngredientsAddsIngredientsToBurger() {
        Burger burger = new Burger();
        ingredients = Arrays.asList (firstIngredient,secondIngredient);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(ingredients,burger.ingredients);
    }

    @Test
    public void setBunSetsBurgerBun () {
        Burger burger = new Burger();

        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
}
