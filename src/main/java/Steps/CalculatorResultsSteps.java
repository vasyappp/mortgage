package Steps;

import Pages.CalculatorResultsPage;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Описание шагов для раздела результатов калькулятора
 */
public class CalculatorResultsSteps {

    @Step("вычисленное значение поля \"{0}\" равно заданному: {1}")
    public void stepCheckResult(String fieldName, String expectedResult) {
        WebDriverWait wait = new WebDriverWait(BaseSteps.getDriver(), 10);

        Assert.assertTrue(String.format
                        ("В поле %s рассчитано значение %s. Ожидалось - %s", fieldName,
                                new CalculatorResultsPage().getResult(fieldName), expectedResult),
                wait.until((ExpectedCondition<Boolean>) driver -> {
                    String actualResult = new CalculatorResultsPage().getResult(fieldName);
                    return actualResult.equalsIgnoreCase(expectedResult);
                }));
    }
}
