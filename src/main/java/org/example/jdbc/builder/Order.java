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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String columName;

        public SortBuilder orderBy(String colum) {
            this.columName = colum;

            return new SortBuilder(columName);
        }

        public static class SortBuilder {

            private String columName;
            private Sort sort;

            public SortBuilder(String columName) {
                this.columName = columName;
            }

            public EndBuilder setSort(Sort sort) {
                this.sort = sort;

                return new EndBuilder(columName, sort);
            }

            public Order build() {
                return new Order(columName, sort);
            }

        }

        public static class EndBuilder {

            private String columName;
            private Sort sort;

            public EndBuilder(String columName, Sort sort) {
                this.columName = columName;
                this.sort = sort;
            }

            public Order build() {
                return new Order(columName, sort);
            }

        }

    }

    private void makeQuery() {
        query.append(String.format("ORDER BY %s", columName));

        if (sort != null) {
            query.append(String.format(" %s", sort));
        }

    }

    public String getQuery() {
        return query.toString();
    }

}
