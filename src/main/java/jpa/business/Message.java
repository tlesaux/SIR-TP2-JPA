package jpa.business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    Long id;

    User sender;

    Ticket relatedTicket;

    String content;

    Date date;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    public Ticket getRelatedTicket() {
        return relatedTicket;
    }

    public void setRelatedTicket(Ticket relatedTicket) {
        this.relatedTicket = relatedTicket;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
