package projects;

import java.util.ArrayList;
import projects.Collaborator;

public class Project {
    private int projectID;
    private String name;
    private String description;
    private String date;
    private ArrayList<Collaborator> collabs;

    public Project(int projectID, String name, String description, String date, ArrayList<Collaborator> collabs) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.collabs = collabs;
    }
}
