package org.example.jdbc.builder.update;

import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class UpdateBuilder {

    private Table table;
    private String[] values;
    private Where where;

    public UpdateBuilder updateInto(Table table) {
        this.table = table;

        return this;
    }

    public UpdateBuilder setValues(String... values ) {
        this.values = values;

        return this;
    }

    public UpdateBuilder where(Where where) {
        this.where = where;

        return this;
    }

    public Update build() {
        return new Update(table, values, where);
    }

}
