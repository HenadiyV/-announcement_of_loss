package adver.example.adver.models;

import javax.persistence.*;
import java.util.Objects;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
17:18
*/
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="textMessage", nullable = false)
    private String textMessage;

    @ManyToOne
    private Status status;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;


    /**
     * Default constructor
     */
    public Message() {
    }
    // getter and setter

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTextMessage() {

        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getId() == message.getId() &&
                getTextMessage().equals(message.getTextMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTextMessage());
    }
}

