package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
@Table(name = "hard_drive")
public class HardDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Enumerated(EnumType.STRING)
    @Column(name = "hd_form_factor", length = 30)
    private HDFormFactor formFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hd_type", length = 10)
    private HDType type;

    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "rw_speed")
    private Integer rwSpeed;

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

    public HDFormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(HDFormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public HDType getType() {
        return type;
    }

    public void setType(HDType type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRwSpeed() {
        return rwSpeed;
    }

    public void setRwSpeed(Integer rwSpeed) {
        this.rwSpeed = rwSpeed;
    }
}
