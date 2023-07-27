package org.example.jdbc.builder.operator;

import static org.junit.jupiter.api.Assertions.*;
import org.example.jdbc.builder.Select;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.testobj.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OperatorTest {

    @DisplayName("Operator 인스턴스 생성시 value 인자로 Integer 타입을 받고 문자열로 반환")
    @Test
    void IntegerValueTest() {
        // given when
        Operator operator = new Eq("VOUCHER_ID", 1);

        // then
        assertEquals("1", operator.getValue());

    }

    @DisplayName("Operator 인스턴스 생성시 value 인자로 Long 타입을 받고 문자열로 반환")
    @Test
    void LongValueTest() {
        // given when
        Operator operator = new Eq("PRICE", 1100L);

        // then
        assertEquals("1100", operator.getValue());

    }

    @DisplayName("Operator 인스턴스 생성시 value 인자로 String 타입을 받고 문자열로 반환")
    @Test
    void StringValueTest() {
        // given when
        Operator operator = new Eq("NAME", "JUWOONG");

        // then
        assertEquals("JUWOONG", operator.getValue());

    }

    @DisplayName("Operator 인스턴스 생성시 value 인자로 select 타입을 받고 문자열로 반환")
    @Test
    void SelectValueTest() {
        // given when
        Select select = Select.builder()
            .select(Person.class)
            .from(Table.ANIMAL)
            .build();

        Operator operator = new Eq("NAME", select);

        // then
        assertEquals("SELECT ID, NAME, AGE FROM ANIMAL", operator.getValue());

    }

}