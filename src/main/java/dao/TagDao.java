package dao;

import jpa.business.Tag;

import javax.persistence.Query;
import java.util.List;

public class TagDao extends AbstractJpaDao<String, Tag>{

    public TagDao(){
        super(Tag.class);
    }

    public List<Tag> findAllTagsByTicketId(Long ticketId){
        Query query = entityManager.createQuery("select g from Tag g join g.tickets t where t.id = ?1");
        query.setParameter(1, ticketId);
        return query.getResultList();
    }

}
