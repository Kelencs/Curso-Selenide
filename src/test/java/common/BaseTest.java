package common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;
import pages.SideBar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    protected static LoginPage login;
    protected static SideBar side;
    protected static MoviePage movie;

    @BeforeMethod
    public void setup(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://ninjaplus-web:5000";
        Configuration.timeout = 30000;
        movie = new MoviePage();
        side = new SideBar();
        login = new LoginPage();

    }
    @AfterMethod
    public void finish(){
        String tempShot = screenshot("temp_shot");
    }
}
