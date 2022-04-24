import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepTest {

    private static final String REPOSITORY = "DmitriyDmitrishin/qa_guru_12_6_HW";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void testGitHubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com/");
        });
        step("Search repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Go to repository link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Click on tab Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Check Issue number" + ISSUE_NUMBER, () -> {
            $(withText("#1")).shouldBe(Condition.visible);
        });
    }
}
