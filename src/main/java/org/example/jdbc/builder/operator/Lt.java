package org.example.jdbc.builder.operator;

public class Lt extends Operator {

    private static final String LT = ">=";

    public Lt(String lhs, String rhs) {
        super(lhs, LT, rhs);
    }

}
