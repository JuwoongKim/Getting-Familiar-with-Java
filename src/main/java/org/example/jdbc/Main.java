package org.example.jdbc;

import org.example.jdbc.builder.Delete;
import org.example.jdbc.builder.Order;
import org.example.jdbc.builder.Update;
import org.example.jdbc.builder.Where;
import static org.example.jdbc.builder.Order.Sort.*;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.operator.Eq;
import org.example.jdbc.builder.operator.Gt;
import org.example.jdbc.testobj.Person;
import org.example.jdbc.builder.Select;

public class Main {

    public static void main(String[] args) throws Exception {

        // where
        Where where = new Where.Builder()
            .where(new Eq("DATE(order_date)","2023-07-24" ))
            .and(new Gt("VOUCHER_ID","12"))
            .or(new Eq("WALLET_ID","30"))
            .build();

        System.out.println(where.getQuery());

        // order by
        Order order = new Order.Builder()
            .orderBy("VOUCHER_ID")
            .setSortOrder(ASC)
            .build();

        System.out.println(order.getQuery());

        // select
        Select select = new Select.Builder<>()
            .select(new Person(1, "juwoong", 20))
            .from(Table.ANIMAL)
            .where(where)
            .build();

        System.out.println(select.getQuery());

        // update
        Update update = new Update.Builder()
            .updateInto(Table.ANIMAL)
            .setValues("dog", "1", "etc")
            .where(where)
            .build();

        System.out.println(update.getQuery());

        // delete
        Delete delete = new Delete.Builder()
            .delete(Table.ANIMAL)
            .where(where)
            .build();

        System.out.println(delete.getQuery());

    }
}
