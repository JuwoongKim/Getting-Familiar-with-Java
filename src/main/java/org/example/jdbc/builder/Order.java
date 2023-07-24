package org.example.jdbc.builder;

public class Order {

    public static enum Sort {
        ASC,
        DESC;
    }

    private final StringBuilder query = new StringBuilder();

    private String columName;
    private Sort sort;

    private Order(String columName, Sort sort) {
        this.columName = columName;
        this.sort = sort;

        makeQuery();
    }

    public static class Builder {

        private String columName;
        private Sort sort;

        public Builder orderBy(String colum) {
            this.columName = colum;

            return this;
        }

        public Builder setSortOrder(Sort sort) {
            this.sort = sort;

            return this;
        }

        public Order build() {
            return new Order(columName, sort);
        }

    }

    private void makeQuery() {
        query.append(String.format("ORDER BY %s %s", columName, sort));
    }

    public String getQuery() {
        return query.toString();
    }

}
