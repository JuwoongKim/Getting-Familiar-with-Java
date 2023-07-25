package org.example.jdbc.builder.operator;

public class Neq extends Operator{

    private final String NEQ = "!=";

    public Neq(String lhs, String rhs) {
        statement.append(String.format("%s %s %s", lhs, NEQ, rhs));
    }
}
