package nl.romkema;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EasyDateTimeFormatterTest {

    @Test
    void test1() {
        LocalDateTime dateTime = EasyDateTimeFormatter.format("1971-01-01 14:30:00.0 CET");
        assertNotNull(dateTime);
    }
}
