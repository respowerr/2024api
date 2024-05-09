package fr.callidos.account.models;

import jakarta.persistence.*;

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

    @Column(name = "message")
    private String message;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTicket_id() {
        return ticket_id;
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

    public String getMessage() {
        return message;
    }
}
