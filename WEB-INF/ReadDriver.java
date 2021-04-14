package projects;

import java.sql.*;
import java.util.ArrayList;
import projects.Project;

public class ReadDriver {
    private ArrayList<Project> allProjects = new ArrayList<Project>();

    ReadDriver() {
      String name, description, date;
      int id;

      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
          Statement sql = db.createStatement();
          ResultSet projects = sql.executeQuery("SELECT * FROM projects");

          while (projects.next()) {
              id = projects.getInt("id");
              name = projects.getString("name");
              description = projects.getString("description");
              date = projects.getString("date");

              Project current = new Project(id, name, description, date);
              allProjects.add(current);
          }

          projects.close();
          db.close();
          sql.close();
      }
      catch (ClassNotFoundException e) {
          System.out.println("Not found");
      }
      catch (SQLException e) {
          System.out.println(e);
      }
  }

    public ArrayList<Project> getProjects(){
        return allProjects;
    }
}
