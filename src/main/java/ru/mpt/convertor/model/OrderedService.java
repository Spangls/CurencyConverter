package ru.mpt.convertor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer_service")
public class OrderedService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "service", nullable = false)
    private Service service;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "active", nullable = false)
    private boolean active;
}
