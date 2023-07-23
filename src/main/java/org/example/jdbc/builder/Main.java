package org.example.jdbc.builder;

import org.example.jdbc.builder.where.Where;
import org.example.jdbc.builder.where.WhereBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        // where
        Where where = new WhereBuilder().where("VOUCHER_ID")
            .isEqualTo("1")
            .build();

        System.out.println(where.getQuery());

    }
}
