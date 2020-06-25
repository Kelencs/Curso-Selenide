package tests;


import common.BaseTest;
import libs.DataBase;
import models.MovieModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MovieTests extends BaseTest {

    private DataBase db;


    @BeforeMethod
    public void setup() {

        login
                .open()
                .with("kelen@ninjaplus.com", "kcs142536");


        side.loggedUser().shouldHave(text("Kelen"));

    }

    @BeforeSuite
    public void delorean(){
        db = new DataBase();
        db.resetMovies();
    }

    @Test
    public void shoudlRegisterANewMovie() {


        MovieModel movieData = new MovieModel(
                "Jumanji - Proxima fase",
                "Pré-venda",
                2021,
                "16/01/2021",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny Devito"),
                "Tentando a revisitar o mundo de Jumanji, Spencer decide"
                        + "consertar o bug no jogo do game que permite que sejam transportador ao local",
                "poster-de-jumanji.png"


                );


        db.deleteMovie(movieData.title);

        movie
                .add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }
    @Test
    public  void shouldSearchTwoMovies(){

        db.resetMovies();
        movie.search("Batman").items().shouldHaveSize(2);
    }
    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
