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
import projects.CRC;
import projects.CRCDAO;

public class Historias extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String type, json, id;
        id = request.getParameter("id");
        type = request.getParameter("type");

        if (type.equals("story")) {
            json = getStories(Integer.parseInt(id));
        } else {
            json = getCRCs(Integer.parseInt(id));
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String json, type;
        Story story = null;
        CRC crc = null;
        Gson gson = new Gson();

        type = request.getParameter("type");

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
            if (type.equals("story")) {
                story = gson.fromJson(jb.toString(), Story.class);
            } else {
                crc = gson.fromJson(jb.toString(), CRC.class);
            }
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }


        if (type.equals("story")) {
            json = createStory(story);
        } else {
            json = createCRC(crc);
        }

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

        public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            String id, json, type;
        Gson gson = new Gson();

        type = request.getParameter("type");
        id = request.getParameter("id");

        if(type.equals("story")) {
            json = deleteStory(Integer.parseInt(id));
        } else {
            json = deleteCRC(Integer.parseInt(id));
        }

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

    }

    public String getStories(int id) {
        StoryDAO dao = new StoryDAO();
        return dao.getStories(id);
    }

    public String createStory(Story story) {
        StoryDAO dao = new StoryDAO();
        return dao.createStory(story);
    }

    public String deleteStory(int id) {
        StoryDAO dao = new StoryDAO();
        return dao.deleteStory(id);
    }

    public String getCRCs(int id) {
        CRCDAO dao = new CRCDAO();
        return dao.getCRCs(id);
    }

    public String createCRC(CRC crc) {
        CRCDAO dao = new CRCDAO();
        return dao.createCRC(crc);
    }

    public String deleteCRC(int id) {
        CRCDAO dao = new CRCDAO();
        return dao.deleteCRC(id);
    }
}
