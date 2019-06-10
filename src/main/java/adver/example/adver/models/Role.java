package adver.example.adver.models;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *01.06.2019
 *22:43
 */


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv
28.05.2019

*/
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false)
    private String name;

    /**
     * Default constructor
     */
    public Role() {
    }

    @OneToMany(mappedBy="role",targetEntity=User.class,cascade = CascadeType.ALL)
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
