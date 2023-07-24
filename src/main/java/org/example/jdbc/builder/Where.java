package org.example.jdbc.builder;

import java.util.ArrayList;
import java.util.List;

import org.example.jdbc.builder.constant.Operator;
import org.example.jdbc.builder.constant.Symbols;

public class Where {

    private final String WHERE = "WHERE";
    private final StringBuilder query = new StringBuilder();

    private String lhs;
    private String operator;
    private String rhs;
    private List<String> logicalConditions;

    public Where(String lhs, String operator, String rhs, List<String> logicalConditions) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
        this.logicalConditions = logicalConditions;

        makeQuery();
    }

    public static class Builder {
        private final String OR = "OR";
        private final String AND = "AND";

        private String lhs;
        private String operator;
        private String rhs;
        private List<String> logicalConditions = new ArrayList<>();

        public Builder where(String lhs) {
            this.lhs = lhs;

            return this;
        }

        public Builder setOperator(String operator) {
            this.operator = operator;

            return this;
        }

        public Builder or(String lhs, Operator operator, String rhs) {
            StringBuilder statement = new StringBuilder();

            statement.append(OR)
                .append(Symbols.SPACE.getSymbol())
                .append(lhs)
                .append(Symbols.SPACE.getSymbol())
                .append(operator.getSymbol())
                .append(Symbols.SPACE.getSymbol())
                .append(rhs);

            logicalConditions.add(statement.toString());

            return this;
        }

        public Builder and(String lhs, Operator operator, String rhs) {
            StringBuilder statement = new StringBuilder();

            statement.append(AND)
                .append(Symbols.SPACE.getSymbol())
                .append(lhs)
                .append(Symbols.SPACE.getSymbol())
                .append(operator.getSymbol())
                .append(Symbols.SPACE.getSymbol())
                .append(rhs);

            logicalConditions.add(statement.toString());

            return this;
        }

        public Builder value(String rhs) {
            this.rhs = rhs;

            return this;
        }

        public Where build() {
            return new Where(lhs, operator, rhs, logicalConditions);
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

        for (String logicalCondition : logicalConditions) {
            query.append(Symbols.SPACE.getSymbol())
                .append(logicalCondition);
        }
    }

    public String getQuery() {
        return query.toString();
    }

}
