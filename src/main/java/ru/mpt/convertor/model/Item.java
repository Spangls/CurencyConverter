package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufactorer_id", nullable = false)
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false, length = 10)
    private ItemType type;

    @Column(name = "weight")
    private Float weight;
    @Column(name = "size", length = 50)
    private String size;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<Price> prices;
    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<Count> counts;

    @Transient
    private Float price;
    @Transient
    private Integer count;

    @PostLoad
    private void postLoad(){
        price = Collections.min(prices, Comparator.comparing(Price::getDate)).getPrice();
        counts.forEach(entry -> count += entry.getChange());
        if (count == null) count = 0;
        if (price == null) price = 0f;
    }

    public Set<Count> getCounts() {
        return counts;
    }

    public void setCounts(Set<Count> counts) {
        this.counts = counts;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }



    public Item() {
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
                ", prices=" + prices +
                ", counts=" + counts +
                ", price=" + price +
                ", count=" + count +
                '}';
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
}
