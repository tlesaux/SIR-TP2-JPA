package dao;

import jpa.business.Tag;

public class TagDao extends AbstractJpaDao<String, Tag>{

    public TagDao(){
        super(Tag.class);
    }


}
