package org.example.jdbc.builder.constant;

import java.util.Arrays;

public enum Operator {

    EQ("="),
    NEQ("!="),
    LT("<="),
    GT(">=")
    ;

    private final String symbol;

    private Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operator from(String symbol) {
        return Arrays.stream(Operator.values())
            .filter(op -> op.symbol.equals(symbol))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown symbol: " + symbol));

    }

}
