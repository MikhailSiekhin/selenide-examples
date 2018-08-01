package tests;

import org.junit.Test;
import pages.LoginPage;
import pages.RoomsPage;

import static com.codeborne.selenide.Selenide.page;

public class CreateEditDeleteRoomsTest extends TestBase {
  @Test
  public void roomShouldBeCreatedEditedDeleted(){
    LoginPage loginPage = page(LoginPage.class);
    RoomsPage roomsPage = page(RoomsPage.class);

    String roomName = "AutomationRoom" + " " + Math.floor((Math.random() * 10000) + 1);
    String newRoomName = "AutomationRoomNew" + " " + Math.floor((Math.random() * 10000) + 1);

    loginPage.loginAs("mikhail.siekhin", "Password1");
    roomsPage.openRoomsPage();
    roomsPage.addRoom(roomName, "lumigrow Office", "Greenhouse",
            "650LV", "Meters","300.0");
    roomsPage.editRoom(roomName, newRoomName);
    roomsPage.deleteRoom(newRoomName);
  }
}
