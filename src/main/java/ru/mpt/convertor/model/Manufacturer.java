package ru.mpt.convertor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Manufacturer(@NotNull String title) {
        this.title = title;
    }

    public Manufacturer() {
    }
}
