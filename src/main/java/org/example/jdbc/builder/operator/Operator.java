package org.example.jdbc.builder.operator;

public class Operator {

    protected final StringBuilder statement =new StringBuilder();

    public String getStatement() {
        return statement.toString();
    }
}
