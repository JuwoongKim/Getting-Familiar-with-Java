package org.example.jdbc.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.operator.Eq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpdateTest {

    @Test
    @DisplayName("UPDATE 절 생성 테스트")
    void UpdateTest() {
        // given when
        Update update = Update.builder()
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
        Where where = Where.builder()
            .where(new Eq("VOUCHER_ID", "1"))
            .build();

        Update update = Update.builder()
            .updateInto(Table.ANIMAL)
            .setValues("dog", "1", "etc")
            .where(where)
            .build();

        // then
        assertEquals("UPDATE INTO ANIMAL (dog,1,et) WHERE VOUCHER_ID = 1", update.getQuery());
    }

}