package jpa.business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag implements Serializable {

    private String name;
    private List<Ticket> tickets;

    public Tag(){
        this.tickets = new ArrayList<Ticket>();
    }

    public Tag(String name){
        this.name = name;
        this.tickets = new ArrayList<Ticket>();
    }

    public String toString(){
        return "Tag Name : " + this.name;
    }

    @Id
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany (mappedBy="tags")
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


}
