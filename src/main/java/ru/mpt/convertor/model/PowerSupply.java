package ru.mpt.convertor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
}
