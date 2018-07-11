package ms.selenide.examples;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/user/selenide-example/src/main/resources/chromedriver");
        Configuration.browser = "chrome";
        Configuration.headless = true;
        open("https://two.dev.lumigrow.com/");
    }
}
