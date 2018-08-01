package tests;

import org.junit.Test;
import pages.LoginPage;
import pages.ProgramsPage;

import static com.codeborne.selenide.Selenide.page;

public class CreateAndDeleteProgramsTest extends TestBase {
  @Test
  public void dliProgramShouldBeCreatedAndDeleted() {
    LoginPage loginPage = page(LoginPage.class);
    ProgramsPage programsPage = page(ProgramsPage.class);

    String dliProgramName = "AutomationDLI" + " " + Math.floor((Math.random() * 10000) + 1);

    loginPage.loginAs("mikhail.siekhin", "Password1");

    programsPage.openProgramsPage();
    programsPage.createDliProgram(dliProgramName);
    programsPage.verifyProgramIsCreated(dliProgramName);
    programsPage.deleteProgram(dliProgramName);
  }
}