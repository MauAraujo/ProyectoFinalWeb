package projects;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import projects.Project;
import projects.ReadDriver;
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
        ReadDriver driver = new ReadDriver();
        ArrayList<Project> allProjects = driver.getProjects();
        return new Gson().toJson(allProjects);
    }

    public void GetProject(String projectID) {
        
    }

    public void CreateProject(Project newProject) {
        
    }

    public void ModifyProject(Project updatedProject) {
        
    }

    public void DeleteProject(String projectID) {
        
    }
}
