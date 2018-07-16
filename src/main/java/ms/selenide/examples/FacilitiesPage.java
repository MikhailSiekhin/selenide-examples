package ms.selenide.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class FacilitiesPage {

  @FindBy(css = "a[href='/#!/facilities']")
  public SelenideElement facilitiesLink;

  @FindBy(css = "button[ng-click='addFacilityModal()']")
  public SelenideElement addFacilityButton;

  @FindBy(id = "facilname")
  public SelenideElement facilityNameInput;

  @FindBy(css = "button[ng-click='ok()']")
  public SelenideElement okButton;

  @FindBy(css = "table > tbody > tr > td")
  public SelenideElement tableList;

  public void openFacilitiesPage(){
    facilitiesLink.click();
  }

  public void verifyFacilitiesTableIsVisible() {
    tableList.shouldBe(Condition.visible);
    tableList.shouldNotHave(Condition.text("Please wait..."));
  }

  public void addFacility(String facilityName){
    addFacilityButton.click();
    facilityNameInput.sendKeys(facilityName);
    okButton.shouldNotHave(attribute("disabled"));
    okButton.shouldBe(visible);
    okButton.click();
    refresh();
    verifyFacilitiesTableIsVisible();
  }

  public void deleteFacility(String facilityName){
    verifyFacilitiesTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(facilityName)) {
          SelenideElement deleteFacilityButton = rowsTable.get(row).$("td:nth-child(3) " +
                  "> button[class=' delete-icon btn btn-xs']");
          deleteFacilityButton.shouldBe(Condition.present);
          deleteFacilityButton.click();
          okButton.shouldBe(Condition.present);
          okButton.click();
          System.out.println("Facility - " + celltext + " is deleted");
          SelenideElement checker = rowsTable.get(row).$("td:nth-child(1)");
          checker.shouldNotHave(Condition.exactText(facilityName));
        }
      }
    }
    verifyFacilitiesTableIsVisible();
  }

  public void editFacility(String facilityName, String newFacilityName){
    verifyFacilitiesTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(facilityName)) {
          SelenideElement editFacilityButton = rowsTable.get(row).$("td:nth-child(3) " +
                  "> button[class='edit-icon btn btn-primary btn-xs']");
          editFacilityButton.shouldBe(Condition.present);
          editFacilityButton.click();
          facilityNameInput.clear();
          facilityNameInput.sendKeys(newFacilityName);
          okButton.shouldNotHave(attribute("disabled"));
          okButton.shouldBe(visible);
          okButton.click();
          System.out.println("Facility - " + celltext + " is edited");
          SelenideElement checker = rowsTable.get(row).$("td:nth-child(1)");
          checker.shouldHave(exactText(newFacilityName));
        }
      }
    }
    verifyFacilitiesTableIsVisible();
  }

}
