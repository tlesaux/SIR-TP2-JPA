package dao;

import jpa.business.Tag;
import jpa.business.Ticket;

import javax.persistence.Query;
import java.util.List;

public class TagDao extends AbstractJpaDao<String, Tag>{

    public TagDao(){
        super(Tag.class);
    }

    public List<Ticket> findAllTagsByTicketId(String tagName){
        Query query = entityManager.createQuery("select g from Tag g where g.tickets.id = ?1");
        query.setParameter(1, tagName);
        return query.getResultList();
    }

}
