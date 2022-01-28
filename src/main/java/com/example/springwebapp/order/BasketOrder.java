package com.example.springwebapp.order;

import javax.persistence.*;
import java.util.Map;

@Entity
public class BasketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long cityId;
    @ElementCollection
    @CollectionTable(name = "order_item_mapping",
            joinColumns = {@JoinColumn(name = "basket_order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "product_count")
    private Map<Long, Integer> productIdList;
    private Long orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Map<Long, Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(Map<Long, Integer> productIdList) {
        this.productIdList = productIdList;
    }

    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }
}
