package fr.callidos.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tickets")
public class TicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ticket_id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "resolved")
    private Boolean resolved;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MessageModel> messages;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public TicketModel() {}

    public TicketModel(Long ticket_id, String sender, String receiver, Boolean resolved, String title, String desc, List<MessageModel> messages) {
        this.ticket_id = ticket_id;
        this.sender = sender;
        this.receiver = receiver;
        this.resolved = resolved;
        this.title = title;
        this.desc = desc;
        this.messages = messages;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public String getTitle() {
        return title;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }
}
