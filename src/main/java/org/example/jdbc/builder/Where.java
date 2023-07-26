package org.example.jdbc.builder;

import java.util.ArrayList;
import java.util.List;
import org.example.jdbc.builder.operator.Operator;

public class Where {

    private final StringBuilder query = new StringBuilder();

    private String baseConditions;
    private List<String> logicalConditions;

    private Where(String baseConditions, List<String> logicalConditions) {
        this.baseConditions = baseConditions;
        this.logicalConditions = logicalConditions;

        makeQuery();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String baseConditions;

        public LogicalConditionBuilder where(Operator operator) {
            this.baseConditions = String.format("WHERE %s %s %s", operator.getLhs(), operator.getOperate(),
                operator.getRhs());

            return new LogicalConditionBuilder(baseConditions);
        }

        public static class LogicalConditionBuilder {

            private String baseConditions;
            private List<String> logicalConditions = new ArrayList<>();

            public LogicalConditionBuilder(String baseConditions) {
                this.baseConditions = baseConditions;
            }

            public LogicalConditionBuilder or(Operator operator) {
                logicalConditions.add(
                    String.format("OR %s %s %s", operator.getLhs(), operator.getOperate(), operator.getRhs()));

                return this;
            }

            public LogicalConditionBuilder and(Operator operator) {
                logicalConditions.add(
                    String.format("AND %s %s %s", operator.getLhs(), operator.getOperate(), operator.getRhs()));

                return this;
            }

            public Where build() {
                return new Where(baseConditions, logicalConditions);
            }
        }

    }

    private void makeQuery() {
        query.append(String.format(baseConditions));

        for (String logicalCondition : logicalConditions) {
            query.append(String.format(" %s", logicalCondition));
        }
    }

    public String getQuery() {
        return query.toString();
    }

}