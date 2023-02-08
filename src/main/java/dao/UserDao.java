package dao;

import jpa.business.Ticket;
import jpa.business.SimpleUser;
import jpa.business.User;

import javax.persistence.Query;
import java.util.List;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao(){
        super(User.class);
    }

    public List<Ticket> findMyCreatedTickets(Long userId){
        Query query = entityManager.createQuery("select t from Ticket t where t.creator.id = ?1");
        List<Ticket> list = query.setParameter(1, userId).getResultList();
        return list;
    }

    public List<Ticket> findMyOpenTickets(Long userId){
        Query query = entityManager.createQuery("select t from Ticket as t where t.creator = ?1 AND t.status!='Resolved'");
        query.setParameter(1, userId);
        return query.getResultList();
    }





}
