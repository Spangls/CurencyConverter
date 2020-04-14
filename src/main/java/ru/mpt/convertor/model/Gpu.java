package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
@Table(name = "gpu")
public class Gpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Column(name = "vram")
    private Float vram;

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

    public Float getVram() {
        return vram;
    }

    public void setVram(Float vram) {
        this.vram = vram;
    }
}
