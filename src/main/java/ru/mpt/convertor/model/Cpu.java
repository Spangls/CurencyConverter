package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
public class Cpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Column(name = "socket", nullable = false, length = 15)
    private Socket socket;

    private Integer cores;
    private Integer flows;
    private Float frequency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Integer getFlows() {
        return flows;
    }

    public void setFlows(Integer flows) {
        this.flows = flows;
    }

    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }
}
