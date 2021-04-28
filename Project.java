package projects;

import java.util.ArrayList;
import projects.Collaborator;

public class Project {
    int id;
    String name;
    String description;
    String date;
    ArrayList<Collaborator> collabs;

    public Project(int id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Project(int id, String name, String description, String date, ArrayList<Collaborator> collabs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.collabs = collabs;
    }

    public void setCollabs(ArrayList < Collaborator > collabs) {
        this.collabs = new ArrayList < Collaborator >(collabs);
    }
}
