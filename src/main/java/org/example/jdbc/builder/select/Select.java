package org.example.jdbc.builder.select;

import org.example.jdbc.builder.constant.Symbols;
import org.example.jdbc.builder.order.Order;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class Select {

    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final StringBuilder query = new StringBuilder();

    private String columName;
    private Table table;
    private Where where;
    private Order order;

    public Select(String columName, Table table, Where where, Order order) {
        this.columName = columName;
        this.table = table;
        this.where = where;
        this.order = order;
        makeQuery();
    }

    private void makeQuery() {
        query.append(SELECT)
            .append(Symbols.SPACE.getSymbol())
            .append(columName)
            .append(Symbols.SPACE.getSymbol())
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

    public String getQuery() {
        return query.toString();
    }

}
