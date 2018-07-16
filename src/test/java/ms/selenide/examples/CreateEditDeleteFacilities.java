package ms.selenide.examples;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.page;

public class CreateEditDeleteFacilities extends TestBase {
  @Test
  public void facilityShouldBeCreated() {
    LoginPage loginPage = page(LoginPage.class);
    FacilitiesPage facilitiesPage = page(FacilitiesPage.class);

    String facilityName = "AutomationFacility" + " " + Math.floor((Math.random() * 10000) + 1);
    String newFacilityName = "AutomationFacilityNew" + " " + Math.floor((Math.random() * 10000) + 1);

    loginPage.loginAs("mikhail.siekhin","Password1");

    facilitiesPage.openFacilitiesPage();
    facilitiesPage.addFacility(facilityName);
    facilitiesPage.editFacility(facilityName, newFacilityName);
    facilitiesPage.deleteFacility(newFacilityName);
  }
}
