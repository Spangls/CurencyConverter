package ru.mpt.convertor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    private Integer capacity;

    @Column(name = "num_of_modules", nullable = true, length = 10)
    private String numOfModules;

    private Integer speed;

    @Enumerated(EnumType.STRING)
    @Column(name = "tech", nullable = false, length = 10)
    private RamTech ramTech;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getNumOfModules() {
        return numOfModules;
    }

    public void setNumOfModules(String numOfModules) {
        this.numOfModules = numOfModules;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public RamTech getRamTech() {
        return ramTech;
    }

    public void setRamTech(RamTech ramTech) {
        this.ramTech = ramTech;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
