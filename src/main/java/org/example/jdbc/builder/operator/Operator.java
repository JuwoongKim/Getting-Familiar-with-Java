package org.example.jdbc.builder.operator;

public class Operator {

    private final String lhs;
    private final String operate;
    private final String rhs;

    public Operator(String lhs, String operate, String rhs) {
        this.lhs = lhs;
        this.operate = operate;
        this.rhs = rhs;
    }

    public String getLhs() {
        return lhs;
    }

    public String getOperate() {
        return operate;
    }

    public String getRhs() {
        return rhs;
    }
}
