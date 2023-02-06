package dao;

import jpa.business.Message;

public class MessageDao extends AbstractJpaDao<Long, Message> {

    public MessageDao(){
        super(Message.class);
    }

}
