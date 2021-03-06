package com.ll.exam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WiseSayingTableTest {

    private WiseSayingTable wiseSayingTable;

    @BeforeAll
    public void beforeAll() {
        App.setMode("test");
        wiseSayingTable = new WiseSayingTable();
    }


    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir(App.getBaseDir());
        wiseSayingTable.save("삶이 있는 한 희망은 있다.", "키케로");
        wiseSayingTable.save("나에게 불가능이란 없다.", "나폴레옹");
    }

    @Test
    public void 저장() {
        int newId = wiseSayingTable.getLastId() + 1;
        wiseSayingTable.save("자유가 아니면 죽음을 달라.", "패트릭 헨리");
        assertTrue(new File(WiseSayingTable.getTableDataFilePath(newId)).exists());
    }

    @Test
    public void 조회() {
        WiseSaying wiseSaying = wiseSayingTable.findById(1);

        assertEquals(1, wiseSaying.id);
        assertEquals("삶이 있는 한 희망은 있다.", wiseSaying.content);
        assertEquals("키케로", wiseSaying.author);
    }

    @Test
    public void 전체조회() {
        List<WiseSaying> wiseSayingList = wiseSayingTable.findAll();

        assertEquals(2, wiseSayingList.size());
        assertEquals(1, wiseSayingList.get(0).id);
        assertEquals("삶이 있는 한 희망은 있다.", wiseSayingList.get(0).content);
        assertEquals("키케로", wiseSayingList.get(0).author);

        assertEquals(2, wiseSayingList.get(1).id);
        assertEquals("나에게 불가능이란 없다.", wiseSayingList.get(1).content);
        assertEquals("나폴레옹", wiseSayingList.get(1).author);
    }

    @Test
    public void 삭제() {
        wiseSayingTable.removeById(1);

        WiseSaying wiseSaying = wiseSayingTable.findById(1);

        assertEquals(null, wiseSaying);
    }
}