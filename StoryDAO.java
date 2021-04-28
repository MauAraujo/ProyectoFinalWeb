package projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import projects.Story;

public class StoryDAO {
  String result;

  public String getStories() {
    Gson gson = new Gson();
    String title, description, date, observations;
    ArrayList < Story > stories = new ArrayList < Story > ();
    int id, projectid, value, days;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `user-stories`");

      while (rs.next()) {
        id = rs.getInt("id");
        projectid = rs.getInt("projectid");
        title = rs.getString("title");
        description = rs.getString("description");
        date = rs.getString("date");
        observations = rs.getString("observations");
        value = rs.getInt("value");
        days = rs.getInt("days");
        stories.add(new Story(id, projectid, title, description, date, observations, value, days));
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
    String title, description, date, observations;
    Story story = null;
    int projectid, value, days;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `user-stories` WHERE id = " + id);

      while (rs.next()) {
        id = rs.getInt("id");
        projectid = rs.getInt("projectid");
        title = rs.getString("title");
        description = rs.getString("description");
        date = rs.getString("date");
        observations = rs.getString("observations");
        value = rs.getInt("value");
        days = rs.getInt("days");
        story = new Story(id, projectid, title, description, date, observations, value, days);
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
    ArrayList < Story > stories = new ArrayList < Story > ();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `user-stories`");

      rs.moveToInsertRow();
      rs.updateInt("projectid", story.projectid);
      rs.updateString("title", story.title);
      rs.updateString("description", story.description);
      rs.updateString("date", story.date);
      rs.updateString("observations", story.observations);
      rs.updateInt("value", story.value);
      rs.updateInt("days", story.days);
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
    return gson.toJson(story);
  }

  public String deleteStory(int id) {
      int response = -1;
      Gson gson = new Gson();

      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
          Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

          response = stmt.executeUpdate("DELETE FROM `user-stories` WHERE id = " + id);

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
