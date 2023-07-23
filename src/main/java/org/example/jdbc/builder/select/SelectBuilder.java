package org.example.jdbc.builder.select;

import org.example.jdbc.builder.order.Order;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class SelectBuilder {

    private String value;
    private Table table;
    private Where where;
    private Order order;

    public SelectBuilder select(String value) {
        this.value = value;
        return this;
    }

    public SelectBuilder from(Table table) {
        this.table = table;

        return this;
    }

    public SelectBuilder where(Where where) {
        this.where = where;

        return this;
    }

    public SelectBuilder orderBy(Order order) {
        this.order = order;

        return this;
    }

    public Select build() {
        return new Select(value, table, where, order);

    }

}
