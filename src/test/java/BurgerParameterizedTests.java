import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    @Mock
    static Bun bun;
    @Mock
    static Ingredient firstIngredient;
    @Mock
    static Ingredient secondIngredient;

    private float expectedPrice;
    private float ingredientPrice;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

   public BurgerParameterizedTests ( float expectedPrice, float ingredientPrice) {
       this.expectedPrice=expectedPrice;
       this.ingredientPrice=ingredientPrice;
    }

    @Parameterized.Parameters (name="Тестовые данные:{0},{1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {400,100},
                {800,200},
                {1200,300},
        };
    }

    @Test
    public void getPriceReturnsCorrectPriceTest () {
       Burger burger=new Burger();

       Mockito.when(bun.getPrice()).thenReturn(ingredientPrice);
       Mockito.when(firstIngredient.getPrice()).thenReturn(ingredientPrice);
       Mockito.when(secondIngredient.getPrice()).thenReturn(ingredientPrice);

       burger.setBuns(bun);
       burger.addIngredient(firstIngredient);
       burger.addIngredient(secondIngredient);

       assertEquals(expectedPrice,burger.getPrice(),0);
    }
}
