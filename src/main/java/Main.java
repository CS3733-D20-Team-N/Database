import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) throws Exception {
    String Query;
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    String URL = "jdbc:derby:memory:MuseumDB;create=true";

    Connection c_object;
    c_object = DriverManager.getConnection(URL);

    Statement s_object;
    s_object = c_object.createStatement();

    Query =
        "CREATE TABLE Museum ("
            + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
            + "Name VARCHAR(255) NOT NULL,"
            + "PhoneNum VARCHAR(10) NOT NULL,"
            + "NumPainting INT NOT NULL,"
            + "PRIMARY Key (Id))";
    s_object.execute(Query);
    System.out.println("Museum Table Created");

    Query =
        "CREATE  TABLE Painting("
            + "Title VARCHAR(255) NOT NULL,"
            + "p_Id INT NOT NULL  GENERATED ALWAYS AS IDENTITY,"
            + "m_ID INT NOT NULL REFERENCES Museum(Id),"
            + "Creator VARCHAR(255) NOT NULL,"
            + "PRIMARY Key(p_Id))";
    s_object.execute(Query);
    System.out.println("Painting Table Created");

    Query = "INSERT INTO Painting VALUES ('Obama', 2, 'Amit Shimoni');" +
            "INSERT INTO Painting VALUES ('Bold and Brash', 3, 'Squidward Tentacles');" +
            "INSERT INTO Painting VALUES ('Painting L', 5, 'Artist');" +
            "INSERT INTO Painting VALUES ('Starry Night', 1, 'Vincent Van Gogh');" +
            "INSERT INTO Painting VALUES ('Sunflowers', 4, 'Vincent Van Gogh');" +
            "INSERT INTO Painting VALUES ('X', 3, 'The Artist');" +
            "INSERT INTO Painting VALUES ('Lights in the Night', 2, 'Leonid Afremov');" +
            "INSERT INTO Painting VALUES ('The Moment of Love', 1, 'Leonid Afremov');";
  }
}
