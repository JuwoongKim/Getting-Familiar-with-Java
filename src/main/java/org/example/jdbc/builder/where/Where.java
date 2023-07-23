package org.example.jdbc.builder.where;

import org.example.jdbc.builder.Symbols;

public class Where {

    private final String WHERE = "WHERE";
    private final StringBuilder query = new StringBuilder();

    private String columName;
    private String condition;

    public Where(String columName, String condition) {
        this.columName = columName;
        this.condition = condition;
    }

    public String getQuery() {

        query.append(WHERE)
            .append(Symbols.SPACE.getSymbol())
            .append(columName)
            .append(Symbols.SPACE.getSymbol())
            .append(Symbols.EQUAL.getSymbol())
            .append(Symbols.SPACE.getSymbol())
            .append(condition);

        return query.toString();
    }

}
