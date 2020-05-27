package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cooling_system")
public class CoolingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

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
    private void postLoad() {
        typeTitle = type.getTitle();
        socketTitle = socket.getTitle();
    }
}
