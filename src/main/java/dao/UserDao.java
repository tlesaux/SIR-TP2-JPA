package dao;

import jpa.business.User;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao(){
        super(User.class);
    }


}
