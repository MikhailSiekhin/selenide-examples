package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends MainPage{

    @FindBy(css = "#loginusername" )
    public SelenideElement loginField;

    @FindBy(partialLinkText =  "ZONES")
    public SelenideElement zonesLink;

    public void loginAs(String emailInput, String passwordInput){
        loginField.setValue(emailInput);
        $("#loginpassword").setValue(passwordInput);
        $("#loginButton").click();
        confirmationHeader();
    }

    public void confirmationHeader(){
        zonesLink.shouldHave(text("ZONES"));
    }

}
