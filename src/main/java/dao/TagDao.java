package dao;

import jpa.business.Tag;
import jpa.business.Ticket;

import javax.persistence.Query;
import java.util.List;

public class TagDao extends AbstractJpaDao<String, Tag>{

    public TagDao(){
        super(Tag.class);
    }

    public List<Ticket> getTicketsByTagName(String tagName){
        Query query = entityManager.createQuery("select t, g from Ticket t LEFT JOIN Tickets_Tags g " +
                "WHERE g.tags_name = 1?");
        query.setParameter(1, tagName);
        return query.getResultList();
    }


}
