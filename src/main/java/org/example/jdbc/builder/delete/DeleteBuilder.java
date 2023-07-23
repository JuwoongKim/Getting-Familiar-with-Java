package org.example.jdbc.builder.delete;

import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.where.Where;

public class DeleteBuilder {

    private Table table;
    private Where where;

    public DeleteBuilder delete(Table table) {
        this.table = table;

        return this;
    }

    public DeleteBuilder where(Where where) {
        this.where = where;

        return this;
    }

    public Delete build() {
        return new Delete(table, where);
    }

}
