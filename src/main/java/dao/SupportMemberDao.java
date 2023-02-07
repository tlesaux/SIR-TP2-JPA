package dao;

import jpa.business.SupportMember;
import jpa.business.Ticket;

import javax.persistence.Query;
import java.util.List;

public class SupportMemberDao extends AbstractJpaDao<Long, SupportMember>{

    public SupportMemberDao(){
        super(SupportMember.class);
    }

    public List<Ticket> findMyAffectedTickets(Long userId){
        Query query = entityManager.createQuery("select t, s from Ticket t LEFT JOIN Support_Tickets s " +
                                                "WHERE s.affectedSupportMembers_id = 1?");
        query.setParameter(1, userId);
        return query.getResultList();
    }



}
