package tests;

import org.junit.Test;
import pages.LoginPage;
import pages.ProgramsPage;

import static com.codeborne.selenide.Selenide.page;

public class DuplicateAndDeleteProgramsTest extends TestBase {
  @Test
  public void dliProgramShouldBeDuplicatedAndDeleted() {
    LoginPage loginPage = page(LoginPage.class);
    ProgramsPage programsPage = page(ProgramsPage.class);

    String dliProgramName = "AutomationDLI" + " " + Math.floor((Math.random() * 10000) + 1);
    String copyOfDliProgramName = "Copy of " + dliProgramName;

    loginPage.loginAs("mikhail.siekhin", "Password1");

    programsPage.openProgramsPage();
    programsPage.createDliProgram(dliProgramName);
    programsPage.verifyProgramIsCreated(dliProgramName);
    programsPage.duplicateProgram(dliProgramName);
    programsPage.deleteProgram(dliProgramName);
    programsPage.verifyProgramIsCreated(copyOfDliProgramName);
    programsPage.deleteProgram(copyOfDliProgramName);
  }
}
