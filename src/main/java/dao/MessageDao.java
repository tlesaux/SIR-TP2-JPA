package dao;

import jpa.business.Message;

import javax.persistence.Query;
import java.util.List;

public class MessageDao extends AbstractJpaDao<Long, Message> {

    public MessageDao(){
        super(Message.class);
    }

    public List<Message> getMessagesByTicketId(Long ticketId){
        Query query = entityManager.createQuery("select m from Message m where m.relatedTicket.id = ?1");
        query.setParameter(1, ticketId);
        return query.getResultList();
    }

    public List<Message> getMessagesByUserId(Long userId){
        Query query = entityManager.createQuery("select m from Message m where m.sender.id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }
}
