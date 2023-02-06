package dao;

import jpa.business.Ticket;

public class TicketDao extends AbstractJpaDao<Long, Ticket>{

    public TicketDao(){
        super(Ticket.class);
    }


}
