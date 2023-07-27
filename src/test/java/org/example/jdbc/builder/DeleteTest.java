package org.example.jdbc.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jdbc.builder.constant.Table;
import org.example.jdbc.builder.operator.Eq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteTest {

    @Test
    @DisplayName("DELETE 절 생성 테스트")
    void DeleteTest() {
        // given when
        Delete delete = Delete.builder()
            .delete(Table.ANIMAL)
            .build();

        // then
        assertEquals("DELETE ANIMAL", delete.getQuery());
    }

    @Test
    @DisplayName("DELETE 절 WHERE 조건 추가 생성 테스트")
    void DeleteWithWhereTest() {
        // given when
        Where where = Where.builder()
            .where(new Eq("VOUCHER_ID", "1"))
            .build();

        Delete delete = Delete.builder()
            .delete(Table.ANIMAL)
            .where(where)
            .build();

        // then
        assertEquals("DELETE ANIMAL WHERE VOUCHER_ID = 1", delete.getQuery());
    }

}