package dao;

import jpa.business.SupportMember;

public class SupportMemberDao extends AbstractJpaDao<Long, SupportMember>{

    public SupportMemberDao(){
        super(SupportMember.class);
    }

}
