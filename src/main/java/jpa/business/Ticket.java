package jpa.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static misc.GlobalFunctions.getCurrentDate;

@Entity
public class Ticket implements Serializable {

    private Long id;
    private String status;
    private String title;
    private String description;
    private Date creationDate;
    private Date closingDate;
    private User creator;
    private List<SupportMember> affectedSupportMembers;
    private List<Message> conversation;
    private List<Tag> tags;

    public Ticket(){
        this.affectedSupportMembers = new ArrayList<SupportMember>();
        this.conversation = new ArrayList<Message>();
        this.tags = new ArrayList<Tag>();
        this.creationDate = getCurrentDate();
    }

    public Ticket(String title, String desc, User creator){
        this.status = "Unresolved";
        this.title = title;
        this.description = desc;
        this.creator = creator;
        this.affectedSupportMembers = new ArrayList<SupportMember>();
        this.conversation = new ArrayList<Message>();
        this.tags = new ArrayList<Tag>();
        this.creationDate = getCurrentDate();
    }

    public String toString(){
        String toReturn =  "Ticket [" + this.id + "] " + this.status + " | started at " + creationDate + " | closed at " + closingDate +
                '\n' + "From " + this.creator.getName() +
                '\n' + "Titre : " + this.title +
                '\n' + "Description : " + this.description ;

        String tags = "";
        for(int i = 0 ; i < this.tags.size() ; i++){
            tags += this.tags.get(i) + " | ";
        }

        toReturn += '\n' + "Tags : " + tags;

        String supportMembers = "";
        for(int i = 0 ; i < this.affectedSupportMembers.size() ; i++){
            supportMembers += this.affectedSupportMembers.get(i).getName() + " | ";
        }

        toReturn += '\n' + "Affected Support Members : " + supportMembers + '\n';
        return toReturn;
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
    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }

    @ManyToMany (mappedBy="affectedTickets")
    public List<SupportMember> getAffectedSupportMembers() {
        return affectedSupportMembers;
    }
    public void setAffectedSupportMembers(List<SupportMember> affectedSupportMembers) { this.affectedSupportMembers = affectedSupportMembers; }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
        tag.getTickets().add(this);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
        tag.getTickets().remove(this);
    }




}

