package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

@Entity
public class Cpu{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false, length = 15)
    @JsonIgnore
    private Socket socket;

    @Column(name = "cores")
    private Integer cores;
    @Column(name = "flows")
    private Integer flows;
    @Column(name = "frequency")
    private Float frequency;

    @Transient
    private String socketTitle;

    @PostLoad
    private void postLoad(){
        socketTitle = socket.getTitle();
    }

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

    public String getSocketTitle() {
        return socketTitle;
    }

    public void setSocketTitle(String socketTitle) {
        this.socketTitle = socketTitle;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "id=" + id +
                ", item=" + item +
                ", socket=" + socket +
                ", cores=" + cores +
                ", flows=" + flows +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpu)) return false;
        Cpu cpu = (Cpu) o;
        return Objects.equals(id, cpu.id) &&
                Objects.equals(item, cpu.item) &&
                socket == cpu.socket &&
                Objects.equals(cores, cpu.cores) &&
                Objects.equals(flows, cpu.flows) &&
                Objects.equals(frequency, cpu.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, socket, cores, flows, frequency);
    }
}
