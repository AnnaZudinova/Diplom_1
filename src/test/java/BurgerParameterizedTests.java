import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    static Database database = new Database();
    static List<Bun> buns = database.availableBuns();
    static List<Ingredient> ingredients = database.availableIngredients();

private Ingredient firstIngredient;
private Ingredient secondIngredient;
private Ingredient thirdIngredient;
private Bun bun;

private static int randomIndex = new Random().nextInt(ingredients.size());

public BurgerParameterizedTests (Ingredient firstIngredient, Ingredient secondIngredient, Ingredient thirdIngredient, Bun bun) {
    this.firstIngredient=firstIngredient;
    this.secondIngredient=secondIngredient;
    this.thirdIngredient=thirdIngredient;
    this.bun=bun;
}

    @Parameterized.Parameters(name="Тестовые данные:{0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {ingredients.get(randomIndex),ingredients.get(randomIndex),ingredients.get(randomIndex),buns.get(0)},
                {ingredients.get(randomIndex),ingredients.get(randomIndex),ingredients.get(new Random().nextInt(randomIndex)),buns.get(1)},
                {ingredients.get(randomIndex),ingredients.get(randomIndex),ingredients.get(new Random().nextInt(randomIndex)),buns.get(2)}
        };
    }

    @Test
    public void addIngredientsAddsIngredientsToBurger() {
    Burger burger = new Burger();
    List<Ingredient> burgerIngredients = Arrays.asList (firstIngredient,secondIngredient,thirdIngredient);
    burger.addIngredient(firstIngredient);
    burger.addIngredient(secondIngredient);
    burger.addIngredient(thirdIngredient);

    assertEquals(burgerIngredients,burger.ingredients);
    }

    @Test
    public void setBunSetsBurgerBun () {
        Burger burger = new Burger();

        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
}
