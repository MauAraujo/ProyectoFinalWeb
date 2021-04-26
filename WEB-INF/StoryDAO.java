package projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import stories.Story;
import stories.Collaborator;

public class StoryDAO {
  String result;

  public String getStories() {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Story > stories = new ArrayList < Story > ();
    int id, uid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `stories`");

      while (rs.next()) {
        id = rs.getInt("id");
        name = rs.getString("name");
        description = rs.getString("description");
        date = rs.getString("date");
        stories.add(new Story(id, name, description, date, collabs));
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
    return gson.toJson(stories);
  }

  public String getStory(int id) {
    Gson gson = new Gson();
    String name, cname, description, date;
    Story story = null;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int uid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `stories` WHERE id = " + id);

      while (rs.next()) {
        name = rs.getString("name");
        description = rs.getString("description");
        date = rs.getString("date");
        story = new Story(id, name, description, date);
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
    return gson.toJson(story);
  }

  public String createStory(Story story) {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int id = 0;
    int uid = 0;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `stories`");

      rs.moveToInsertRow();
      rs.updateString("name", story.name);
      rs.updateString("description", story.description);
      rs.updateString("date", story.date);
      rs.insertRow();
      rs.moveToCurrentRow();

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
    return gson.toJson(story);
  }

  public String updateStory(Story story) {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int id = 0;
    int uid = 0;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `stories` WHERE id = " + story.id);

      rs.updateString("name", story.name);
      rs.updateString("description", story.description);
      rs.updateString("date", story.date);
      rs.updateRow();
      rs.moveToCurrentRow();

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
    return gson.toJson(story);
  }

  public String deleteStory(int id) {
    Gson gson = new Gson();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `stories` WHERE id = " + id);

      rs.deleteRow();

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
    return gson.toJson(id);
  }

  public String getResult() {
    return result;
  }
}
