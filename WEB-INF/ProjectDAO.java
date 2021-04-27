package projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import projects.Project;
import projects.Collaborator;

public class ProjectDAO {
  String result;

  public String getProjects() {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Project > projects = new ArrayList < Project > ();
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int id, uid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `projects`");

      while (rs.next()) {
        id = rs.getInt("id");
        name = rs.getString("name");
        description = rs.getString("description");
        date = rs.getString("date");
        projects.add(new Project(id, name, description, date, collabs));
      }

      for (Project np: projects) {
        String query = "SELECT `collaborator-projects`.uid, collaborators.name FROM";
        query = query + " `collaborator-projects` INNER JOIN `collaborators`";
        query = query + " ON `collaborator-projects`.uid = collaborators.uid";
        query = query + " WHERE projectid = " + np.id;

        rs = stmt.executeQuery(query);
        while (rs.next()) {
          uid = rs.getInt("uid");
          cname = rs.getString("name");
          collabs.add(new Collaborator(uid, cname));
        }
        np.setCollabs(collabs);
        collabs.clear();
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
    return gson.toJson(projects);
  }

  public String getProject(int id) {
    Gson gson = new Gson();
    String name, cname, description, date;
    Project project = null;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int uid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `projects` WHERE id = " + id);

      while (rs.next()) {
        name = rs.getString("name");
        description = rs.getString("description");
        date = rs.getString("date");
        project = new Project(id, name, description, date);
      }

      String query = "SELECT `collaborator-projects`.uid, collaborators.name FROM";
      query = query + " `collaborator-projects` INNER JOIN `collaborators`";
      query = query + " ON `collaborator-projects`.uid = collaborators.uid";
      query = query + " WHERE projectid = " + id;

      rs = stmt.executeQuery(query);
      while (rs.next()) {
        uid = rs.getInt("uid");
        cname = rs.getString("name");
        collabs.add(new Collaborator(uid, cname));
      }
      project.setCollabs(collabs);
      collabs.clear();

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
    return gson.toJson(project);
  }

  public String createProject(Project project) {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int id = 0;
    int uid = 0;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `projects`");

      rs.moveToInsertRow();
      rs.updateString("name", project.name);
      rs.updateString("description", project.description);
      rs.updateString("date", project.date);
      rs.insertRow();
      rs.moveToCurrentRow();

      while (rs.next()) {
        id = rs.getInt("id");
      }

      // rs = stmt.executeQuery("SELECT * FROM `collaborators`");

      // for (Collaborator collab: project.collabs) {
      //   rs.moveToInsertRow();
      //   rs.updateString("name", collab.name);
      //   rs.insertRow();
      //   rs.moveToCurrentRow();

      //   while (rs.next()) {
      //       collab.setUid(rs.getInt("uid"));
      //   }
      // }


      rs = stmt.executeQuery("SELECT * FROM `collaborator-projects`");

      for (Collaborator collab: project.collabs) {
          rs.moveToInsertRow();
          rs.updateInt("uid", collab.uid);
          rs.updateInt("projectid", id);
          rs.insertRow();
          rs.moveToCurrentRow();
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
    return gson.toJson(project);
  }

  public String updateProject(Project project) {
    Gson gson = new Gson();
    String name, cname, description, date;
    ArrayList < Collaborator > collabs = project.collabs;
    int id = 0;
    int uid = 0;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `projects` WHERE id = " + project.id);
      result = gson.toJson(project);

      while(rs.next()) {
          rs.updateString("name", project.name);
          rs.updateString("description", project.description);
          rs.updateString("date", project.date);
          rs.updateRow();
      }

      // rs = stmt.executeQuery("SELECT * FROM `collaborators`");

      // for (Collaborator collab: project.collabs) {
      //   rs.moveToInsertRow();
      //   rs.updateString("name", collab.name);
      //   rs.insertRow();
      //   rs.moveToCurrentRow();

      //   while (rs.next()) {
      //       collab.setUid(rs.getInt("uid"));
      //   }
      // }

      stmt.executeUpdate("DELETE FROM `collaborator-projects` WHERE projectid = " + project.id);

      rs = stmt.executeQuery("SELECT * FROM `collaborator-projects`");

      for(Collaborator collab : collabs) {
          rs.moveToInsertRow();
          rs.updateInt("uid", collab.uid);
          rs.updateInt("projectid", project.id);
          rs.insertRow();
          rs.moveToCurrentRow();
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
    return gson.toJson(project);
  }

  public String deleteProject(int id) {
    int response = -1;
    Gson gson = new Gson();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

      response = stmt.executeUpdate("DELETE FROM `projects` WHERE id = " + id);
      stmt.executeUpdate("DELETE FROM `collaborator-projects` WHERE projectid = " + id);

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

    public String getCollaborators() {
    Gson gson = new Gson();
    String name;
    ArrayList < Collaborator > collabs = new ArrayList < Collaborator > ();
    int uid;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `collaborators`");

      while (rs.next()) {
        uid = rs.getInt("uid");
        name = rs.getString("name");
        collabs.add(new Collaborator(uid, name));
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
    return gson.toJson(collabs);
  }

  public String getResult() {
    return result;
  }
}
