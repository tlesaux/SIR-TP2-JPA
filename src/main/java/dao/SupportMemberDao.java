package dao;

import jpa.business.SupportMember;

import javax.persistence.Query;
import java.util.List;

public class SupportMemberDao extends AbstractJpaDao<Long, SupportMember>{

    public SupportMemberDao(){
        super(SupportMember.class);
    }

    public List<SupportMember> getAffectedSupportMembersByTicketId(Long ticketId){
        Query query = entityManager.createQuery("select m from SupportMember join m.affectedTickets t where t.id = ?1");
        query.setParameter(1, ticketId);
        return query.getResultList();
    }

}
