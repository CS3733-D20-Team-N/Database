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
            + "m_Id INT NOT NULL REFERENCES Museum(Id),"
            + "Creator VARCHAR(255) NOT NULL,"
            + "PRIMARY Key(p_Id))";
    s_object.execute(Query);
    System.out.println("Painting Table Created");


    Query = "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES ('Smithsonian', '866-868-7774', 4)";

    Query = "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES ('American Museum of Natural History', '212-769-5151', 4)";

    Query = "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES ('Metropolitan Museum of Art', '212-535-7710', 4)";

    Query = "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES ('Louvre', '014-020-5104', 4)";

    Query = "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES ('Guggenheim', '212-423-4355', 4)";

    Query = "INSERT INTO Painting (Title, m_Id, Creator) VALUES" +
            "('Obama', 2, 'Amit Shimoni')," +
            "('Bold and Brash', 3, 'Squidward Tentacles')," +
            "('Painting L', 5, 'Artist')," +
            "('Starry Night', 1, 'Vincent Van Gogh')," +
            "('Sunflowers', 4, 'Vincent Van Gogh')," +
            "('X', 3, 'The Artist')," +
            "('Lights in the Night', 2, 'Leonid Afremov')," +
            "('The Moment of Love', 1, 'Leonid Afremov')";
    s_object.execute(Query);
    System.out.println("Nick's Insert Statement Worked");

    Query = "INSERT INTO Painting(" +
            "Title, p_Id, m_Id, Creator) VALUES" +
            "('Chris', 19, , 'Chris')" +
            "('Noah', 20, , 'Noah')" +
            "('Nick P', 21, , 'Nick P')" +
            "('Annie', 22, , 'Annie')" +
            "('Ivan', 23, , 'Ivan')" +
            "('Evan', 24, , 'Evan')" +
            "('Nick W', 25, , 'Nick W')" +
            "('Michael', 26, , 'Michael')";
    s_object.execute(Query);
    System.out.println("Chris's Insert Statement Worked");

  }
}
