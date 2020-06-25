package libs;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.nio.file.Files.readAllBytes;

public class DataBase {
    private final String url = "jdbc:postgresql://pgdb/ninjaplus";
    private final String user = "postgres";
    private final String pass = "qaninja";

    public Connection connect() throws SQLException {
      return  DriverManager.getConnection(url,user,pass);
    }

    public  void resetMovies(){
        String  executionPath = System.getProperty("user.dir");
        String os = System.getProperty("os.name");

        String target;

        if(os.contains("Windows")){
            target = executionPath + " \\src\\main\\resources\\sql\\movies.sql";
        }else{
            target = executionPath + "/src/main/resources/sql/movies.sql";
        }

        String moviesSQL ;

        try{
            moviesSQL = new String(readAllBytes(Paths.get(target)));
            PreparedStatement query = this.connect().prepareStatement(moviesSQL);
            query.executeQuery();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteMovie(String  title)  {
        String SQL = "deletee from public.movies where title = ?;";

        try {
            PreparedStatement query = this.connect().prepareStatement(SQL);
            query.setString(1, title);
            query.executeQuery();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }


    }
}






















