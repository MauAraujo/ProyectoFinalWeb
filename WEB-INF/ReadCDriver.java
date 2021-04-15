package projects;

import java.sql.*;
import java.util.ArrayList;
import projects.Collaborator;

public class ReadCDriver {
    private ArrayList<Collaborator> allCollaborators = new ArrayList<Collaborator>();
    private String projectID;

    ReadCDriver(String projectID) {
      String name, projects;
      int uid, id;

      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
          Statement sql = db.createStatement();
          ResultSet collabs = sql.executeQuery("SELECT * FROM `collaborator-projects` WHERE projectid = " + projectID);

          uid = collabs.getInt("userid");
          collabs = sql.executeQuery("SELECT * FROM collaborators WHERE userid = " + uid);
          while (collabs.next()) {
              id = collabs.getInt("userid");
              name = collabs.getString("name");
              projects = collabs.getString("projects");

              Collaborator current = new Collaborator(id, name, projects);
              allCollaborators.add(current);
          }

          collabs.close();
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

    public ArrayList<Collaborator> getCollaborators(){
        return allCollaborators;
    }
}
