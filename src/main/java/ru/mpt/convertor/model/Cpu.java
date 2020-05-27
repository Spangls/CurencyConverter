package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Cpu{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    @NonNull
    private Item  item;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false, length = 15)
    @JsonIgnore
    @NonNull
    private Socket socket;

    @NonNull
    @Column(name = "cores")
    private Integer cores;
    @NonNull
    @Column(name = "flows")
    private Integer flows;
    @NonNull
    @Column(name = "frequency")
    private Float frequency;

    @Transient
    private String socketTitle;

    @PostLoad
    private void postLoad(){
        socketTitle = socket.getTitle();
    }
}
