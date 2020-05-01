package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
@Table(name = "power_supply")
public class PowerSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    @Column(name = "power")
    private Integer power;
    @Column(name = "sata_count")
    private Integer sataCount;
    @Column(name = "pcie_8_count")
    private Integer pcie8Count;
    @Column(name = "pcie_6_count")
    private Integer pcie6Count;

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

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getSataCount() {
        return sataCount;
    }

    public void setSataCount(Integer sataCount) {
        this.sataCount = sataCount;
    }

    public Integer getPcie8Count() {
        return pcie8Count;
    }

    public void setPcie8Count(Integer pcie8Count) {
        this.pcie8Count = pcie8Count;
    }

    public Integer getPcie6Count() {
        return pcie6Count;
    }

    public void setPcie6Count(Integer pcie6Count) {
        this.pcie6Count = pcie6Count;
    }
}
