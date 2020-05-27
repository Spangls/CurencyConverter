package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufactorer_id", nullable = false)
    @NonNull
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false, length = 10)
    @NonNull
    private ItemType type;

    @Column(name = "weight")
    private Float weight;
    @Column(name = "size", length = 50)
    private String size;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Price> prices;
    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<Count> counts;
    @OneToMany(mappedBy = "item")
    private Set<Picture> pictures;

    @Transient
    private Float price = 0f;
    @Transient
    private Integer count = 0;

    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Cpu cpu;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private CoolingSystem coolingSystem;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Case _case;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Gpu gpu;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private HardDrive hardDrive;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Motherboard motherboard;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private PowerSupply powerSupply;
    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Ram ram;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private Set<FollowedItem> followedItems;

    @Transient
    private boolean followed = false;

    @PostLoad
    private void postLoad() {
        price = Collections.max(prices, Comparator.comparing(Price::getDate)).getPrice();
        for (Count value : counts) {
            count += value.getChange();
        }
        if (price == null) price = 0f;
    }

    public FollowedItem findFollowByUser(User user){
        return followedItems.stream().filter(followedItem -> followedItem.getUser() == user).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(manufacturer, item.manufacturer) &&
                type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, type);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer=" + manufacturer +
                ", type=" + type +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
