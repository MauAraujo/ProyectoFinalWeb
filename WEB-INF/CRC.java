package projects;

import java.util.ArrayList;

public class CRC {
    int id, projectid;
    String crClass, superclass;
    ArrayList < String > responsibilities;
    ArrayList < String > collaborations;

    public CRC(int id, int projectid, String crClass, String superclass, ArrayList < String > responsibilities, ArrayList < String > collaborations) {
        this.id = id;
        this.projectid = projectid;
        this.crClass = crClass;
        this.superclass = superclass;
        this.responsibilities = responsibilities;
        this.collaborations = collaborations;
    }

    public CRC(int id, int projectid, String crClass, String superclass) {
        this.id = id;
        this.projectid = projectid;
        this.crClass = crClass;
        this.superclass = superclass;
    }

    public void setResponsibilities(ArrayList < String > responsibilities) {
        this.responsibilities = new ArrayList < String > (responsibilities);
    }

    public void setCollaborations(ArrayList < String > collaborations) {
        this.collaborations = new ArrayList < String > (collaborations);
    }
}
