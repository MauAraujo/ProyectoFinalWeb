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
      String json = GetProjects();
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

    public void GetProject(String projectID) {
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
