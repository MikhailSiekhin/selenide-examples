package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

  @FindBy(css = "table > tbody > tr > td")
  public SelenideElement tableList;

  public void verifyTableIsVisible() {
    tableList.shouldBe(Condition.visible);
    tableList.shouldNotHave(Condition.text("Please wait..."));
  }
}
