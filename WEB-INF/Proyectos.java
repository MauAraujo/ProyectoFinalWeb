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

        String option, json;
        option = request.getParameter("id");

        if (option == null) {
            json = getProjects();
        } else {
            json = getProject(Integer.parseInt(option));
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

        json = deleteProject(project.id);

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
}
