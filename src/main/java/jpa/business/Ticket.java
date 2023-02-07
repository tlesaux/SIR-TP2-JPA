package jpa.business;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ticket implements Serializable {

    Long id;
    String status;
    String description;
    Date startingDate;
    Date closingDate;
    User userToHelp;
    List<SupportMember> affectedSupportMembers;
    List<Message> conversation;
    List<Tag> tags;

    public Ticket(){
        this.affectedSupportMembers = new ArrayList<SupportMember>();
        this.conversation = new ArrayList<Message>();
        this.tags = new ArrayList<Tag>();
    }

    public Ticket(String desc, User user){
        this.status = "Unresolved";
        this.description = desc;
        this.userToHelp = user;
        this.affectedSupportMembers = new ArrayList<SupportMember>();
        this.conversation = new ArrayList<Message>();
        this.tags = new ArrayList<Tag>();
    }

    public Ticket(String desc, User user, List<SupportMember> supp, List<Tag> tags){
        this.status = "Unresolved";
        this.description = desc;
        this.userToHelp = user;
        this.affectedSupportMembers = supp;
        this.tags = tags;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    @ManyToMany (mappedBy="affectedTickets")
    public List<SupportMember> getAffectedSupportMembers() {
        return affectedSupportMembers;
    }
    public void setAffectedSupportMembers(List<SupportMember> affectedSupportMembers) { this.affectedSupportMembers = affectedSupportMembers; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
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

    @OneToMany (mappedBy="relatedTicket", cascade = {CascadeType.REMOVE})
    public List<Message> getConversation() {
        return conversation;
    }
    public void setConversation(List<Message> conversation) {
        this.conversation = conversation;
    }

    @ManyToMany
    @JoinTable(name="Tickets_Tags")
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
        tag.tickets.add(this);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
        tag.tickets.remove(this);
    }




}

