package fr.callidos.account.models;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketModel ticket;

    @Column(name = "sender")
    private String sender;

    @Column(name = "message_text")
    private String messageText;

    public Long getId() {
        return id;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageModel() {}

    public MessageModel(Long id, TicketModel ticket, String sender, String messageText) { // Modification du constructeur pour inclure le texte du message
        this.id = id;
        this.ticket = ticket;
        this.sender = sender;
        this.messageText = messageText;
    }
}
