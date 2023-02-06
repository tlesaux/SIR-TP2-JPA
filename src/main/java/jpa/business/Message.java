package jpa.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    Long id;

    User sender;

    Ticket relatedTicket;

    String content;

    Date date;

    public Message(){

    }

    public Message(User sender, Ticket ticket, String content){
        this.sender = sender;
        this.relatedTicket = ticket;
        this.content = content;
    }

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

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
