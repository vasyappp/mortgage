package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Описание страницы калькулятора ипотеки
 */
public class CalculatorPage extends BasePage {
    // Путь к разделу калькулятора на странице
    static final String calculatorXpath = ".//div[@class = 'dcCalc_frame']";
    //Пути к иконкам чекбокса
    static final String discountIconOnXpath = ".//span[@class = 'dcCalc_switch__icon-on']";
    static final String discountIconOffXpath = ".//span[@class = 'dcCalc_switch__icon-off']";


    @FindBy(xpath = ".//h1")
    WebElement title; // Заголовок страницы

    @FindBy(xpath = calculatorXpath +
            "//div[contains(text(), 'Цель кредита')]/following-sibling::div//input[@class = 'dcCalc_textfield__input']")
    WebElement purposeOptionsButton; // Кнопка выбора опций цели кредита

    @FindBy(xpath = ".//div[@class = 'Select-menu']/div[@role = 'option']")
    List<WebElement> purposeOptions; // Опции цели кредита

    @FindBy(xpath = calculatorXpath + "//input[@id = 'estateCost']")
    WebElement estateCostInput; // Поле для ввода стоимости недвижимости

    @FindBy(xpath = calculatorXpath + "//input[@id = 'initialFee']")
    WebElement initialFeeInput; // Поле для ввода первоначального взноса

    @FindBy(xpath = calculatorXpath + "//input[@id = 'creditTerm']")
    WebElement creditTermInput; // Поле для ввода срока кредитования

    @FindBy(xpath = calculatorXpath + "//div[@class = 'dcCalc_frame__discounts']/div")
    List<WebElement> discounts; // Чекбоксы калькулятора


    /**
     * Метод возвращает заголовок страницы
     *
     * @return Заголовок страницы
     */
    public String getTitle() {
        waitVisibility(title);
        scrollToElement(title);

        return title.getText();
    }

    /**
     * Метод выбирает заданную цель кредита
     *
     * @param purposeName Цель кредита
     */
    public void selectPurpose(String purposeName) {
        waitToBeClickable(purposeOptionsButton);
        scrollToElement(purposeOptionsButton);

        purposeOptionsButton.click();

        waitVisibility(purposeOptions.get(0));

        for (WebElement purpose : purposeOptions) {
            if (purpose.getText().equalsIgnoreCase(purposeName)) {
                purpose.click();
                return;
            }
        }
    }

    /**
     * Метод заполняет поле калькулятора
     *
     * @param fieldName Поле для заполнения
     * @param value Значение
     */
    public void fillFields(String fieldName, String value) {
        switch (fieldName) {
            case "Цель кредита":
                selectPurpose(value);
                break;
            case "Стоимость недвижимости":
                scrollToElement(estateCostInput);
                fillChangingField(estateCostInput, value);
                break;
            case "Первоначальный взнос":
                scrollToElement(initialFeeInput);
                fillChangingField(initialFeeInput, value);
                break;
            case "Срок кредитования":
                scrollToElement(creditTermInput);
                fillChangingField(creditTermInput, value.substring(0, value.length() - 4));
                break;
            default:
                Assert.fail("Неправильное имя поля для заполнения \"" + fieldName + "\"");
        }
    }

    /**
     * Метод возвращает название чекбокса
     *
     * @param discount Чекбокс для получения его названия
     *
     * @return Название чекбокса
     */
    private String getDiscountTitle(WebElement discount) {
        return discount.findElement(By.xpath(".//div[contains(@class, 'title')]")).getText();
    }

    /**
     * Метод выставляет чекбоксы в зависимости от того, должны ли они быть включены
     *
     * @param discountNames Названия чекбоксов, которые должны быть включенными
     */
    public void setDiscounts(List<String> discountNames) {
        for (WebElement discount : discounts) {
            scrollToElement(discount);

            if (discountNames.contains(getDiscountTitle(discount)))
                setDiscount(discount);
            else
                disableDiscount(discount);
        }
    }

    /**
     * Метод включает чекбокс
     *
     * @param discount Чекбокс
     */
    private void setDiscount(WebElement discount) {
        if (discount.findElement(By.xpath(discountIconOffXpath)).isDisplayed()) {
            discount.findElement(By.xpath(".//label")).click();
            try {
                waitVisibility(discount.findElement(By.xpath(discountIconOnXpath)));
            } catch (TimeoutException e) {
                discount.findElement(By.xpath(".//label")).click();
                waitVisibility(discount.findElement(By.xpath(discountIconOnXpath)));
            }
        }
    }

    /**
     * Метод выключает чекбокс
     *
     * @param discount Чекбокс
     */
    private void disableDiscount(WebElement discount) {
        if (discount.findElement(By.xpath(discountIconOnXpath)).isDisplayed()) {
            discount.findElement(By.xpath(".//label")).click();
            try {
                waitVisibility(discount.findElement(By.xpath(discountIconOffXpath)));
            } catch (TimeoutException e) {
                discount.findElement(By.xpath(".//label")).click();
                waitVisibility(discount.findElement(By.xpath(discountIconOffXpath)));
            }
        }
    }
}
