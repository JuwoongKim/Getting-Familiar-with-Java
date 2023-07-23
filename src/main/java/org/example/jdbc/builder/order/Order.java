package org.example.jdbc.builder.order;

import org.example.jdbc.builder.constant.SortOrder;
import org.example.jdbc.builder.constant.Symbols;

public class Order {

    private final String ORDER_BY = "ORDER BY";
    private final StringBuilder query = new StringBuilder();

    private String columName;
    private SortOrder sortOrder;

    public Order(String columName, SortOrder sortOrder) {
        this.columName = columName;
        this.sortOrder = sortOrder;
        makeQuery();
    }

    private void makeQuery() {
        query.append(ORDER_BY)
            .append(Symbols.SPACE.getSymbol())
            .append(columName)
            .append(Symbols.SPACE.getSymbol())
            .append(sortOrder);
    }

    public String getQuery() {
        return query.toString();
    }

}
