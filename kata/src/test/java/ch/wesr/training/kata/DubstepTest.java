package ch.wesr.training.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DubstepTest {

    @Test
    void test1() {
        assertEquals("ABC", new Dubstep().SongDecoder("WUBWUBABCWUB"));
    }
    @Test
    void test2()
    {
        assertEquals("R L", new Dubstep().SongDecoder("RWUBWUBWUBLWUB"));
    }

    @Test
    void test3()
    {
        assertEquals("R L M", new Dubstep().SongDecoder("RWUBWUBWUBLWUBM"));
    }

    @Test
    void playAround() {
        assertEquals("R L M", "RWUBWUBWUBLWUBM".replaceAll("(WUB)+", " "));
    }
}
