package ru.mpt.convertor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@Table(name = "ordered_item")
public class OrderedItem implements Serializable {

    @Getter
    @Setter
    @EmbeddedId
    private OrderedItemId pk = new OrderedItemId();

    @JsonIgnore
    @Getter
    @MapsId("orderId")
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @Getter
    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;

    @Getter
    @Setter
    @Column(name = "quantity")
    private int quantity;

    public void setOrder(Order order) {
        this.order = order;
        getPk().setOrderId(order.getId());
    }

    public void setItem(Item item) {
        this.item = item;
        getPk().setItemId(item.getId());
    }
}
