package adver.example.adver.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
17:16, unique = true
*/
@Entity(name = "status")
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false)
    private String name;
    /**
     * Default constructor
     */
    public Status() {
    }


//    @OneToMany(mappedBy="status",targetEntity=Message.class,cascade = CascadeType.ALL)
//    private Set<Message> messages;

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;
        Status status = (Status) o;
        return getId() == status.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

