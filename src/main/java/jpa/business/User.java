package jpa.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static misc.GlobalFunctions.getCurrentDate;
import static org.hsqldb.Tokens.CURRENT_DATE;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@NamedQuery(name="getAllUsers", query="select u from User as u")
//Cette requête nommée est inutile car on utilise le pattern DAO. Elle est juste là pour répondre à la question du TP.

public abstract class User implements Serializable {

    private Long id;
    private String name;
    private Date registrationDate;
    private List<Ticket> createdTickets;
    private List<Message> sentMessages;

    public User(){
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
        this.registrationDate = getCurrentDate();
    }

    public User(String name){
        this.name = name;
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
        this.registrationDate = getCurrentDate();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="creator")
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
