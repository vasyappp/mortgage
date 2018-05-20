package Pages;

import Steps.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Описание базовой страницы и методов, общих для всех страниц
 */
public class BasePage {
    WebDriver driver = BaseSteps.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод прокручивает страницу до заданного элемента
     *
     * @param element Заданный элемент
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /**
     * Метод заполняет поле заданным значением
     *
     * @param field Поле для заполнения
     * @param value Значение
     */
    public void fillField(WebElement field, String value) {
        field.click();
        field.clear();
        field.sendKeys(value);
        field.sendKeys(Keys.ENTER);
    }

    /**
     * Метод выбирает из коллекции элемент, содержащий заданный текст
     *
     * @param itemName Заданный текст
     * @param collection Коллекция элементов
     */
    public void selectCollectionItem(String itemName, List<WebElement> collection) {
        for (WebElement item : collection) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                scrollToElement(item);
                item.click();

                return;
            }
        }
        Assert.fail("Не найден элемент коллекции - " + itemName);
    }

    /**
     * Метод ожидания отображения элемента на странице
     *
     * @param element Искомый элемент
     * @param time Время ожидания
     */
    public void waitVisibility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Метод ожидания отображения элемента на странице
     *
     * @param element Искомый элемент
     */
    public void waitVisibility(WebElement element) {
        waitVisibility(element, 10);
    }

    public boolean isElementPresent(WebElement element) {
        WebDriver driver = BaseSteps.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    /**
     * Метод кликает по элементу, открывающему новую вкладку браузера, и переходит на эту вкладку
     *
     * @param element Элемент для клика
     */
    public void clickWithNewPage(WebElement element) {
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        element.click();

        String newWindow = wait
                .until((ExpectedCondition<String>) driver -> {
                            Set<String> newWindowsSet = driver.getWindowHandles();
                            newWindowsSet.removeAll(oldWindowsSet);

                            return newWindowsSet.size() > 0 ?
                                    newWindowsSet.iterator().next() : null;
                        }
                );

        driver.switchTo().window(newWindow);
    }

    /**
     * Метод вводит указанное значение в поле с автозаполнением
     *
     * @param field Поле для ввода данных
     * @param value Значение
     */
    public void fillChangingField(WebElement field, String value) {
        waitToBeClickable(field);

        wait.until((ExpectedCondition<Boolean>) driver -> {
            field.clear();
            field.click();
            field.sendKeys(value);

            String actualValue = field.getAttribute("value");

            if (field.getAttribute("value").contains("\u20BD"))
                actualValue = actualValue.substring(0, actualValue.length() - 2);
            else
                actualValue = actualValue.substring(0, actualValue.length() - 4);

            return actualValue.equalsIgnoreCase(value);
        });
    }

    /**
     * Метод ожидает, когда элемент станет кликабельным
     *
     * @param element Элемент
     */
    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
