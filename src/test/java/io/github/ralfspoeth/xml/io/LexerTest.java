package io.github.ralfspoeth.xml.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void test1() {
        var source = "<?xml version='1.0'?><root/>";
        var is = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
        
    }

    @Test
    void testEmpty() throws IOException {
        var is = ByteArrayInputStream.nullInputStream();
        int cnt = 0;
        try(var lexer = new Lexer(is)) {
            while(lexer.hasNext()) {
                var t = lexer.next();
                cnt++;
            }
        }
        assertEquals(0, cnt);
    }

    @Test
    void hasNext() {
    }

    @Test
    void next() {
    }
}