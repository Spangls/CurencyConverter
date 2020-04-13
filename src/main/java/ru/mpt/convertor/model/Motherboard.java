package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Column(name = "socket", nullable = false, length = 15)
    private String socket;

}
