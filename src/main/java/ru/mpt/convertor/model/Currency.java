package ru.mpt.convertor.model;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "char_code")
    private String charCode;
    @Column(name = "num_code")
    private String numCode;
    private Integer nominal;

    public Currency() {
    }

    public Currency(String name, String charCode, String numCode, Integer nominal){
        this.name = name;
        this.charCode = charCode;
        this.numCode = numCode;
        this.nominal = nominal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    @Override
    public String toString() {
        return id+name;
    }
}
