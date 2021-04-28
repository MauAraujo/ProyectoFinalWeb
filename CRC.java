package projects;

import java.util.ArrayList;

public class CRC {
    int id, projectid;
    String name, superclass;
    ArrayList < String > responsibilities;
    ArrayList < String > collaborations;

    public CRC(int id, int projectid, String name, String superclass, ArrayList < String > responsibilities, ArrayList < String > collaborations) {
        this.id = id;
        this.projectid = projectid;
        this.name = name;
        this.superclass = superclass;
        this.responsibilities = responsibilities;
        this.collaborations = collaborations;
    }

    public CRC(int id, int projectid, String name, String superclass) {
        this.id = id;
        this.projectid = projectid;
        this.name = name;
        this.superclass = superclass;
    }

    public void setResponsibilities(ArrayList < String > responsibilities) {
        this.responsibilities = new ArrayList < String > (responsibilities);
    }

    public void setCollaborations(ArrayList < String > collaborations) {
        this.collaborations = new ArrayList < String > (collaborations);
    }
}
