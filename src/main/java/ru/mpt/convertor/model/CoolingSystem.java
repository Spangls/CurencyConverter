package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cooling_system")
public class CoolingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_type", nullable = false, length = 20)
    @JsonIgnore
    private CoolingSystemType type;

    @Column(name = "fan_diam")
    private Integer fanDiameter;
    @Column(name = "fan_count")
    private Integer fanCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false, length = 20)
    @JsonIgnore
    private Socket socket;

    @Transient
    private String typeTitle;
    @Transient
    private String socketTitle;

    @PostLoad
    private void postLoad(){
        typeTitle = type.getTitle();
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

    public CoolingSystemType getType() {
        return type;
    }

    public void setType(CoolingSystemType type) {
        this.type = type;
    }

    public Integer getFanDiameter() {
        return fanDiameter;
    }

    public void setFanDiameter(Integer fanDiameter) {
        this.fanDiameter = fanDiameter;
    }

    public Integer getFanCount() {
        return fanCount;
    }

    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getSocketTitle() {
        return socketTitle;
    }

    public void setSocketTitle(String socketTitle) {
        this.socketTitle = socketTitle;
    }
}
