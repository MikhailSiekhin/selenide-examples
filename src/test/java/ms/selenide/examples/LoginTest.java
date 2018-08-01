package ms.selenide.examples;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.page;


public class LoginTest extends TestBase {
    @Test
    public void userCanLogin(){
       LoginPage loginPage = page(LoginPage.class);
       ZonesPage zonesPage = page(ZonesPage.class);

       loginPage.loginAs("mikhail.siekhin", "Password1");

       zonesPage.getMqttState();
       zonesPage.getCopyrightInfo();
    }
}
