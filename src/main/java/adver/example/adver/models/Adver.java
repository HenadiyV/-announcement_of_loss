package adver.example.adver.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
17:15
*/

@Entity(name = "adver")
@Table(name = "adver")
public class Adver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="textAdver")
    private String textAdver;
    @Column(name="photo")
    private String photo;
    @Temporal(TemporalType.DATE)
    private Date dataStart;
    @Temporal(TemporalType.DATE)
    private Date dataStop;

    /**
     * Определяет   тип  объявления  нашел потерял
     */
    @ManyToOne
    private Status status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @ManyToOne
    private City city;


    /**
     * Default constructor
     */
    public Adver() {
    }

    public Adver(String textAdver, String photo, Date dataStart, Date dataStop, Status status, Category category, User user, City city) {
        this.textAdver = textAdver;
        this.photo = photo;
        this.dataStart = dataStart;
        this.dataStop = dataStop;
        this.status = status;
        this.category = category;
        this.user = user;
        this.city = city;
    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextAdver() {
        return textAdver;
    }

    public void setTextAdver(String textAdver) {
        this.textAdver = textAdver;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataStop() {
        return dataStop;
    }

    public void setDataStop(Date dataStop) {
        this.dataStop = dataStop;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Adver{" +
                "id=" + id +
                ", id_User=" +
                ", textAdver='" + textAdver + '\'' +
                ", photo='" + photo + '\'' +
                ", dataStart=" + dataStart +
                ", dataStop=" + dataStop +
                ", id_Category=" +
                ", category=" + category +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adver)) return false;
        Adver adver = (Adver) o;
        return getId() == adver.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
