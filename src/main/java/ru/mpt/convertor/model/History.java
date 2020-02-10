package ru.mpt.convertor.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_currency_id")
    private Currency sourceCurrency;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_currency_id")
    private Currency targetCurrency;
    @Column(name = "source_currency_amount")
    private Float sourceCurrencyAmount;
    @Column(name = "target_currency_amount")
    private Float targetCurrencyAmount;

    public History() {
    }

    public History(Currency sourceCurrency, float sourceCurrencyAmount, Currency targetCurrency, float targetCurrencyAmount){
        this.sourceCurrency = sourceCurrency;
        this.sourceCurrencyAmount = sourceCurrencyAmount;
        this.targetCurrency = targetCurrency;
        this.targetCurrencyAmount = targetCurrencyAmount;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Float getSourceCurrencyAmount() {
        return sourceCurrencyAmount;
    }

    public void setSourceCurrencyAmount(Float sourceCurrencyAmount) {
        this.sourceCurrencyAmount = sourceCurrencyAmount;
    }

    public Float getTargetCurrencyAmount() {
        return targetCurrencyAmount;
    }

    public void setTargetCurrencyAmount(Float targetCurrencyAmount) {
        this.targetCurrencyAmount = targetCurrencyAmount;
    }
}
