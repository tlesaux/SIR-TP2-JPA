package jpa.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("User")
public class SimpleUser extends User implements Serializable {

    public SimpleUser(){
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
    }

    public SimpleUser(String name){
        this.name = name;
        this.createdTickets = new ArrayList<Ticket>();
        this.sentMessages = new ArrayList<Message>();
    }

    public String toString(){
        return "Name : " + this.name + " | ID : " + this.id + '\n';
    }



}
