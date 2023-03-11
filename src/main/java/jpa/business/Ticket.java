package jpa.business;

import com.sun.istack.NotNull;
import enumeration.Status;
import enumeration.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;
    @ManyToOne
    private User reporter;
    @ManyToOne
    private User assignee;
    @OneToMany(mappedBy = "relatedTicket", cascade = {CascadeType.REMOVE})
    private List<Message> conversation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection(targetClass = Tag.class)
    @Enumerated(EnumType.STRING)
    private Set<Tag> tags;

    public Ticket(String title, String description, User reporter) {
        this.title = title;
        this.description = description;
        this.reporter = reporter;
        this.creationDate = LocalDateTime.now();
        this.status = Status.TO_DO;
        this.tags = new HashSet<>();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}



