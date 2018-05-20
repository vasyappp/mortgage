package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Описание раздела результатов расчетов калькулятора
 */
public class CalculatorResultsPage extends BasePage {
    // Путь к разделу расчетов калькулятора
    static final String resultsXpath =
            ".//div[contains(@class, 'sidebar')]//div[@class = 'dcCalc_calcResult_layout']";


    @FindBy(xpath = resultsXpath + "//span[@data-test-id = 'amountOfCredit']")
    WebElement amountOfCredit; // Рассчитанная сумма кредита

    @FindBy(xpath = resultsXpath + "//span[@data-test-id = 'monthlyPayment']")
    WebElement monthlyPayment; // Рассчитанный ежемесячный платеж

    @FindBy(xpath = resultsXpath + "//span[@data-test-id = 'requiredIncome']")
    WebElement requiredIncome; // Рассчитанный необходимый доход

    @FindBy(xpath = resultsXpath + "//span[@data-test-id = 'rate']")
    WebElement rate; // Рассчитанная процентная ставка


    /**
     * Метод получает рассчитанное значение заданного поля
     *
     * @param fieldName Поле для получения результата
     *
     * @return Рассчитанный результат
     */
    public String getResult(String fieldName) {
        String result = "tmp";

        switch (fieldName) {
            case "Сумма кредита":
                scrollToElement(amountOfCredit);
                result = amountOfCredit.getText();
                break;
            case "Ежемесячный платеж":
                scrollToElement(monthlyPayment);
                result = monthlyPayment.getText();
                break;
            case "Процентная ставка":
                scrollToElement(rate);
                result = rate.getText();
                break;
            case "Необходимый доход":
                scrollToElement(requiredIncome);
                result = requiredIncome.getText();
                break;
            default:
                Assert.fail("Неправильное поле результата \"" + fieldName + "\"");
        }

        return result.substring(0, result.length() - 2);
    }
}
