package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    @BeforeClass
    public static void setUp(){
        //System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.headless = true;
        open("https://two.dev.lumigrow.com/");
    }

    @AfterClass
    public static void tearDown(){
        WebDriverRunner.closeWebDriver();
    }
}