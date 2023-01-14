package ru.netology.page;

import org.openqa.selenium.support.FindBy;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {
    @FindBy(css = "[data-test-id=login] input")
    private SelenideElement loginField;

    @FindBy(css = "[data-test-id=password] input")
    private SelenideElement passwordField;

    @FindBy(css = "[data-test-id=action-login]")
    private SelenideElement loginButton;

    @FindBy(css = "[data-test-id=error-notification]")
    private SelenideElement errorNotification;

    public void verifyErrorNotificationVisiblity(String expectedText) {
        errorNotification.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }
}
