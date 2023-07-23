package org.example.jdbc.builder.select;

import java.lang.reflect.Field;
import org.example.jdbc.builder.constant.Symbols;
import org.example.jdbc.builder.order.Order;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class Select<T> {

    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final StringBuilder query = new StringBuilder();

    private T columName;
    private Table table;
    private Where where;
    private Order order;

    public Select(T columName, Table table, Where where, Order order) {
        this.columName = columName;
        this.table = table;
        this.where = where;
        this.order = order;

        makeQuery();
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
