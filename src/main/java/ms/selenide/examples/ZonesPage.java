package ms.selenide.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class ZonesPage extends MainPage {

    @FindBy(partialLinkText =  "ZONES")
    public SelenideElement zonesLink;

    @FindBy(css = "span[ng-show=mqttConnected]")
    public SelenideElement mqttIcon;

    @FindBy(css ="span[class='version ng-binding']")
    public SelenideElement copyrightInfo;

    @FindBy(css = "button[class='add btn btn-primary']")
    public SelenideElement addZoneButton;

    @FindBy(id = "name")
    public SelenideElement zoneNameInput;

    @FindBy(id = "room")
    public SelenideElement roomDropdown;

    @FindBy(css = "button[class='btn btn-primary']")
    public SelenideElement okButton;

    public ZonesPage getMqttState(){
        mqttIcon.shouldHave(cssValue("color","rgba(0, 255, 0, 1)"));
        return this;
    }

    public ZonesPage getCopyrightInfo(){
        copyrightInfo.shouldHave(text("© 2016, 2017, 2018 LumiGrow, Inc. All Rights Reserved."));
        return this;
    }

    public void addZone(String zoneName, String roomName){
        addZoneButton.click();
        zoneNameInput.clear();
        zoneNameInput.sendKeys(zoneName);
        roomDropdown.selectOptionContainingText(roomName);
        roomDropdown.shouldBe(matchText(roomName));
        okButton.shouldNotHave(attribute("disabled"));
        okButton.shouldBe(visible);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        okButton.click();
        refresh();
    }
}