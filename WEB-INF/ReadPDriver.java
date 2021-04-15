package projects;

import java.sql.*;
import java.util.ArrayList;
import projects.Project;
import projects.Collaborator;
import projects.ReadCDriver;

public class ReadPDriver {
    private ArrayList<Project> allProjects = new ArrayList<Project>();
    private Project project;

    ReadPDriver() {
        String name, description, date;
        int id;
        ArrayList<Collaborator> collabs;

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
                ReadCDriver cdriver = new ReadCDriver(id);
                collabs = cdriver.getCollaborators();
                Project current = new Project(id, name, description, date, collabs);
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

    ReadPDriver(int projectID) {
        String name, description, date;
        int id;
        ArrayList<Collaborator> collabs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
            Statement sql = db.createStatement();
            ResultSet projects = sql.executeQuery("SELECT * FROM projects WHERE id = " + projectID);

            while (projects.next()) {
                id = projects.getInt("id");
                name = projects.getString("name");
                description = projects.getString("description");
                date = projects.getString("date");
                ReadCDriver cdriver = new ReadCDriver(id);
                collabs = cdriver.getCollaborators();
                project = new Project(id, name, description, date, collabs);
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

    public Project getProject() {
        return project;
    }
}
