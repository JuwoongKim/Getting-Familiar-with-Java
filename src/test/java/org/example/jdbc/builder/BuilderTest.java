package org.example.jdbc.builder;

import static org.example.jdbc.builder.Order.Sort.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.operator.Eq;
import org.example.jdbc.builder.operator.Gt;
import org.example.jdbc.testobj.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuilderTest {

    @DisplayName("WHERE 절 생성 테스트")
    @Test
    void WhereTest() {
        // given when
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_ID", "1"))
            .build();

        // then
        assertEquals("WHERE VOUCHER_ID = 1", where.getQuery());
    }

    @DisplayName("WHERE 절 논리 조건문 추가 생성 테스트")
    @Test
    void WhereWithLogicalConditionTest() {
        // given when
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_TYPE", "WELCOME"))
            .and(new Gt("CUSTOMER_AGE", "20"))
            .or(new Eq("CUSTOMER_TYPE", "VIP"))
            .build();

        // then
        assertEquals("WHERE VOUCHER_TYPE = WELCOME AND CUSTOMER_AGE >= 20 OR CUSTOMER_TYPE = VIP", where.getQuery());
    }

    @DisplayName("ORDER 절 생성 테스트")
    @Test
    void OrderTest() {
        // given when
        Order order = new Order.Builder()
            .orderBy("VOUCHER_ID")
            .setSortOrder(ASC)
            .build();

        // then
        assertEquals("ORDER BY VOUCHER_ID ASC", order.getQuery());
    }

    @Test
    @DisplayName("SELECT 절 생성 테스트")
    void SelectTest() {
        // given when
        Select select = new Select.Builder<>()
            .select(new Person(1, "juwoong", 20))
            .from(Table.ANIMAL)
            .build();

        // then
        assertEquals("SELECT ID, NAME, AGE FROM ANIMAL", select.getQuery());
    }

    @Test
    @DisplayName("SELECT 절 WHERE 조건 추가 생성 테스트")
    void SelectWithWhereTest() {
        // given when
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_TYPE", "WELCOME"))
            .and(new Gt("CUSTOMER_AGE", "20"))
            .or(new Eq("CUSTOMER_TYPE", "VIP"))
            .build();

        Order order = new Order.Builder()
            .orderBy("VOUCHER_ID")
            .setSortOrder(ASC)
            .build();

        Select select = new Select.Builder<>()
            .select(new Person(1, "juwoong", 20))
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
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_TYPE", "WELCOME"))
            .and(new Gt("CUSTOMER_AGE", "20"))
            .or(new Eq("CUSTOMER_TYPE", "VIP"))
            .build();

        Order order = new Order.Builder()
            .orderBy("VOUCHER_ID")
            .setSortOrder(ASC)
            .build();

        Select select = new Select.Builder<>()
            .select(new Person(1, "juwoong", 20))
            .from(Table.ANIMAL)
            .where(where)
            .orderBy(order)
            .build();

        // then
        assertEquals(
            "SELECT ID, NAME, AGE FROM ANIMAL WHERE VOUCHER_TYPE = WELCOME AND CUSTOMER_AGE >= 20 OR CUSTOMER_TYPE = VIP ORDER BY VOUCHER_ID ASC",
            select.getQuery());
    }

    @Test
    @DisplayName("UPDATE 절 생성 테스트")
    void UpdateTest() {
        // given when
        Update update = new Update.Builder()
            .updateInto(Table.ANIMAL)
            .setValues("dog", "1", "etc")
            .build();

        // then
        assertEquals("UPDATE INTO ANIMAL (dog,1,et)", update.getQuery());
    }

    @Test
    @DisplayName("UPDATE 절 WHERE 조건 추가 생성 테스트")
    void UpdateWithWhereTest() {
        // given when
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_ID", "1"))
            .build();

        Update update = new Update.Builder()
            .updateInto(Table.ANIMAL)
            .setValues("dog", "1", "etc")
            .where(where)
            .build();

        // then
        assertEquals("UPDATE INTO ANIMAL (dog,1,et) WHERE VOUCHER_ID = 1", update.getQuery());
    }

    @Test
    @DisplayName("DELETE 절 생성 테스트")
    void DeleteTest() {
        // given when
        Delete delete = new Delete.Builder()
            .delete(Table.ANIMAL)
            .build();

        // then
        assertEquals("DELETE ANIMAL", delete.getQuery());
    }

    @Test
    @DisplayName("DELETE 절 WHERE 조건 추가 생성 테스트")
    void DeleteWithWhereTest() {
        // given when
        Where where = new Where.Builder()
            .where(new Eq("VOUCHER_ID", "1"))
            .build();

        Delete delete = new Delete.Builder()
            .delete(Table.ANIMAL)
            .where(where)
            .build();

        // then
        assertEquals("DELETE ANIMAL WHERE VOUCHER_ID = 1", delete.getQuery());
    }

}