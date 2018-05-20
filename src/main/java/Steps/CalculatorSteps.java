package Steps;

import Pages.CalculatorPage;
import Utils.Stash;
import io.qameta.allure.Step;
import org.junit.Assert;

/**
 * Описание шагов для раздела калькулятора
 */
public class CalculatorSteps {

    @Step("заголовок страницы равен \"{0}\"")
    public void stepCheckTitle(String expectedTitle) {
        String actualTitle = new CalculatorPage().getTitle().toLowerCase();

        String[] expectedTitleWords = expectedTitle.toLowerCase().split(" ");

        for (String word : expectedTitleWords) {
            if (!actualTitle.contains(word))
                Assert.fail(String.format("Заголовок страницы равен \"%s\". Ожидалось - \"%s\"", actualTitle, expectedTitle));
        }
    }

    @Step("в поле \"{0}\" введено значение: {1}")
    public void stepFillFields(String fieldName, String value) {
        new CalculatorPage().fillFields(fieldName, value);
    }

    @Step("выставлен чекбокс \"{0}\"")
    public void stepSetDiscount(String discountName) {
        Stash.getInstance().putDiscountName(discountName);
    }

    @Step("сняты остальные чекбоксы")
    public void stepDisableDiscounts() {
        new CalculatorPage().disableDiscounts(Stash.getInstance().getDiscountNames());
    }
}
