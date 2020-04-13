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
    private Socket socket;
    @Column(name = "mb_form_factor", nullable = false, length = 30)
    private MotherboardFormFactor formFactor;
    @Column(name = "chipset", nullable = false, length = 20)
    private Chipset chipset;
    @Column(name = "number_of_ram_sockets", nullable = true)
    private Integer num_of_ram;
    @Column(name = "ram_tech", nullable = false, length = 10)
    private RamTech ramTech;
    private String ports;

}
