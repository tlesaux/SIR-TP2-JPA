package jpa.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static misc.GlobalFunctions.getCurrentDate;


@Entity
public class Message implements Serializable {

    private Long id;
    private User sender;
    private Ticket relatedTicket;
    private String content;
    private Date date;

    public Message(){
        this.date = getCurrentDate();
    }

    public Message(User sender, Ticket ticket, String content){
        this.sender = sender;
        this.relatedTicket = ticket;
        this.content = content;
        this.date = getCurrentDate();
    }

    public String toString(){
        return "Message : [" + this.id + "] " + date + '\n' + this.sender + this.relatedTicket + "Content : " + '\n' + this.content + '\n';
    }

    @Id
    @GeneratedValue
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

    @Temporal(TemporalType.TIME)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
