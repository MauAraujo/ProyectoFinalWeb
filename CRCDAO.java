package projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import projects.CRC;

public class CRCDAO {
  String result;

  public String getCRCs(int projectid) {
    Gson gson = new Gson();
    String name, superclass, responsibility, collaboration;
    ArrayList < CRC > crcs = new ArrayList < CRC > ();
    ArrayList < String > responsibilities = new ArrayList < String > ();
    ArrayList < String > collaborations = new ArrayList < String > ();
    int id;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc` WHERE projectid = " + projectid);

      while (rs.next()) {
        id = rs.getInt("id");
        name = rs.getString("class");
        superclass = rs.getString("superclass");
        crcs.add(new CRC(id, projectid, name, superclass));
      }

      for (CRC crc: crcs) {
        rs = stmt.executeQuery("SELECT * FROM `crc-responsibilities` WHERE crc = " + crc.id);
        while (rs.next()) {
          responsibility = rs.getString("responsibility");
          responsibilities.add(responsibility);
        }
        crc.setResponsibilities(responsibilities);
        responsibilities.clear();
      }

      for (CRC crc: crcs) {
        rs = stmt.executeQuery("SELECT * FROM `crc-collaborations` WHERE crc = " + crc.id);
        while (rs.next()) {
          collaboration = rs.getString("collaboration");
          collaborations.add(collaboration);
        }
        crc.setCollaborations(collaborations);
        collaborations.clear();
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
    String name, superclass, responsibility, collaboration;
    CRC crc = null;
    ArrayList < String > responsibilities = new ArrayList < String > ();
    ArrayList < String > collaborations = new ArrayList < String > ();
    int projectid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc` WHERE id = " + id);

      while (rs.next()) {
        id = rs.getInt("id");
        projectid = rs.getInt("projectid");
        name = rs.getString("class");
        superclass = rs.getString("superclass");
        crc = new CRC(id, projectid, name, superclass);
      }

      rs = stmt.executeQuery("SELECT * FROM `crc-responsibilities` WHERE crc = " + id);
      while (rs.next()) {
        responsibility = rs.getString("responsibility");
        responsibilities.add(responsibility);
      }
      crc.setResponsibilities(responsibilities);
      responsibilities.clear();

      rs = stmt.executeQuery("SELECT * FROM `crc-collaborations` WHERE crc = " + id);
      while (rs.next()) {
        collaboration = rs.getString("collaboration");
        collaborations.add(collaboration);
      }
      crc.setCollaborations(collaborations);
      collaborations.clear();

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
    result = gson.toJson(crc);
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `crc`");

      rs.moveToInsertRow();
      rs.updateInt("projectid", crc.projectid);
      rs.updateString("class", crc.name);
      rs.updateString("superclass", crc.superclass);
      rs.insertRow();
      rs.moveToCurrentRow();

      while(rs.next()) {
          crc.id = rs.getInt("id");
      }

      for (String responsibility: crc.responsibilities) {
        rs = stmt.executeQuery("SELECT * FROM `crc-responsibilities`");
        rs.moveToInsertRow();
        rs.updateInt("crc", crc.id);
        rs.updateString("responsibility", responsibility);
        rs.insertRow();
      }

      for (String collaboration: crc.collaborations) {
        rs = stmt.executeQuery("SELECT * FROM `crc-collaborations`");
        rs.moveToInsertRow();
        rs.updateInt("crc", crc.id);
        rs.updateString("collaboration", collaboration);
        rs.insertRow();
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

  public String deleteCRC(int id) {
    int response = -1;
    Gson gson = new Gson();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

      stmt.executeUpdate("DELETE FROM `crc-responsibilities` WHERE crc = " + id);
      stmt.executeUpdate("DELETE FROM `crc-collaborations` WHERE crc = " + id);
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
