package jpa.business;

import javax.persistence.*;
import java.util.List;

@Entity
public class SupportMember extends User {

    List<Ticket> affectedTickets;

    public SupportMember(){
        super();
    }

    public SupportMember(String name){
        super(name);
    }


    @ManyToMany
    @JoinTable(name="Support_Tickets")
    public List<Ticket> getAffectedTickets() {
        return affectedTickets;
    }

    public void setAffectedTickets(List<Ticket> tickets) {
        this.affectedTickets = tickets;
    }


}
