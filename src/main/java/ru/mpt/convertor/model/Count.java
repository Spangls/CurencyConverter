package ru.mpt.convertor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "count")
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "change", nullable = false)
    private Integer change;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Count(Item item, Integer change) {
        this.item = item;
        this.change = change;
        date = LocalDate.now();
    }
}
