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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Ticket> ticketList = ticketDao.findAll();
        StringBuilder html = new StringBuilder("<HTML>\n<BODY>\n" + "<H1>Tickets</H1>\n" + "<UL>\n");

        for (Ticket t : ticketList) {
            html.append("<LI>");
            html.append("<H3>Titre: ").append(t.getTitle()).append("</H3>");
            html.append("<P>Statut: ").append(t.getStatus()).append("</P>");
            html.append("<P>TAG: ").append(t.getTags()).append("</P>");
            if (t.getAssignee() != null) {
                html.append("<P>Assigné: ").append(t.getAssignee().getName()).append("</P>");
            }
            if (t.getReporter() != null) {
                html.append("<P>Reporteur: ").append(t.getReporter().getName()).append("</P>");
            }
            html.append("</LI>");
        }
        html.append("\n" + "</UL>\n");

        html.append("<FORM Method=\"GET\" Action=\"/TicketForm\">\n" +
                "    <Button type=”submit” value=”Send”> Ajouter un ticket </Button>\n" +
                "</FORM>");

        html.append("</BODY></HTML>");
        out.println(html);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getParameter("title"));
        ticket.setDescription("description");
        ticketDao.save(ticket);

        out.println("<HTML>\n<BODY>\n" + "Ticket créé" + "<BR>"+
                "<FORM Method=\"GET\" Action=\"/TicketInfo\">\n" +
                "    <Button type=”submit” value=”Send”> Voir les tickets </Button>\n" +
                "</FORM>" + "</BODY></HTML>");
    }
}
