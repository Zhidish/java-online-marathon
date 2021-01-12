package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 /*   public List<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles .add(role);
    }*/

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false)
    private String first_name;


    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false)
    private String last_name;


    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false)
    private String password;

    /*  @OneToMany(mappedBy = "roles")
      List<Role> roles;
  */
    @JoinColumn
    private long role_id;

    public long getRole() {
        return role_id;
    }

    public void setRole(Role role) {
        this.role_id = role.getId();
    }
}
