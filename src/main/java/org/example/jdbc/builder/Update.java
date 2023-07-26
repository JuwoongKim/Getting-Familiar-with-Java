package org.example.jdbc.builder;

import org.example.jdbc.builder.constant.Table;

public class Update {

    private final int REMOVE_SIZE = 2;

    private final StringBuilder query = new StringBuilder();

    private Table table;
    private String[] values;
    private Where where;

    private Update(Table table, String[] values, Where where) {
        this.table = table;
        this.values = values;
        this.where = where;

        makeQuery();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Table table;

        public ValuesBuilder updateInto(Table table) {
            this.table = table;

            return new ValuesBuilder(table);
        }

        public static class ValuesBuilder {

            private Table table;
            private String[] values;

            public ValuesBuilder(Table table) {
                this.table = table;
            }

            public WhereBuilder setValues(String... values) {
                this.values = values;

                return new WhereBuilder(table, values);
            }

        }

        public static class WhereBuilder {

            private Table table;
            private String[] values;
            private Where where;

            public WhereBuilder(Table table, String[] values) {
                this.table = table;
                this.values = values;
            }

            public EndBuilder where(Where where) {
                this.where = where;

                return new EndBuilder(table, values, where);
            }

            public Update build() {
                return new Update(table, values, where);
            }

        }

        public static class EndBuilder {

            private Table table;
            private String[] values;
            private Where where;

            public EndBuilder(Table table, String[] values, Where where) {
                this.table = table;
                this.values = values;
                this.where = where;
            }

            public Update build() {
                return new Update(table, values, where);
            }

        }

    }

    private void makeQuery() {
        query.append(String.format("UPDATE INTO %s (", table));

        for (String value : values) {
            query.append(String.format("%s,", value));
        }

        query.delete(query.length() - REMOVE_SIZE, query.length());
        query.append(")");

        if (where != null) {
            query.append(String.format(" %s", where.getQuery()));
        }
    }

    public String getQuery() {
        return query.toString();
    }
}
