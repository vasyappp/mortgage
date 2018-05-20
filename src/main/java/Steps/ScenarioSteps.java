package Steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioSteps {
    MenuSteps menuSteps = new MenuSteps();
    MortgageOptionsSteps mortgageOptionsSteps = new MortgageOptionsSteps();
    CalculatorSteps calculatorSteps = new CalculatorSteps();
    CalculatorResultsSteps calculatorResultsSteps = new CalculatorResultsSteps();


    @Given("^выбран раздел меню \"(.+)\" и услуга \"(.+)\"$")
    public void selectMainMenuOption(String mainMenuOption, String subMenuOption) {
        menuSteps.stepSelectMainMenuOption(mainMenuOption, subMenuOption);
    }

    @When("^выполнен переход к расчету ипотечного кредита$")
    public void goToCalculator() {
        mortgageOptionsSteps.stepGoToCalculator();
    }

    @Then("^заголовок страницы Калькулятора равен \"(.+)\"$")
    public void checkCalculatorTitle(String expectedTitle) {
        calculatorSteps.stepCheckTitle(expectedTitle);
    }

    @When("^заполнены поля Калькулятора$")
    public void fillCalculatorFields(DataTable parameters) {
        parameters.asMap(String.class, String.class)
                .forEach((fieldName, value) -> calculatorSteps.stepFillFields(fieldName, value));
    }

    @When("^выставлены заданные чекбоксы и отключены остальные$")
    public void setCheckboxes(DataTable parameters) {
        parameters.asList(String.class)
                .forEach(discountName -> calculatorSteps.stepSaveDiscounts(discountName));
        calculatorSteps.stepSetDiscounts();
    }

    @Then("^рассчитаны значения$")
    public void checkResults(DataTable results) {
        results.asMap(String.class, String.class)
                .forEach((fieldName, expectedResult) -> calculatorResultsSteps.stepCheckResult(fieldName, expectedResult));
    }
}
