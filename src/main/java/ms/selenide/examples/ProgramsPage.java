package ms.selenide.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class ProgramsPage {

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

    @FindBy(css = "button[disabled='disabled']")
    public SelenideElement disabledApplyProgramButton;

    @FindBy(id = "name")
    public SelenideElement programNameInput;

    @FindBy(css = "button[class='btn dli-program__button save']")
    public SelenideElement saveButton;

    @FindBy(css = "table > tbody > tr > td")
    public SelenideElement tableList;

    @FindBy(css = "button[ng-click='ok()']")
    public SelenideElement okButton;

    public void openProgramsPage(){
        programsLink.click();
    }

    public void createDliProgram(String name){
        addProgramButton.click();
        dliProgramType.click();
        applyProgramButton.click();
        programNameInput.clear();
        programNameInput.sendKeys(name);
        saveButton.shouldNotHave(Condition.attribute("disabled"));
        saveButton.click();
        tableList.shouldBe(Condition.visible);
        tableList.shouldNotHave(Condition.text("Please wait..."));
    }

    public void createSpectralProgram(){
        addProgramButton.click();

    }

    public void verifyProgramIsCreated(String programName){
        SelenideElement table = $(".table");
        ElementsCollection rows_table = table.$$("tr.ng-scope");
        int rows_count = rows_table.size();
        for(int row=0; row<rows_count; row++){
            ElementsCollection Columns_row = rows_table.get(row).$$("td:nth-child(1)");
            int columns_count = Columns_row.size();
            for (int column=0; column<columns_count; column++){
                String celltext = Columns_row.get(column).getText();
                if(celltext.equals(programName)){
                    System.out.println("Program name is correct - " +celltext);
                }
            }
        }
    }

    public void deleteProgram(String programName){
        SelenideElement table = $(".table");
        ElementsCollection rows_table = table.$$("tr.ng-scope");
        int rows_count = rows_table.size();
        for(int row=0; row<rows_count; row++){
            ElementsCollection Columns_row = rows_table.get(row).$$("td:nth-child(1)");
            int columns_count = Columns_row.size();
            for (int column=0; column<columns_count; column++){
                String celltext = Columns_row.get(column).getText();
                if(celltext.equals(programName)){
                    SelenideElement deleteProgramButton = rows_table.get(row).$("td:nth-child(4) " +
                            "> button[class='delete-icon btn btn-xs btn-error']");
                    deleteProgramButton.click();
                    okButton.click();
                    System.out.println("Program - "+celltext + " is deleted");
                }
            }
        }
    }
}
