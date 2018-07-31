package ms.selenide.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class RoomsPage extends MainPage {
  @FindBy(css = "a[href='/#!/rooms']")
  public SelenideElement roomsLink;

  @FindBy(css = "button[ng-click='addRoomModal()']")
  public SelenideElement addRoomButton;

  @FindBy(id = "name")
  public SelenideElement roomNameInput;

  @FindBy(id = "facility")
  public SelenideElement facilityDropdown;

  @FindBy(id = "roomtype")
  public SelenideElement roomTypeDropdown;

  @FindBy(id = "fixturetype")
  public SelenideElement fixtureTypeDropdown;

  @FindBy(id = "dimUnits")
  public SelenideElement dimUnitsDropdown;

  @FindBy(id = "fixtureArea")
  public SelenideElement fixtureAreaField;

  @FindBy(css = "button[ng-click='ok()']")
  public SelenideElement okButton;

  public void openRoomsPage(){
    roomsLink.click();
  }

  public void addRoom(String roomName, String facilityName, String roomTypeName, String fixtureTypeName,
                      String dimUnitsName, String fixtureAreaNumber){
    verifyTableIsVisible();
    addRoomButton.click();
    roomNameInput.sendKeys(roomName);
    facilityDropdown.selectOptionContainingText(facilityName);
    facilityDropdown.shouldBe(matchText(facilityName));
    roomTypeDropdown.selectOptionContainingText(roomTypeName);
    roomTypeDropdown.shouldBe(matchText(roomTypeName));
    fixtureTypeDropdown.selectOptionContainingText(fixtureTypeName);
    fixtureTypeDropdown.shouldBe(matchText(fixtureTypeName));
    dimUnitsDropdown.selectOptionContainingText(dimUnitsName);
    dimUnitsDropdown.shouldBe(matchText(dimUnitsName));
    fixtureAreaField.sendKeys(fixtureAreaNumber);
    okButton.click();
    verifyTableIsVisible();
    refresh();
  }

  public void deleteRoom(String roomName){
    verifyTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(roomName)) {
          SelenideElement deleteRoomButton = rowsTable.get(row).$("td:nth-child(7) " +
                  "> button[class='delete-icon btn btn-error btn-xs']");
          deleteRoomButton.shouldBe(Condition.present);
          deleteRoomButton.click();
          okButton.shouldBe(Condition.present);
          okButton.click();
          System.out.println("Room - " + celltext + " is deleted");
          SelenideElement checker = rowsTable.get(row).$("td:nth-child(1)");
          checker.shouldNotHave(Condition.exactText(roomName));
        }
      }
    }
    verifyTableIsVisible();
  }

  public void editRoom(String roomName, String newRoomName){
    verifyTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(roomName)) {
          SelenideElement editRoomButton = rowsTable.get(row).$("td:nth-child(7) " +
                  "> button[class='edit-icon btn btn-primary btn-xs']");
          editRoomButton.shouldBe(Condition.present);
          editRoomButton.click();
          roomNameInput.clear();
          roomNameInput.sendKeys(newRoomName);
          okButton.shouldBe(Condition.present);
          okButton.click();
          System.out.println("Room - " + celltext + " is edited");
          SelenideElement checker = rowsTable.get(row).$("td:nth-child(1)");
          checker.shouldNotHave(Condition.exactText(roomName));
          checker.shouldHave(exactText(newRoomName));
        }
      }
    }
    verifyTableIsVisible();
  }

}
