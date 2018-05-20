package Pages;

import Steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Описание страницы ипотеки
 */
public class MortgageOptionsPage extends BasePage {
    @FindBy(xpath = ".//a[contains(@aria-label, 'Расчет ипотечного кредита')]")
    WebElement calculateMortgageButton; // Кнопка перехода к расчету ипотечного кредита


    /**
     * Метод перехода к расчету ипотечного кредита
     */
    public void goToCalculatorPage() {
        scrollToElement(calculateMortgageButton);
        try {
            clickWithNewPage(calculateMortgageButton);
        } catch (Exception e) {
            BaseSteps.closePopup();
            clickWithNewPage(calculateMortgageButton);
        }
    }
}
