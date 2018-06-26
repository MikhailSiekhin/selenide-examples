package ms.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

public class ZonesPage {

    @FindBy(partialLinkText =  "ZONES")
    public SelenideElement zonesLink;

    @FindBy(css = "span[ng-show=mqttConnected]")
    private SelenideElement mqttIcon;

    @FindBy(css ="span[class='version ng-binding']")
    private SelenideElement copyrightInfo;

    public ZonesPage confirmationHeader(){
        zonesLink.shouldHave(text("ZONES"));
        return this;
    }

    public ZonesPage getMqttState(){
        mqttIcon.shouldHave(cssValue("color","rgba(0, 255, 0, 1)"));
        return this;
    }

    public ZonesPage getCopyrightInfo(){
        copyrightInfo.shouldHave(text("© 2016, 2017, 2018 LumiGrow, Inc. All Rights Reserved."));
        return this;
    }
}
