package org.example.jdbc;

import org.example.jdbc.builder.constant.SortOrder;
import org.example.jdbc.builder.order.Order;
import org.example.jdbc.builder.order.OrderBuilder;
import org.example.jdbc.builder.select.Select;
import org.example.jdbc.builder.select.SelectBuilder;
import org.example.jdbc.builder.table.Table;
import org.example.jdbc.builder.update.Update;
import org.example.jdbc.builder.update.UpdateBuilder;
import org.example.jdbc.builder.where.Where;
import org.example.jdbc.builder.where.WhereBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        // where
        Where where = new WhereBuilder().where("VOUCHER_ID")
            .isEqualTo("1")
            .build();

        System.out.println(where.getQuery());

        // order by
        Order order = new OrderBuilder().orderBy("VOUCHER_ID")
            .setSortOrder(SortOrder.DESC)
            .build();

        System.out.println(order.getQuery());

        // select
        Select select = new SelectBuilder().select("*")
            .from(Table.PERSON)
            .where(where)
            .build();

        System.out.println(select.getQuery());

        // update
        Update update = new UpdateBuilder().updateInto(Table.ANIMAL)
            .setValues("dog", "1", "etc")
            .where(where)
            .build();

        System.out.println(update.getQuery());

    }
}
