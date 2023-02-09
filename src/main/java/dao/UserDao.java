package dao;

import jpa.business.Message;
import jpa.business.Ticket;
import jpa.business.SimpleUser;
import jpa.business.User;

import javax.persistence.Query;
import java.util.List;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao(){
        super(User.class);
    }

    public User getUserByTicketId(Long ticketId){
        Query query = entityManager.createQuery("select u from User u where u.creator = ?1");
        query.setParameter(1, ticketId);
        return (User) query.getSingleResult();
    }

}
