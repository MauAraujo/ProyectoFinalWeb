package projects;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import projects.Project;
import projects.ReadPDriver;
import projects.ReadCDriver;
import com.google.gson.Gson;

public class Proyectos extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String json;
        String projectID = request.getParameter("id");
        if (projectID != null) {
            json = GetProject(Integer.parseInt(projectID));
        } else {
            json = GetProjects();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    public String GetProjects() {
        ReadPDriver pdriver = new ReadPDriver();
        ArrayList<Project> allProjects = pdriver.getProjects();
        return new Gson().toJson(allProjects);
    }

    public String GetProject(int projectID) {
        ReadPDriver pdriver = new ReadPDriver(projectID);
        Project project = pdriver.getProject();
        return new Gson().toJson(project);
    }

    public void CreateProject(Project newProject) {
        //CreatePDriver driver = new CreatePDriver();
        //driver.createProject(project);
    }

    public void ModifyProject(Project updatedProject) {
    }

    public void DeleteProject(String projectID) {
    }
}
