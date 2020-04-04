import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

  private static Statement s_object;

  public static void main(String[] args) throws Exception {
    String Query;
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    String URL = "jdbc:derby:memory:MuseumDB;create=true";

    Connection c_object;
    c_object = DriverManager.getConnection(URL);

    s_object = c_object.createStatement();

    Query =
        "CREATE TABLE Museum ("
            + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
            + "Name VARCHAR(255) NOT NULL,"
            + "PhoneNum VARCHAR(20) NOT NULL,"
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

    Query =
        "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES "
            + "('Smithsonian', '866-868-7774', 4),"
            + "('American Museum of Natural History', '212-769-5151', 4),"
            + "('Metropolitan Museum of Art', '212-535-7710', 4),"
            + "('Louvre', '014-020-5104', 4),"
            + "('Guggenheim', '212-423-4355', 4)";

    s_object.execute(Query);
    Query = "SELECT Id FROM Museum";
    ResultSet rs = s_object.executeQuery(Query);
    while (rs.next()) {
      System.out.println(rs.getInt("Id"));
    }
    Query =
        "INSERT INTO Painting (Title, m_Id, Creator) VALUES"
            + "('Obama', 1, 'Amit Shimoni'),"
            + "('Bold and Brash', 2, 'Squidward Tentacles'),"
            + "('Painting L', 3, 'Artist'),"
            + "('Starry Night', 4, 'Vincent Van Gogh'),"
            + "('Sunflowers', 5, 'Vincent Van Gogh'),"
            + "('X', 1, 'The Artist'),"
            + "('Lights in the Night', 2, 'Leonid Afremov'),"
            + "('The Moment of Love', 3, 'Leonid Afremov')";
    s_object.execute(Query);
    System.out.println("Nick's Insert Statement Worked");

    Query =
        "INSERT INTO Painting("
            + "Title, m_Id, Creator) VALUES"
            + "('Chris', 4, 'Chris'),"
            + "('Noah', 5, 'Noah'),"
            + "('Nick P', 1, 'Nick P'),"
            + "('Annie', 2, 'Annie'),"
            + "('Ivan', 3 , 'Ivan'),"
            + "('Evan', 4 , 'Evan'),"
            + "('Nick W', 5 , 'Nick W'),"
            + "('Michael', 1 , 'Michael')";
    s_object.execute(Query);
    System.out.println("Chris's Insert Statement Worked");

    Query =
        "INSERT INTO Painting("
            + "Title, m_Id, Creator) VALUES"
            + "('Mona Lisa', 2, 'Leonardo Da Vinci'),"
            + "('The Last Supper', 3, 'Vincent Van Gogh'),"
            + "('Girl with a Pearl Earring', 4, 'Johannes Vermeer'),"
            + "('The Kiss', 5, 'Gustav Klimt'),"
            + "('Cafe Terrace at Night', 1 , 'Vincent van Gogh'),"
            + "('American Gothic', 2 , 'Grant Wood'),"
            + "('Guernica', 3 , 'Pablo Picasso'),"
            + "('The Birth of Venus', 4 , 'Sandro Botticelli'),"
            + "('The Night Watch', 5 , 'Rembrandt')";
    s_object.execute(Query);
    System.out.println("Noah's Inserts");
  }

  private static void paintings(){
    String paintQuery = "SELECT * FROM Painting";
    //ResultSet rs =
  }

  private static void museumInfo() throws Exception {
    String museumQuery = "SELECT * FROM Museum";
    ResultSet rs = s_object.executeQuery(museumQuery);
    while (rs.next()) {
      System.out.println("Id: " + rs.getString("Id"));
      System.out.println("Name: " + rs.getString("Name"));
      System.out.println("Phone Number: " + rs.getString("PhoneNum"));
      System.out.println("Number of Paintings: " + rs.getString("NumPainting"));
      System.out.println(" ");
    }
  }

  private void updatePhoneNumber(){
    Scanner inp = new Scanner(System.in);
    System.out.println("Enter the name of the museum you would like to change: ");
    String museum = inp.next();
    System.out.println("Enter the new phone number with dashes: ");
    String num = inp.next();

    String q = "UPDATE Museum";

  }
}
