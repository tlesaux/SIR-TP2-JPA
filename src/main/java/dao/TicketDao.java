package dao;

import jpa.business.Ticket;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDao extends AbstractJpaDao<Long, Ticket>{

    public TicketDao(){
        super(Ticket.class);
    }

    public void closeTicketById(Long ticketId){
        Query query = entityManager.createQuery("UPDATE Ticket t SET status = 'CLOSE', closingDate = :date WHERE t.id = :id");
        LocalDateTime date = LocalDateTime.now();
        query.setParameter("date", date);
        query.setParameter("id", ticketId);
        query.executeUpdate();
    }

    public List<Ticket> getAllTicketsByReporterId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t where t.reporter.id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Ticket> getAllTicketsByAssigneeId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t where t.assignee.id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }

}
