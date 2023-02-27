package com.example.ToDoList.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ToDo")
public class ToDo {

    //@Column(unique = true)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    // @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column(name = "dueDate")
    private LocalDate dueDate;

    @NotNull
    @Column(name = "completed")
    private Boolean completed;

    public ToDo(String title, String description, LocalDate dueDate, Boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

}
