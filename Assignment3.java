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
                               ArrayList brandList) {
    int resultSize = 0;
    try {
      while (resultSet.next()) {
        nameList.add(resultSet.getString("firstname"));
        brandList.add(resultSet.getString("brand"));
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
    ArrayList<String> brandList = new ArrayList<>();

    ResultSet resultSet = statement.executeQuery(
                            "SELECT firstname, brand " +
                            "FROM   owners, people, cars " +
                            "WHERE  owners.person_id = people.person_id " +
                            "AND    owners.car_id = cars.car_id");

    int resultSize = storeInArrayLists(resultSet, nameList, brandList);

    for (int i = 0; i < resultSize; i++) {
      System.out.println(nameList.get(i) + " äger en " + brandList.get(i));
    }
  }

}
