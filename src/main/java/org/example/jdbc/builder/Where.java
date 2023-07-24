package org.example.jdbc.builder;

import java.util.ArrayList;
import java.util.List;
import org.example.jdbc.builder.constant.Operator;

public class Where {

    private final StringBuilder query = new StringBuilder();

    private String lhs;
    private String operator;
    private String rhs;
    private List<String> logicalConditions;

    private Where(String lhs, String operator, String rhs, List<String> logicalConditions) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
        this.logicalConditions = logicalConditions;

        makeQuery();
    }

    public static class Builder {

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
            logicalConditions.add(String.format("OR %s %s %s", lhs, operator.getSymbol(), rhs));

            return this;
        }

        public Builder and(String lhs, Operator operator, String rhs) {
            logicalConditions.add(String.format("AND %s %s %s", lhs, operator.getSymbol(), rhs));

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
        query.append(String.format("WHERE %s %s %s", lhs, operator, rhs));

        for (String logicalCondition : logicalConditions) {
            query.append(String.format(" %s", logicalCondition));
        }
    }

    public String getQuery() {
        return query.toString();
    }

}
