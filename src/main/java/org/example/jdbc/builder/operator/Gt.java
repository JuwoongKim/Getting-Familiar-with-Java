package org.example.jdbc.builder.operator;

public class Gt extends Operator {

    private final String GT = ">=";

    public Gt(String lhs, String rhs) {
        statement.append(String.format("%s %s %s", lhs, GT, rhs));
    }
}
