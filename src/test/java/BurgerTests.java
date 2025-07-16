import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Test
    public void removeIngredientRemovesIngredientFromBurger () {
        Database database = new Database();
        Burger burger = new Burger();
        int randomIndex = new Random().nextInt(database.availableIngredients().size());

        burger.addIngredient(database.availableIngredients().get(randomIndex));
        burger.removeIngredient(0);
        assert(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesIngredientsOrder() {
        Database database = new Database();
        Burger burger = new Burger();
        int randomIndex = new Random().nextInt(database.availableIngredients().size());
        Ingredient firstIngredient = database.availableIngredients().get(randomIndex);
        Ingredient secondIngredient = database.availableIngredients().get(randomIndex);
        Ingredient thirdIngredient = database.availableIngredients().get(randomIndex);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(2,1);

        assertEquals(thirdIngredient,burger.ingredients.get(1));
    }

    @Test
    public void getPriceInvokesBunGetPrice() {
        Burger burger=new Burger();

        burger.setBuns(bun);
        burger.getPrice();

        Mockito.verify(bun).getPrice();
    }

    @Test
    public void getPriceInvokesIngredientGetPrice() {
        Burger burger=new Burger();
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.getPrice();

        Mockito.verify(ingredient).getPrice();
    }

    @Test public void getPriceReturnsPrice() {
        Burger burger=new Burger();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        assertEquals(300f, burger.getPrice(),0);
    }

    @Test
    public void getReceiptInvokesBunGetName2Times () {
        Burger burger = new Burger();

        burger.setBuns(bun);
        burger.getReceipt();

        Mockito.verify(bun,Mockito.times(2)).getName();
    }

    @Test
    public void getReceiptInvokesIngredientGetTypeForEachIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        burger.getReceipt();

        Mockito.verify(ingredient,Mockito.times(3)).getType();
    }

    @Test
    public void getReceiptInvokesIngredientGetNameForEachIngredient() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        Mockito.when(ingredient.getName()).thenReturn("cutlet");
        burger.getReceipt();

        Mockito.verify(ingredient,Mockito.times(3)).getName();
    }
}
