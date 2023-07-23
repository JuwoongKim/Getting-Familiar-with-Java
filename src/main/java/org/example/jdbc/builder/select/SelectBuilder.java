package org.example.jdbc.builder.select;

import org.example.jdbc.builder.order.Order;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class SelectBuilder<T> {

    private T colums;
    private Table table;
    private Where where;
    private Order order;

    public SelectBuilder<T> select(T colums) {
        this.colums = colums;
        return this;
    }

    public SelectBuilder<T> from(Table table) {
        this.table = table;

        return this;
    }

    public SelectBuilder<T> where(Where where) {
        this.where = where;

        return this;
    }

    public SelectBuilder<T> orderBy(Order order) {
        this.order = order;

        return this;
    }

    public Select build() {
        return new Select(colums, table, where, order);

    }

}
