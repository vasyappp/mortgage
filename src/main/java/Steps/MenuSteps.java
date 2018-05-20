package Steps;

import Pages.MenuPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

/**
 * Описание шагов для главного меню сайта Сбербанка
 */
public class MenuSteps {

    @Step("выбран раздел меню \"{0}\" и услуга \"{1}\"")
    public void stepSelectMainMenuOption(String mainMenuOption, String subMenuOption) {
        MenuPage menuPage = new MenuPage();

        WebElement section = menuPage.selectMainMenuOption(mainMenuOption);
        menuPage.selectSubMenuOption(section, subMenuOption);
    }
}
