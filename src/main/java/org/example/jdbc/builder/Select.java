package org.example.jdbc.builder;

import java.lang.reflect.Field;
import org.example.jdbc.builder.constant.Table;

public class Select {

    private final StringBuilder query = new StringBuilder();

    private Class columns;
    private Table table;
    private Where where;
    private Order order;

    private Select(Class columns, Table table, Where where, Order order) {
        this.columns = columns;
        this.table = table;
        this.where = where;
        this.order = order;

        makeQuery();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Class columns;
        private Table table;
        private Where where;
        private Order order;

        public Builder select(Class columns) {
            this.columns = columns;

            return this;
        }

        public Builder from(Table table) {
            this.table = table;

            return this;
        }

        public Builder where(Where where) {
            this.where = where;

            return this;
        }

        public Builder orderBy(Order order) {
            this.order = order;

            return this;
        }

        public Select build() {
            return new Select(columns, table, where, order);
        }

    }

    private void makeQuery() {
        query.append("SELECT ");
        appendColums();
        query.append(String.format(" FROM %s", table));

        if (where != null) {
            query.append(String.format(" %s", where.getQuery()));
        }

        if (order != null) {
            query.append(String.format(" %s", order.getQuery()));
        }

    }

    private void appendColums() {
        Field[] fields = columns.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            String fieldName = field.getName().toUpperCase();
            query.append(fieldName);

            if (i < fields.length - 1) {
                query.append(", ");
            }
        }

    }

    public String getQuery() {
        return query.toString();
    }

}
