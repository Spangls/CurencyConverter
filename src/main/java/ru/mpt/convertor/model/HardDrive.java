package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hard_drive")
public class HardDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "hd_form_factor", length = 30)
    @JsonIgnore
    private HDFormFactor formFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hd_type", length = 10)
    @JsonIgnore
    private HDType type;

    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "rw_speed")
    private Integer rwSpeed;

    @Transient
    private String formFactorTitle;
    @Transient
    private String typeTitle;

    @PostLoad
    private void postLoad(){
        typeTitle = type.getTitle();
        formFactorTitle = formFactor.getTitle();
    }

    public String getFormFactorTitle() {
        return formFactorTitle;
    }

    public void setFormFactorTitle(String formFactorTitle) {
        this.formFactorTitle = formFactorTitle;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
}
