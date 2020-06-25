package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginTests extends BaseTest {


    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"kelen@ninjaplus.com", "abc142536", "Usuário e/ou senha inválidos"},
                {"404@ninjaplus.com", "kcs142536", "Usuário e/ou senha inválidos"},
                {"", "abc142536", "Opps. Cadê o email?"},
                {"kelen@ninjaplus.com", "", "Opps. Cadê o senha?"},
        };
    }


    @Test
    public void shouldSeeLoggedUser() {

        login
                .open()
                .with("kelen@ninjaplus.com", "kcs142536");


        side.loggedUser().shouldHave(text("Kelen"));
    }

    @Test(dataProvider = "login-alerts")
    public void shoulSeeLoginAlerts(String email, String pass, String expectAlert) {

        login
                .open()
                .with(email, pass);

        $(".alert span").shouldHave(text(expectAlert));
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }

}


