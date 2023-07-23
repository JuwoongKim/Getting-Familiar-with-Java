package org.example.jdbc.builder.update;

import org.example.jdbc.builder.constant.Symbols;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class Update {

    private final int REMOVE_SIZE = 1;

    private final String UPDATE = "UPDATE INTO";
    private final StringBuilder query = new StringBuilder();

    private Table table;
    private String[] values;
    private Where where;

    public Update(Table table, String[] values, Where where) {
        this.table = table;
        this.values = values;
        this.where = where;

        makeQuery();
    }

    private void makeQuery() {
        query.append(UPDATE)
            .append(Symbols.SPACE.getSymbol())
            .append(table)
            .append(Symbols.SPACE.getSymbol())
            .append(Symbols.LEFT_PARENTHESES.getSymbol());

        for (String value : values) {
            query.append(value)
                .append(Symbols.COMMA.getSymbol());
        }

        query.delete(query.length() - REMOVE_SIZE, query.length());
        query.append(Symbols.RIGHT_PARENTHESES.getSymbol());

        if (where != null) {
            query.append(Symbols.SPACE.getSymbol())
                .append(where.getQuery());
        }

    }

    public String getQuery() {
        return query.toString();
    }
}
