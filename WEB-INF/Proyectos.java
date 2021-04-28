package projects;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.BufferedReader;
import com.google.gson.Gson;
import projects.Project;
import projects.ProjectDAO;

public class Proyectos extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String option, collabs, json;
        option = request.getParameter("id");
        collabs = request.getParameter("collabs");

        if (option == null && collabs == null) {
            json = getProjects();
        } else if (collabs == null){
            json = getProject(Integer.parseInt(option));
        } else {
            json = getCollaborators();
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String json;
        Project project;
        Gson gson = new Gson();

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            project = gson.fromJson(jb.toString(), Project.class);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        if (project.id > 0) {
            json = updateProject(project);
        } else {
            json = createProject(project);
        }

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

        public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String id, json;

        id = request.getParameter("id");
        json = deleteProject(Integer.parseInt(id));

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String getProjects() {
        ProjectDAO dao = new ProjectDAO();
        return dao.getProjects();
    }

    public String getProject(int id) {
        ProjectDAO dao = new ProjectDAO();
        return dao.getProject(id);
    }

    public String createProject(Project project) {
        ProjectDAO dao = new ProjectDAO();
        return dao.createProject(project);
    }

    public String updateProject(Project project) {
        ProjectDAO dao = new ProjectDAO();
        return dao.updateProject(project);
    }

    public String deleteProject(int id) {
        ProjectDAO dao = new ProjectDAO();
        return dao.deleteProject(id);
    }

    public String getCollaborators() {
        ProjectDAO dao = new ProjectDAO();
        return dao.getCollaborators();
    }
}
