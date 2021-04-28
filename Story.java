package projects;

public class Story {
    int id, projectid, value, days;
    String title, description, date, observations;

    public Story(int id, int projectid, String title, String description, String date, String observations, int value, int days) {
        this.id = id;
        this.projectid = projectid;
        this.title = title;
        this.description = description;
        this.date = date;
        this.observations = observations;
        this.value = value;
        this.days = days;
    }
}
