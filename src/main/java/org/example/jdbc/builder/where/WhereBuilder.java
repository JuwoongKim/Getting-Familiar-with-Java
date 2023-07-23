package org.example.jdbc.builder.where;

public class WhereBuilder {

    private String columName;
    private String condition;

    public WhereBuilder where(String columName) {
        this.columName = columName;

        return this;
    }

    public WhereBuilder isEqualTo(String condition) {
        this.condition = condition;

        return this;
    }

    public Where build() {
        return new Where(columName, condition);
    }

}
