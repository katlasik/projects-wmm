package pl.sda.projects.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 20, message = "{registration.errorMsg.name}")
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String name;

    @Email(regexp = ".+@.+\\..+", message = "{registration.errorMsg.email}")
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;


    @Size(min = 6, max = 100, message = "{registration.errorMsg.password}")
    @NotEmpty
    @NotNull
    private String password;

    @Size(min = 6, max = 100, message = "{registration.errorMsg.password}")
    @NotEmpty
    @NotNull
    private String rePassword;



    private boolean active;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    protected User() {}

    public User(Long id, @Size(min = 3, max = 20, message = "{registration.errorMsg.name}") @NotEmpty @NotNull String name, @Email(regexp = ".+@.+\\..+", message = "{registration.errorMsg.email}") @NotEmpty @NotNull String email, @Size(min = 6, max = 100, message = "{registration.errorMsg.password}") @NotEmpty @NotNull String password, @Size(min = 6, max = 100, message = "{registration.errorMsg.password}") @NotEmpty @NotNull String rePassword, boolean active, @NotNull Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(rePassword, user.rePassword) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, rePassword, active, role);
    }
}
