package servlet;

import dao.TicketDao;
import jpa.business.Ticket;
import jpa.business.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



@WebServlet(name = "ticketinfo", urlPatterns = {"/TicketInfo"})
public class TicketInfo extends HttpServlet {
    TicketDao ticketDao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ticketDao = new TicketDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Ticket ticket = new Ticket(request.getParameter("name"), "Ma description,",new User("Tristan"));
        ticketDao.save(ticket);

       out.println("<HTML>\n<BODY>\n" + "<H1>Tickets</H1>\n" + "<UL>\n" + " <LI>Nom: " + request.getParameter("name") + "\n" + " <LI>Prenom: " + request.getParameter("firstname") + "\n" + " <LI>Age: " + request.getParameter("age") + "\n" + "</UL>\n" + "</BODY></HTML>");
    }
}
