package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "motherboard")
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false, length = 15)
    @JsonIgnore
    private Socket socket;
    @Enumerated(EnumType.STRING)
    @Column(name = "mb_form_factor", nullable = false, length = 30)
    @JsonIgnore
    private MotherboardFormFactor formFactor;
    @Enumerated(EnumType.STRING)
    @Column(name = "chipset", nullable = false, length = 20)
    @JsonIgnore
    private Chipset chipset;
    @Column(name = "number_of_ram_sockets")
    private Integer num_of_ram;
    @Enumerated(EnumType.STRING)
    @Column(name = "ram_tech", nullable = false, length = 10)
    @JsonIgnore
    private RamTech ramTech;
    @Column(name = "ports")
    private String ports;

    @Transient
    private String socketTitle;
    @Transient
    private String formFactorTitle;
    @Transient
    private String ramTechTitle;
    @Transient
    private String chipsetTitle;


    @PostLoad
    private void postLoad(){
        socketTitle = socket.getTitle();
        formFactorTitle = formFactor.getTitle();
        ramTechTitle = ramTech.getTitle();
        chipsetTitle = chipset.getTitle();
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

    public MotherboardFormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(MotherboardFormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public Chipset getChipset() {
        return chipset;
    }

    public void setChipset(Chipset chipset) {
        this.chipset = chipset;
    }

    public Integer getNum_of_ram() {
        return num_of_ram;
    }

    public void setNum_of_ram(Integer num_of_ram) {
        this.num_of_ram = num_of_ram;
    }

    public RamTech getRamTech() {
        return ramTech;
    }

    public void setRamTech(RamTech ramTech) {
        this.ramTech = ramTech;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getSocketTitle() {
        return socketTitle;
    }

    public void setSocketTitle(String socketTitle) {
        this.socketTitle = socketTitle;
    }

    public String getFormFactorTitle() {
        return formFactorTitle;
    }

    public void setFormFactorTitle(String formFactorTitle) {
        this.formFactorTitle = formFactorTitle;
    }

    public String getRamTechTitle() {
        return ramTechTitle;
    }

    public void setRamTechTitle(String ramTechTitle) {
        this.ramTechTitle = ramTechTitle;
    }

    public String getChipsetTitle() {
        return chipsetTitle;
    }

    public void setChipsetTitle(String chipsetTitle) {
        this.chipsetTitle = chipsetTitle;
    }
}
