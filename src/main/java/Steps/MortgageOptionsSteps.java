package Steps;

import Pages.MortgageOptionsPage;
import io.qameta.allure.Step;

/**
 * Описание шагов для страницы ипотеки
 */
public class MortgageOptionsSteps {

    @Step("выполнен переход к расчету ипотечного кредита")
    public void stepGoToCalculator() {
        new MortgageOptionsPage().goToCalculatorPage();
    }
}
