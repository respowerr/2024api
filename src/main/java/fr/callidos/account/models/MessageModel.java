package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Column(name = "message")
    @NotBlank
    private String message;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel() {}

    public String getFormattedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }

    public MessageModel(Long id, TicketModel ticket, String sender, String message, Date date) {
        this.id = id;
        this.ticket = ticket;
        this.sender = sender;
        this.message = message;
        this.date = date;
    }
}
