package org.example.jdbc.builder;

import org.example.jdbc.builder.constant.Symbols;

public class Where {

    private final String WHERE = "WHERE";
    private final StringBuilder query = new StringBuilder();

    private String lhs;
    private String operator;
    private String rhs;

    public Where(String lhs, String operator, String rhs) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;

        makeQuery();
    }

    public static class Builder {
        private String lhs;
        private String operator;
        private String rhs;

        public Builder where(String lhs) {
            this.lhs = lhs;

            return this;
        }

        public Builder setOperator(String operator) {
            this.operator = operator;

            return this;
        }

        public Builder value(String rhs) {
            this.rhs = rhs;

            return this;
        }

        public Where build() {
            return new Where(lhs, operator, rhs);
        }

    }

    private void makeQuery() {
        query.append(WHERE)
            .append(Symbols.SPACE.getSymbol())
            .append(lhs)
            .append(Symbols.SPACE.getSymbol())
            .append(operator)
            .append(Symbols.SPACE.getSymbol())
            .append(rhs);
    }

    public String getQuery() {
        return query.toString();
    }

}
