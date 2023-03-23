package dao;

import jpa.business.Ticket;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static misc.GlobalFunctions.getCurrentDate;

public class TicketDao extends AbstractJpaDao<Long, Ticket>{

    public TicketDao(){
        super(Ticket.class);
    }

    public void closeTicketByTicketId(Long ticketId){
        Ticket t = findOne(ticketId);
        if(t != null){
            t.setStatus("Resolved");
            t.setClosingDate(getCurrentDate());
            update(t);
        }else {
            System.out.println("Could not find a Ticket with the ID " + ticketId);
        }
    }

    public List<Ticket> getAllTicketsByTitle(String title){
        Query query = entityManager.createQuery("select t from Ticket t where UPPER(t.title) LIKE CONCAT('%',UPPER(?1),'%')");
        query.setParameter(1, title);
        return query.getResultList();
    }

    public List<Ticket> getAllTicketsByUserId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t where t.creator.id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Ticket> getOpenTicketsByUserId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket as t where t.creator.id = ?1 AND t.status != 'Resolved'");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Ticket> getAffectedTicketsBySupportMemberId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t join t.affectedSupportMembers m WHERE m.id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Ticket> getOpenAffectedTicketsBySupportMemberId(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t join t.affectedSupportMembers m WHERE m.id = ?1 AND t.status != 'Resolved'");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public List<Ticket> getTicketsByTagName(String tagName){
        Query query = entityManager.createQuery("select t from Ticket t join t.tags g WHERE g.name = ?1 ");
        query.setParameter(1, tagName);
        return query.getResultList();
    }

    public List<Ticket> getOpenTicketsByTagName(String tagName){
        Query query = entityManager.createQuery("select t from Ticket t join t.tags g WHERE g.name = ?1 AND t.status != 'Resolved' ");
        query.setParameter(1, tagName);
        return query.getResultList();
    }

}
