package jpa.business;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//Cette requête nommée est inutile car on utilise le pattern DAO. Elle est juste là pour répondre à la question du TP.
@NamedQuery(name = "getAllUsers", query = "select u from User as u")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    LocalDate registrationDate;
    @OneToMany(mappedBy = "assignee")
    private List<Ticket> AssigneeTickets;
    @OneToMany(mappedBy = "reporter")
    private List<Ticket> ReporterTickets;
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    public User(String name) {
        this.name = name;
        this.registrationDate = LocalDate.now();
    }

}
