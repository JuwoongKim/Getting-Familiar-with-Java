package org.example.jdbc.builder.operator;

public class Gt extends Operator {

    private static final String GT = ">=";

    public Gt(String lhs, String rhs) {
        super(lhs, GT, rhs);
    }

}
