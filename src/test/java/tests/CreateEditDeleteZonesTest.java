package tests;

import org.junit.Test;
import pages.LoginPage;
import pages.ZonesPage;

import static com.codeborne.selenide.Selenide.page;

public class CreateEditDeleteZonesTest extends TestBase {
  @Test
  public void zoneShouldBeCreatedEditedDeleted(){
    LoginPage loginPage = page(LoginPage.class);
    ZonesPage zonesPage = page(ZonesPage.class);

    String zoneName = "AutomationZone" + " " + Math.floor((Math.random() * 10000) + 1);


    loginPage.loginAs("mikhail.siekhin", "Password1");
    zonesPage.addZone(zoneName, "QA Room");
  }
}
