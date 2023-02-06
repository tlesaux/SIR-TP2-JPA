package jpa.business;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ticket {

    long id;
    String status;


    User userToHelp;

    List<SupportMember> affectedSupportMembers;
    String description;
    Date startingDate;
    Date closingDate;

    List<Tag> tags;


    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    public User getUserToHelp() {
        return userToHelp;
    }

    public void setUserToHelp(User userToHelp) {
        this.userToHelp = userToHelp;
    }

    @ManyToMany (mappedBy="tickets")
    public List<SupportMember> getAffectedSupportMembers() {
        return affectedSupportMembers;
    }

    public void setAffectedSupportMembers(List<SupportMember> affectedSupportMembers) {
        this.affectedSupportMembers = affectedSupportMembers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    @ManyToMany
    @JoinTable(name="Tickets_Tags")
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }



}

