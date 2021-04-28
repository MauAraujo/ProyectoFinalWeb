package auth;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import auth.AuthDAO;

public class Auth extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name, passwd;
        name = request.getParameter("name");
        passwd = request.getParameter("passwd");

        PrintWriter out = response.getWriter();
        String token = LogIn(name, passwd);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(token);
        out.flush();

    }

    private String LogIn(String name, String passwd) {
        AuthDAO auth = new AuthDAO();
        return auth.LogIn(name, passwd);
        //return auth.Register(name, passwd);
    }
}
