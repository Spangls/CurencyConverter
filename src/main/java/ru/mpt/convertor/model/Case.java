package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
@Table(name = "cse")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item  item;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_form_factor", length = 30)
    private CaseFormFactor caseFormFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "mb_form_factor", nullable = false, length = 30)
    private MotherboardFormFactor motherboardFormFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "window_material", length = 20)
    private CaseWindow window;

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
}
