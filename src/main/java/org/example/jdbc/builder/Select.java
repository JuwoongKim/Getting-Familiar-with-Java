package org.example.jdbc.builder;

import java.lang.reflect.Field;
import org.example.jdbc.builder.constant.Symbols;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.where.Where;

public class Select<T> {

    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final StringBuilder query = new StringBuilder();

    private T columName;
    private Table table;
    private Where where;
    private Order order;

    private Select(T columName, Table table, Where where, Order order) {
        this.columName = columName;
        this.table = table;
        this.where = where;
        this.order = order;

        makeQuery();
    }

    public static class Builder<T> {
        private T colums;
        private Table table;
        private Where where;
        private Order order;

        public Builder<T> select(T colums) {
            this.colums = colums;

            return this;
        }

        public Builder<T> from(Table table) {
            this.table = table;

            return this;
        }

        public Builder<T> where(Where where) {
            this.where = where;

            return this;
        }

        public Builder<T> orderBy(Order order) {
            this.order = order;

            return this;
        }

        public Select build() {
            return new Select(colums, table, where, order);
        }

    }

    private void makeQuery() {
        query.append(SELECT)
            .append(Symbols.SPACE.getSymbol());

        appendColums();

        query.append(Symbols.SPACE.getSymbol())
            .append(FROM)
            .append(Symbols.SPACE.getSymbol())
            .append(table);

        if (where != null) {
            query.append(Symbols.SPACE.getSymbol())
                .append(where.getQuery());
        }

        if (order != null) {
            query.append(Symbols.SPACE.getSymbol())
                .append(order.getQuery());
        }

    }

    private void appendColums() {
        Class<?> clazz = columName.getClass();
        Field[] fields = clazz.getDeclaredFields();

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
