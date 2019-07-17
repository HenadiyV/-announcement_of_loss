package adver.example.adver.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
17:19, unique = true
*/
@Entity(name = "city")
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false)
    private String name;

    /**
     * Default constructor
     */
    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="city",targetEntity=Adver.class,cascade = CascadeType.ALL)
    private Set<Adver> advers;

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

    public Set<Adver> getAdvers() {
        return advers;
    }

    public void setAdvers(Set<Adver> advers) {
        this.advers = advers;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

