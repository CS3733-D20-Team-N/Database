import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

  private static Statement s_object;

  public static void main(String[] args) throws Exception {
    if (args.length < 2 || args.length > 3) {
      System.out.println("Usage: Ndb <username> <password> <option>");
      System.exit(0);
    }
    if (args.length < 3) {
      System.out.println(
          "Options:\n1-Report Museum Information"
              + "\n2-Report Paintings in Museum"
              + "\n3-Update Museum Phone Number"
              + "\n4-Exit Program");
      System.exit(0);
    }

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
    // System.out.println("Museum Table Created");

    Query =
        "CREATE  TABLE Painting("
            + "Title VARCHAR(255) NOT NULL,"
            + "p_Id INT NOT NULL  GENERATED ALWAYS AS IDENTITY,"
            + "m_Id INT NOT NULL REFERENCES Museum(Id),"
            + "Creator VARCHAR(255) NOT NULL,"
            + "PRIMARY Key(p_Id))";
    s_object.execute(Query);
    // System.out.println("Painting Table Created");

    Query =
        "INSERT INTO Museum (Name, PhoneNum, NumPainting) VALUES "
            + "('Smithsonian', '866-868-7774', 4),"
            + "('American Museum of Natural History', '212-769-5151', 4),"
            + "('Metropolitan Museum of Art', '212-535-7710', 4),"
            + "('Louvre', '014-020-5104', 4),"
            + "('Guggenheim', '212-423-4355', 4)";

    s_object.execute(Query);
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
    // System.out.println("Nick's Insert Statement Worked");

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
    // System.out.println("Chris's Insert Statement Worked");

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
    // System.out.println("Noah's Inserts");

    // updatePhoneNumber();
    // paintings();
    // museumInfo();
    try {
      int option = Integer.parseInt(args[2]);
      switch (option) {
        case 1:
          museumInfo();
          break;
        case 2:
          paintings();
          break;
        case 3:
          updatePhoneNumber();
          break;
        case 4:
          System.exit(0);
          break;
        default:
          System.out.println(
              "Options:\n1-Report Museum Information"
                  + "\n2-Report Paintings in Museum"
                  + "\n3-Update Museum Phone Number"
                  + "\n4-Exit Program");
          System.exit(0);
      }
    } catch (NumberFormatException e) {
      System.out.println("The option must be a number");
    }
  }

  private static void paintings() throws Exception {
    String museumQuery = "SELECT Id, Name FROM Museum";
    LinkedList<String> names = new LinkedList<String>();
    LinkedList<String> ids = new LinkedList<String>();
    String paintQuery;
    ResultSet paintRs;
    String m_Id;
    ResultSet rs = s_object.executeQuery(museumQuery);
    while (rs.next()) {
      ids.add(rs.getString("Id"));
      names.add(rs.getString("name"));
    }
    while (names.size() > 0) {
      System.out.println("Name: " + names.remove());
      m_Id = ids.remove();
      paintQuery = "SELECT * FROM PAINTING WHERE m_Id =" + m_Id;
      paintRs = s_object.executeQuery(paintQuery);
      while (paintRs.next()) {
        System.out.println("\tTitle: " + paintRs.getString("Title"));
        System.out.println("\tp_Id: " + paintRs.getString("p_Id"));
        System.out.println("\tm_Id: " + paintRs.getString("m_Id"));
        System.out.println("\tCreator: " + paintRs.getString("Creator"));
      }
    }
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

  private static void updatePhoneNumber() throws Exception {
    Scanner inp = new Scanner(System.in);
    inp.useDelimiter("\n");
    System.out.print("Enter the name of the museum you would like to change: ");
    String museum = inp.next();
    System.out.print("Enter the new phone number with dashes: ");
    String num = inp.next();

    String q = "UPDATE Museum SET PhoneNum = '" + num + "' WHERE Name = '" + museum + "'";

    try {
      s_object.executeUpdate(q);
    } catch (SQLException e) {
      System.out.println("Invalid entry");
    }
  }
}
