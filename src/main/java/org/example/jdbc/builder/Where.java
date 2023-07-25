package org.example.jdbc.builder;

import java.util.ArrayList;
import java.util.List;

import org.example.jdbc.builder.operator.Operator;

public class Where {

    private final StringBuilder query = new StringBuilder();

    public String baseCondition;
    private List<String> logicalConditions;

    private Where(String baseCondition, List<String> logicalConditions) {
        this.baseCondition = baseCondition;
        this.logicalConditions = logicalConditions;

        makeQuery();
    }

    public static class Builder {

        private String baseCondition;
        private List<String> logicalConditions = new ArrayList<>();

        public Builder where(Operator operator) {
            this.baseCondition = String.format("WHERE %s", operator.getStatement());

            return this;
        }

        public Builder or(Operator operator) {
            logicalConditions.add(String.format("OR %s", operator.getStatement()));

            return this;
        }

        public Builder and(Operator operator) {
            logicalConditions.add(String.format("AND %s", operator.getStatement()));

            return this;
        }

        public Where build() {
            return new Where(baseCondition, logicalConditions);
        }

    }

    private void makeQuery() {
        query.append(baseCondition);

        for (String logicalCondition : logicalConditions) {
            query.append(String.format(" %s", logicalCondition));
        }
    }

    public String getQuery() {
        return query.toString();
    }

}
