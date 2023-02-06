package jpa.business;

import javax.persistence.*;
import java.util.List;

@Entity
public class SupportMember extends User {

    List<Ticket> affectedTickets;

    public SupportMember(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name="Support_Tickets")
    public List<Ticket> getAffectedTickets() {
        return affectedTickets;
    }

    public void setAffectedTickets(List<Ticket> tickets) {
        this.affectedTickets = tickets;
    }


    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





}
