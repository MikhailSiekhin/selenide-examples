package ms.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @FindBy(css = "#loginusername" )
    public SelenideElement loginField;

    public void loginAs(String emailInput, String passwordInput){
        loginField.setValue(emailInput);
        $("#loginpassword").setValue(passwordInput);
        $("#loginButton").click();
    }
}
