import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Assignment3 {

  public static Connection getConnection() {
    try {
      String url = "jdbc:mysql://localhost:3306/sqlandjava" +
                   "?serverTimezone=UTC";
      String username = "user";
      String password = "password";

      Connection conn = DriverManager.getConnection(url,username,password);
      System.out.println("Connected to database!");
      return conn;

    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  static int storeInArrayLists(ResultSet resultSet,
                               ArrayList nameList,
                               ArrayList carList) {
    String fullname;
    String color;
    String brand;
    int resultSize = 0;
    try {
      while (resultSet.next()) {
        fullname = resultSet.getString("firstname") + " " +
                   resultSet.getString("lastname");
        color =    resultSet.getString("color");
        brand =    resultSet.getString("brand");
        nameList.add(fullname);
        carList.add(color.toLowerCase() + " " + brand);
        resultSize++;
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return resultSize;
  }

  public static void main(String[] args) throws Exception {
    Connection conn             = getConnection();
    Statement statement         = conn.createStatement();
    ArrayList<String> nameList  = new ArrayList<>();
    ArrayList<String> carList   = new ArrayList<>();

    ResultSet resultSet = statement.executeQuery(
                            "SELECT firstname, lastname, brand, color " +
                            "FROM   owners, people, cars " +
                            "WHERE  owners.person_id = people.person_id " +
                            "AND    owners.car_id = cars.car_id");

    int resultSize = storeInArrayLists(resultSet, nameList, carList);

    for (int i = 0; i < resultSize; i++) {
      System.out.println(nameList.get(i) + " owns a " + carList.get(i));
    }

    /*
    Connected to database!
    Carl Dolk owns a red Audi
    Anna Bolt owns a green Ford
    Erik Fram owns a blue Saab
    Gina Hult owns a black Volvo
    */
  }

}
