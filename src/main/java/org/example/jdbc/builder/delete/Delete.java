package org.example.jdbc.builder.delete;

import org.example.jdbc.builder.constant.Symbols;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class Delete {

    private final String DELETE = "DELETE";
    private final StringBuilder query = new StringBuilder();

    private Table table;
    private Where where;

    public Delete(Table table, Where where) {
        this.table = table;
        this.where = where;

        makeQuery();
    }

    private void makeQuery() {
        query.append(DELETE)
            .append(Symbols.SPACE.getSymbol())
            .append(table);

        if (where != null) {
            query.append(Symbols.SPACE.getSymbol())
                .append(where.getQuery());
        }
    }

    public String getQuery() {
        return query.toString();
    }

}
