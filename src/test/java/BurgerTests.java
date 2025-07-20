import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient secondIngredient;

    @Mock
    List<Ingredient> ingredients;

    @Test
    public void removeIngredientRemovesIngredientFromBurgerTest () {
        Burger burger = new Burger();
        ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assert(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesIngredientsOrderTest() {
        Burger burger = new Burger();
        ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        secondIngredient = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(1,0);

        assertEquals(secondIngredient,burger.ingredients.get(0));
    }

    @Test
    public void getPriceInvokesBunGetPriceTest() {
        Burger burger=new Burger();

        burger.setBuns(bun);
        burger.getPrice();

        Mockito.verify(bun).getPrice();
    }

    @Test
    public void getPriceInvokesIngredientGetPriceTest() {
        Burger burger=new Burger();
        bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.getPrice();

        Mockito.verify(ingredient).getPrice();
    }

    @Test public void getPriceReturnsPriceTest() {
        Burger burger=new Burger();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        assertEquals(300f, burger.getPrice(),0);
    }

    @Test
    public void getReceiptInvokesBunGetName2TimesTest () {
        Burger burger = new Burger();

        burger.setBuns(bun);
        burger.getReceipt();

        Mockito.verify(bun,Mockito.times(2)).getName();
    }

    @Test
    public void getReceiptInvokesIngredientGetTypeForEachIngredientTest() {
        Burger burger = new Burger();
        bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        burger.getReceipt();

        Mockito.verify(ingredient,Mockito.times(3)).getType();
    }

    @Test
    public void getReceiptInvokesIngredientGetNameForEachIngredientTest() {
        Burger burger = new Burger();
        bun = new Bun("black bun", 100);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        Mockito.when(ingredient.getName()).thenReturn("cutlet");
        burger.getReceipt();

        Mockito.verify(ingredient,Mockito.times(3)).getName();
    }

    @Test
    public void getReceiptReturnsReceiptTest() {
        Burger burger = new Burger();
        bun = new Bun("black bun", 100);
        ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        secondIngredient = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        ingredients = new ArrayList<>();

        burger.setBuns(bun);
        ingredients.add(ingredient);
        ingredients.add(secondIngredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        StringBuilder receiptTest = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        for (Ingredient ingredient : ingredients) {
            receiptTest.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receiptTest.append(String.format("(==== %s ====)%n", bun.getName()));
        receiptTest.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals(receiptTest.toString(), burger.getReceipt());
    }
}
