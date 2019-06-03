package adver.example.adver.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
17:16, unique = true
*/
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy="category",targetEntity=Adver.class,cascade = CascadeType.ALL)
    private Set<Adver> advers;

    /**
     * Default constructor
     */
    public Category() {
    }

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

    public void setAdvers(Set<Adver> advers) {
        this.advers = advers;
    }

    public Set<Adver> getAdvers() {
        return advers;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getId() == category.getId() &&
                Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
