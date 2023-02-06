package jpa.business;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    long id;
    String name;

    List<Ticket> createdTickets;
    List<Message> sentMessages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany (mappedBy="userToHelp")
    public List<Ticket> getCreatedTickets() {
        return createdTickets;
    }

    public void setCreatedTickets(List<Ticket> tickets) {
        this.createdTickets = tickets;
    }

    @OneToMany (mappedBy="sender")
    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
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
