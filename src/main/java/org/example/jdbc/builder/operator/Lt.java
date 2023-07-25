package org.example.jdbc.builder.operator;

public class Lt extends Operator {

    private final String LT = "<=";

    public Lt(String lhs, String rhs) {
        statement.append(String.format("%s %s %s", lhs, LT, rhs));
    }
}
