package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Описание главного меню сайта Сбербанка
 */
public class MenuPage extends BasePage {

    @FindBy(xpath = ".//div[contains(@class, 'header_more_nav')]//ul[@role = 'navigation']/li")
    List<WebElement> mainMenuOptions; // Список разделов меню


    /**
     * Метод выбирает заданный раздел сайта
     *
     * @param optionName Название раздела сайта
     *
     * @return Элемент заданного раздела сайта
     */
    public WebElement selectMainMenuOption(String optionName) {
        for (WebElement option : mainMenuOptions) {
            String actualOptionName = option
                    .findElement(By.xpath(".//a[contains(@aria-label, 'Раздел')]"))
                    .getAttribute("aria-label")
                    .toLowerCase().replaceAll("\\s+", " ");

            if (actualOptionName.contains(optionName.toLowerCase())) {
                Actions actions = new Actions(driver);
                actions.moveToElement(option).build().perform();
                return option;
            }
        }

        Assert.fail("Не найден раздел меню \"" + optionName + "\"");
        return null;
    }

    /**
     * Метод выбирает заданную услугу
     *
     * @param section Раздел, в котором ищется услуга
     * @param optionName Название услуги
     */
    public void selectSubMenuOption(WebElement section, String optionName) {
        List<WebElement> subMenuOptions = section.findElements(By.xpath
                (".//div[@class = 'alt-menu-collapser__hidder']//a"));

        selectCollectionItem(optionName, subMenuOptions);
    }
}
