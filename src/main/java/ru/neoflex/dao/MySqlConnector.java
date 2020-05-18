package ru.neoflex.dao;

import java.sql.*;

public class MySqlConnector {

    private static Connection getConnection(){
      Connection con = null;
      try {
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testimony?useUnicode=true&serverTimezone=UTC", "root", "148192");
        System.out.println("Got our connection");
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return con;
    }


  public static void selectAllFrommBilling() throws SQLException {
    Connection connection = getConnection();
    Statement st = connection.createStatement();
    String query = "SELECT * FROM billing_period";
    ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
      int id = rs.getInt("id");
      String date = rs.getString("currentmonth");
      int coldWater = rs.getInt("coldWater");
      int hotWater = rs.getInt("hotWater");
      int gas = rs.getInt("gas");
      int electricity = rs.getInt("electricity");
      System.out.format("%s, %s, %s, %s, %s, %s\n", id, date, coldWater, hotWater, gas, electricity);
      System.out.println(date);
    }
    connection.close();
  }

}
