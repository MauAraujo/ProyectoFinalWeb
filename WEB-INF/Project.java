package projects;

public class Project {
    private int projectID;
    private String name;
    private String description;
    private String date;

    public Project(int projectID, String name, String description, String date) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.date = date;
    }
}
