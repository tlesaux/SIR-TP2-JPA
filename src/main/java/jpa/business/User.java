package jpa.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public abstract class User {

    Long id;
    String name;
    List<Ticket> createdTickets;
    List<Message> sentMessages;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="userToHelp")
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
}
