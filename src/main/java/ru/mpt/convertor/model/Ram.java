package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ram")
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "item_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "frequency")
    private int frequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "tech", nullable = false, length = 10)
    @JsonIgnore
    private RamTech ramTech;

    @Transient
    private String techTitle;

    @PostLoad
    private void postLoad(){
        techTitle = ramTech.getTitle();
    }

    public String getTechTitle() {
        return techTitle;
    }

    public void setTechTitle(String techTitle) {
        this.techTitle = techTitle;
    }
}
