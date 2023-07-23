package org.example.jdbc.builder.order;

import org.example.jdbc.builder.constant.SortOrder;

public class OrderBuilder {

    private String columName;
    private SortOrder sortOrder;

    public OrderBuilder orderBy(String colum) {
        this.columName = colum;

        return this;
    }

    public OrderBuilder setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;

        return this;
    }

    public Order build() {
        return new Order(columName, sortOrder);
    }

}
