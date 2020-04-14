package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "num_of_modules", length = 10)
    private String numOfModules;

    @Column(name = "frequency")
    private Integer frequency;

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

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer speed) {
        this.frequency = speed;
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
