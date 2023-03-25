package servlet;

import dao.TicketDao;
import dao.UserDao;
import jpa.business.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userinfo",
        urlPatterns = {"/UserInfo"})
public class UserInfo extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    public void init() throws ServletException {
        super.init();
        this.userDao = new UserDao();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = new User(request.getParameter("name"));
        userDao.save(user);


        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Id: "
                + user.getId() + "\n" +
                " <LI>Nom: " +
                user.getName() + "\n" +
                " <LI>Date de création: "
                + user.getRegistrationDate() + "\n" +

                "</UL>\n" +
                "</BODY></HTML>");
    }
}
