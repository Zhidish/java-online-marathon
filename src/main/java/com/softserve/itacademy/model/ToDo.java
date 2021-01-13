package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos")
public class ToDo {
    public ToDo() {
        created_at = LocalDateTime.now();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner_id() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ToDo_sequence"),
                    @Parameter(name = "initial_value", value = "20"),
                    @Parameter(name = "increment_size", value = "1")


            }

    )
    private long id;


    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at;


    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false, unique = true)
    private String title;



    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;

}
