package dao;

import jpa.business.Message;
import jpa.business.Ticket;

import javax.persistence.Query;
import java.util.List;

public class TicketDao extends AbstractJpaDao<Long, Ticket>{

    public TicketDao(){
        super(Ticket.class);
    }

    public List<Ticket> findAllTicketsByUserId(Long userId){
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








}
