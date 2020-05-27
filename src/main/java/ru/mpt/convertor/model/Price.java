package ru.mpt.convertor.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
public class Price{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    private Item item;

    @Column(name = "price")
    @NonNull
    private Float price;
    @Column(name = "date")
    @NonNull
    private LocalDate date;

    public Price(Item item, Float price) {
        this.item =  item;
        this.price  = price;
        this.date = LocalDate.now();
    }
}
