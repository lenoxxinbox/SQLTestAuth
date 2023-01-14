package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DBHelper.cleanDB;

public class AuthTest {
    @BeforeEach
    void before() {
        Configuration.holdBrowserOpen = true;
        Configuration.startMaximized = true;
    }

    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    @DisplayName("Should successfully login, password and code")
    void shouldSuccessAuth() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        verificationPage.validVerify(DBHelper.getVerificationCode());
    }

    @Test
    @DisplayName("Should not successfully with invalid login, password and code")
    void shouldNotSuccessAuth() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLogin(DataHelper.generateRandomUser());
        loginPage.verifyErrorNotificationVisiblity("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should successfully login, password and invalid code")
    void shouldAuthWithInvalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        verificationPage.invalidVerify("Неверно указан код! Попробуйте ещё раз.");
    }
}
