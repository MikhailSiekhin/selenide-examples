package ms.selenide.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class ProgramsPage extends MainPage {

  @FindBy(css = "a[href='/#!/programs']")
  public SelenideElement programsLink;

  @FindBy(css = "button[ng-click='addProgramChoiceModal()']")
  public SelenideElement addProgramButton;

  @FindBy(css = "div[class='col-xs-12 col-sm-4 program-type text-left']")
  public SelenideElement dliProgramType;

  @FindBy(css = "div[class=col-xs-12 null col-sm-4 program-type text-left']")
  public SelenideElement spectralProgramType;

  @FindBy(css = "button[ng-click='save()']")
  public SelenideElement applyProgramButton;

  @FindBy(id = "name")
  public SelenideElement programNameInput;

  @FindBy(css = "button[class='btn dli-program__button save']")
  public SelenideElement saveButton;

  @FindBy(css = "button[ng-click='ok()']")
  public SelenideElement okButton;

  public void openProgramsPage() {
    programsLink.click();
  }

  public void createDliProgram(String name) {
    addProgramButton.click();
    dliProgramType.click();
    applyProgramButton.click();
    programNameInput.clear();
    programNameInput.sendKeys(name);
    saveButton.shouldNotHave(Condition.attribute("disabled"));
    saveButton.click();
    verifyTableIsVisible();
  }

  public void createSpectralProgram() {
    addProgramButton.click();

  }

  public void verifyProgramIsCreated(String programName) {
    verifyTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(programName)) {
          System.out.println("Program - " + celltext + " is created");
        }
      }
    }
  }

  public void deleteProgram(String programName) {
    verifyTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columnsCount = ColumnsRow.size();
      for (int column = 0; column < columnsCount; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(programName)) {
          SelenideElement deleteProgramButton = rowsTable.get(row).$("td:nth-child(4) " +
                  "> button[class='delete-icon btn btn-xs btn-error']");
          deleteProgramButton.shouldBe(Condition.present);
          deleteProgramButton.click();
          okButton.shouldBe(Condition.present);
          okButton.click();
          System.out.println("Program - " + celltext + " is deleted");
          SelenideElement checker = rowsTable.get(row).$("td:nth-child(1)");
          checker.shouldNotHave(Condition.exactText(programName));
        }
      }
    }
    verifyTableIsVisible();
  }

  public void duplicateProgram(String programName) {
    verifyTableIsVisible();
    SelenideElement table = $(".table");
    ElementsCollection rowsTable = table.$$("tr.ng-scope");
    int rowsCount = rowsTable.size();
    for (int row = 0; row < rowsCount; row++) {
      ElementsCollection ColumnsRow = rowsTable.get(row).$$("td:nth-child(1)");
      int columns_count = ColumnsRow.size();
      for (int column = 0; column < columns_count; column++) {
        String celltext = ColumnsRow.get(column).getText();
        if (celltext.equals(programName)) {
          SelenideElement duplicateProgramButton = rowsTable.get(row).$("td:nth-child(4) " +
                  "> button[class='document-icon btn btn-xs']");
          duplicateProgramButton.click();
          okButton.click();
          System.out.println("Program - " + celltext + " is duplicated");
        }
      }
    }
    verifyTableIsVisible();
  }

  public boolean retryingFindClick(By by) {
    boolean result = false;
    int attempts = 0;
    while (attempts < 3) {
      try {
        $(by).click();
        result = true;
        break;
      } catch (StaleElementReferenceException e) {
      }
      attempts++;
    }
    return result;
  }
}
