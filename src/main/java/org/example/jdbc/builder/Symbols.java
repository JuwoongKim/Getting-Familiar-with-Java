package org.example.jdbc.builder;

public enum Symbols {

    SPACE(" "),
    EQUAL("="),
    LEFT_PARENTHESES("("),
    RIGHT_PARENTHESES(")")
    ;

    private final String symbol;

    Symbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
