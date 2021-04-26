package projects;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.BufferedReader;
import com.google.gson.Gson;
import projects.Story;
import projects.StoryDAO;

public class Historias extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String option, json;
        option = request.getParameter(type);

        json = getStories();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String json;
        Story story;
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
            story = gson.fromJson(jb.toString(), Story.class);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        if (story.id > 0) {
            json = updateStory(story);
        } else {
            json = createStory(story);
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
        Story story;
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
            story = gson.fromJson(jb.toString(), Story.class);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        json = deleteStory(story.id);

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String getStories() {
        StoryDAO dao = new StoryDAO();
        return dao.getStories();
    }

    public String createStory(Story story) {
        StoryDAO dao = new StoryDAO();
        return dao.createStory(story);
    }

    public String updateStory(Story story) {
        StoryDAO dao = new StoryDAO();
        return dao.updateStory(story);
    }

    public String deleteStory(int id) {
        StoryDAO dao = new StoryDAO();
        return dao.deleteStory(id);
    }
}
