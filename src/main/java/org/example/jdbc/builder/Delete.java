package org.example.jdbc.builder;

import org.example.jdbc.builder.constant.Table;

public class Delete {

    private final StringBuilder query = new StringBuilder();

    private Table table;
    private Where where;

    private Delete(Table table, Where where) {
        this.table = table;
        this.where = where;

        makeQuery();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Table table;

        public WhereBuilder delete(Table table) {
            this.table = table;

            return new WhereBuilder(table);
        }

        public static class WhereBuilder {

            private Table table;
            private Where where;

            public WhereBuilder(Table table) {
                this.table = table;
            }

            public EndBuilder where(Where where) {
                this.where = where;

                return new EndBuilder(table, where);
            }

            public Delete build() {
                return new Delete(table, where);
            }

        }

        public static class EndBuilder {

            private Table table;
            private Where where;

            public EndBuilder(Table table, Where where) {
                this.table = table;
                this.where = where;
            }

            public Delete build() {
                return new Delete(table, where);
            }

        }

    }

    private void makeQuery() {
        query.append(String.format("DELETE %s", table));

        if (where != null) {
            query.append(String.format(" %s", where.getQuery()));
        }
    }

    public String getQuery() {
        return query.toString();
    }

}
