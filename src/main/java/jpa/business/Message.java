package jpa.business;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.StringContent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @NotNull
    private User sender;
    @ManyToOne
    @NotNull
    private Ticket relatedTicket;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime date;

    public Message(User sender, Ticket relatedTicket, String content) {
        this.sender = sender;
        this.relatedTicket = relatedTicket;
        this.content = content;
        this.date = LocalDateTime.now();
    }
}
