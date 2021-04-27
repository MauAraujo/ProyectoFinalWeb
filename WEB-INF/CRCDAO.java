package projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import projects.CRC;

public class CRCDAO {
  String result;

  public String getCRCs() {
    Gson gson = new Gson();
    String crClass, superclass;
    ArrayList < CRC > crcs = new ArrayList < CRC > ();
    int id, projectid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc`");

      while (rs.next()) {
        id = rs.getInt("id");
        projectid = rs.getInt("projectid");
        crClass = rs.getString("class");
        superclass = rs.getString("superclass");
        crcs.add(new CRC(id, projectid, crClass, superclass));
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      result = e.getMessage();
      e.printStackTrace();
    } catch (SQLException e) {
      result = e.getMessage();
      e.printStackTrace();
    }
    return gson.toJson(crcs);
  }

  public String getCRC(int id) {
    Gson gson = new Gson();
    String crClass, superclass;
    CRC crc = null;
    int projectid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc` WHERE id = " + id);

      while (rs.next()) {
        id = rs.getInt("id");
        projectid = rs.getInt("projectid");
        crClass = rs.getString("class");
        superclass = rs.getString("superclass");
        crc = new CRC(id, projectid, crClass, superclass);
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      result = e.getMessage();
      e.printStackTrace();
    } catch (SQLException e) {
      result = e.getMessage();
      e.printStackTrace();
    }
    return gson.toJson(crc);
  }

  public String createCRC(CRC crc) {
    Gson gson = new Gson();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc`");

      rs.moveToInsertRow();
      rs.updateInt("projectid", crc.projectid);
      rs.updateString("class", crc.crClass);
      rs.updateString("superclass", crc.superclass);
      rs.insertRow();

      rs.close();
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      result = e.getMessage();
      e.printStackTrace();
    } catch (SQLException e) {
      result = e.getMessage();
      e.printStackTrace();
    }
    return gson.toJson(crc);
  }

  public String deleteCRC(int id) {
      int response = -1;
      Gson gson = new Gson();

      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
          Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

          response = stmt.executeUpdate("DELETE FROM `crc` WHERE id = " + id);

          stmt.close();
          conn.close();
      } catch (ClassNotFoundException e) {
          result = e.getMessage();
          e.printStackTrace();
      } catch (SQLException e) {
          result = e.getMessage();
          e.printStackTrace();
      }
      return gson.toJson(response);
  }

  public String getResult() {
    return result;
  }
}
