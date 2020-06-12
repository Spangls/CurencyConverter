package ru.mpt.convertor.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "price")
@NoArgsConstructor
@RequiredArgsConstructor
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
}
