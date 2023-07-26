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

        public FromBuilder select(Class columns) {
            this.columns = columns;
            return new FromBuilder(columns);
        }

        public static class FromBuilder {

            private Class columns;
            private Table table;

            public FromBuilder(Class columns) {
                this.columns = columns;
            }

            public WhereBuilder from(Table tables) {
                this.table = tables;
                return new WhereBuilder(columns, table);
            }

        }

        public static class WhereBuilder {

            private Class columns;
            private Table table;
            private Where where;
            private Order order;

            public WhereBuilder(Class columns, Table table) {
                this.columns = columns;
                this.table = table;
            }

            public OrderBuilder where(Where where) {
                this.where = where;

                return new OrderBuilder(columns, table, where);
            }

            public Select build() {
                return new Select(columns, table, where, order);
            }

        }

        public static class OrderBuilder {

            private Class columns;
            private Table table;
            private Where where;
            private Order order;

            public OrderBuilder(Class columns, Table table, Where where) {
                this.columns = columns;
                this.table = table;
                this.where = where;
            }

            public EndBuilder orderBy(Order order) {
                this.order = order;

                return new EndBuilder(columns, table, where, order);
            }

            public Select build() {
                return new Select(columns, table, where, order);
            }
        }

        public static class EndBuilder {

            private Class columns;
            private Table table;
            private Where where;
            private Order order;

            public EndBuilder(Class columns, Table table, Where where, Order order) {
                this.columns = columns;
                this.table = table;
                this.where = where;
                this.order = order;
            }

            public Select build() {
                return new Select(columns, table, where, order);
            }
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
