package adver.example.adver.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/*
*@autor Hennadiy Voroboiv 
27.05.2019
16:01
*/
@Entity // This tells Hibernate to make a table out of this class
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false)

    @NotEmpty(message= "Заповніть поле имя.")
    private String name;
    @Column(name="email", nullable = false)
    @NotEmpty(message="Заповніть поле email.")
    private String email;
    @Column(name="password", nullable = false)
    @NotEmpty(message="Заповніть поле пароль.")
    private String password;
    @Column(name="phohe")
    private String phone;

    @OneToMany(mappedBy="user",targetEntity=Adver.class,cascade = CascadeType.ALL)
    private Set<Adver> advers;

    @ManyToOne
    private Role role;
    //===================================
    @ElementCollection(targetClass = Rl.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns =@JoinColumn(name="user_id") )
    @Enumerated(EnumType.STRING)
    private Set<Rl> rls;

    public Set<Rl> getRls() {
        return rls;
    }

    public void setRls(Set<Rl> rls) {
        this.rls = rls;
    }

    //================================
    /**
     * Default constructor
     */
    public User() {
    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRls();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdvers(Set<Adver> advers) {
        this.advers = advers;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
