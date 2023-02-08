package jpa.business;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Support")
public class SupportMember extends User {

    List<Ticket> affectedTickets;

    public SupportMember(){
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
        this.affectedTickets = new ArrayList<Ticket>();
    }

    public SupportMember(String name){
        this.name = name;
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
        this.affectedTickets = new ArrayList<Ticket>();
    }


    @ManyToMany
    @JoinTable(name="Support_Tickets")
    public List<Ticket> getAffectedTickets() {
        return affectedTickets;
    }

    public void setAffectedTickets(List<Ticket> tickets) {
        this.affectedTickets = tickets;
    }

    public void affectTicket(Ticket ticket){
        this.affectedTickets.add(ticket);
        ticket.getAffectedSupportMembers().add(this);
    }

    public void disaffectTicket(Ticket ticket){
        this.affectedTickets.remove(ticket);
        ticket.getAffectedSupportMembers().remove(this);
    }


}
