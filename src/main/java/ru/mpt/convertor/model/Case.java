package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cse")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_form_factor", length = 30)
    @JsonIgnore
    private CaseFormFactor caseFormFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "mb_form_factor", nullable = false, length = 30)
    @JsonIgnore
    private MotherboardFormFactor motherboardFormFactor;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Column(name = "window_material", length = 20)
    private CaseWindow window;

    @Transient
    private String windowTitle;
    @Transient
    private String cffTitle;
    @Transient
    private String mffTitle;

    @PostLoad
    private void postLoad(){
        windowTitle = window.getTitle();
        cffTitle = caseFormFactor.getTitle();
        mffTitle = motherboardFormFactor.getTitle();
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getCffTitle() {
        return cffTitle;
    }

    public void setCffTitle(String cffTitle) {
        this.cffTitle = cffTitle;
    }

    public String getMffTitle() {
        return mffTitle;
    }

    public void setMffTitle(String mffTitle) {
        this.mffTitle = mffTitle;
    }
}
