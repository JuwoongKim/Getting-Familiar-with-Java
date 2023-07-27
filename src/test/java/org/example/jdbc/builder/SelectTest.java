package org.example.jdbc.builder;

import static org.example.jdbc.builder.Order.Sort.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.operator.Eq;
import org.example.jdbc.builder.operator.Gt;
import org.example.jdbc.testobj.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SelectTest {

    @Test
    @DisplayName("SELECT 절 생성 테스트")
    void SelectTest() {
        // given when
        Select select = Select.builder()
            .select(Person.class)
            .from(Table.ANIMAL)
            .build();

        // then
        assertEquals("SELECT ID, NAME, AGE FROM ANIMAL", select.getQuery());
    }

    @Test
    @DisplayName("SELECT 절 WHERE 조건 추가 생성 테스트")
    void SelectWithWhereTest() {
        // given when
        Where where = Where.builder()
            .where(new Eq("VOUCHER_TYPE", "WELCOME"))
            .and(new Gt("CUSTOMER_AGE", "20"))
            .or(new Eq("CUSTOMER_TYPE", "VIP"))
            .build();

        Order order = Order.builder()
            .orderBy("VOUCHER_ID")
            .setSort(ASC)
            .build();

        Select select = Select.builder()
            .select(Person.class)
            .from(Table.ANIMAL)
            .where(where)
            .build();

        // then
        assertEquals(
            "SELECT ID, NAME, AGE FROM ANIMAL WHERE VOUCHER_TYPE = WELCOME AND CUSTOMER_AGE >= 20 OR CUSTOMER_TYPE = VIP",
            select.getQuery());
    }

    @Test
    @DisplayName("SELECT 절 WHERE 조건 추가 ORDER 조건 추가 생성 테스트")
    void SelectWithWhereWithOrderTest() {
        // given when
        Where where = Where.builder()
            .where(new Eq("VOUCHER_TYPE", "WELCOME"))
            .and(new Gt("CUSTOMER_AGE", 20))
            .or(new Eq("CUSTOMER_TYPE", "VIP"))
            .build();

        Order order = Order.builder()
            .orderBy("VOUCHER_ID")
            .setSort(ASC)
            .build();

        Select select = Select.builder()
            .select(Person.class)
            .from(Table.ANIMAL)
            .where(where)
            .orderBy(order)
            .build();

        // then
        assertEquals(
            "SELECT ID, NAME, AGE FROM ANIMAL WHERE VOUCHER_TYPE = WELCOME AND CUSTOMER_AGE >= 20 OR CUSTOMER_TYPE = VIP ORDER BY VOUCHER_ID ASC",
            select.getQuery());
    }

}