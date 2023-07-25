package org.example.jdbc.builder.operator;

public class Eq extends Operator {

    private static final String EQ = "=";

    public Eq(String lhs, String rhs) {
        super(lhs, EQ, rhs);
    }

}
