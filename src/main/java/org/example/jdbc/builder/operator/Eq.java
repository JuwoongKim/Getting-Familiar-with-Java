package org.example.jdbc.builder.operator;

public class Eq extends Operator {

    private final String EQ = "=";

    public Eq(String lhs, String rhs) {
        statement.append(String.format("%s %s %s", lhs, EQ, rhs));
    }
}
