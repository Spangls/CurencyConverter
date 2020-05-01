package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    public CaseFormFactor getCaseFormFactor() {
        return caseFormFactor;
    }

    public void setCaseFormFactor(CaseFormFactor caseFormFactor) {
        this.caseFormFactor = caseFormFactor;
    }

    public MotherboardFormFactor getMotherboardFormFactor() {
        return motherboardFormFactor;
    }

    public void setMotherboardFormFactor(MotherboardFormFactor motherboardFormFactor) {
        this.motherboardFormFactor = motherboardFormFactor;
    }

    public CaseWindow getWindow() {
        return window;
    }

    public void setWindow(CaseWindow window) {
        this.window = window;
    }

    public String getWindowTitle() {
        return windowTitle;
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
