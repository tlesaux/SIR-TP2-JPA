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
        super();
    }

    public SimpleUser(String name){
        super(name);
    }

    public String toString(){
        return "Name : " + this.name + " | ID : " + this.id + '\n';
    }



}
