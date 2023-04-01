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


@WebServlet(name = "ticket form", urlPatterns = {"/TicketForm"})
public class TicketForm extends HttpServlet {
    TicketDao ticketDao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ticketDao = new TicketDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Ticket> ticketList = ticketDao.findAll();
        StringBuilder html = new StringBuilder("<HTML>\n<BODY>\n" + "<H1>Nouveau Ticket</H1>\n" + "<UL>\n");
        html.append("<FORM Method=\"POST\" Action=\"/TicketInfo\">\n" +
                "    <label for=\"title\">Titre :</label>\n" +
                "    <INPUT type=\"text\" name=\"title\" id=\"title\"><BR><BR>\n" +
                "    <INPUT type=\"submit\">\n" +
                "</FORM>");

        html.append("\n" + "</UL>\n");

        html.append("</BODY></HTML>");
        out.println(html);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Ticket ticket = new Ticket(request.getParameter("name"), "Ma description,", new User("Tristan"));
        System.out.println(ticket);
        ticketDao.save(ticket);
        out.println("<HTML>\n<BODY>\n" + "<H1>Tickets</H1>\n" + "<UL>\n" + " <LI>Nom: " + ticket.getDescription() + "\n" + " <LI>Prenom: " + request.getParameter("firstname") + "\n" + " <LI>Age: " + request.getParameter("age") + "\n" + "</UL>\n" + "</BODY></HTML>");
    }
}
