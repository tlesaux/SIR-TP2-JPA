package dao;

import jpa.business.Message;
import jpa.business.SupportMember;
import jpa.business.Ticket;

import javax.persistence.Query;
import java.util.List;

public class SupportMemberDao extends AbstractJpaDao<Long, SupportMember>{

    public SupportMemberDao(){
        super(SupportMember.class);
    }

    public List<SupportMember> getAffectedSupportMembersByTicketId(Long ticketId){
        Query query = entityManager.createQuery("select m from SupportMember m where m.affectedTickets.id = ?1");
        query.setParameter(1, ticketId);
        return query.getResultList();
    }

}
