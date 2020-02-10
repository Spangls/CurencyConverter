package ru.mpt.convertor.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;
    private LocalDate date;
    private Float value;

    public Rate() {
    }

    public Rate(Currency currency, Float value) {
        this.currency = currency;
        this.date = LocalDate.now();
        this.value = value;
    }

    public Rate(Currency currency, String value) {
        this(currency, Float.parseFloat(value.replace(",", ".")));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
