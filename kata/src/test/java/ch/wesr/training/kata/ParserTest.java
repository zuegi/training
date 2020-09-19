package ch.wesr.training.kata;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

//    @Test
//    public void testReplaceAnd() {
//        System.out.println("two million and two hundred thousand".replaceAll("\\band\\b", ""));
//    }
//
//    @Test
//    public void testSplit2000000() {
//        String[] split = "two hundred thousand".split("(\\W\\b)+");
//        for (String s : split) {
//            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void testSplitRegex() {
//        String[] split = "two million two hundred thousand seven thousand four houndred eighty-one"
//                .split("(\\W million)|(\\W hundred thousand)|(\\W thousand)|(\\W)(?!million|hundred thousand|thousand|hundred)");
//        for (String s : split) {
//            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void testSplitRegex2() {
//        String[] split = "twenty Six Thousand Seven Hundred Thirty Nine"
//                .split(" ");
//        for (String s : split) {
//            System.out.println(s);
//        }
//    }
//    @Test
//    public void test() throws ParseException {
//        String str = "a + b - c * d / e < f > g >= h <= i == j";
//        String reg = "((?<=[<=|>=|==|\\+|\\*|\\-|<|>|/|=])|(?=[<=|>=|==|\\+|\\*|\\-|<|>|/|=]))";
//
//        String[] res = str.split(reg);
//        System.out.println(Arrays.toString(res));
//    }


    // wie schreibt man englische Zahlen
    // https://www.englisch-hilfen.de/en/texte/ausschreiben.htm?numver=99999&textver=
    @Test
    public void parse1() {
        assertEquals(1, Parser.parseInt("one"));
    }

    @Test
    public void parse20() {
        assertEquals(20 , Parser.parseInt("twenty"));
    }
    @Test
    public void parse246() {
        assertEquals(246 , Parser.parseInt("two hundred forty-six"));
    }
    @Test
    public void parse2242() {
        assertEquals(2242 , Parser.parseInt("two thousand two hundred forty-two"));
    }
    @Test
    public void parse900242() {
        assertEquals(900242 , Parser.parseInt("nine hundred thousand and two hundred forty-two"));
    }
    @Test
    public void parse1207481() {
        assertEquals(1207481, Parser.parseInt("one million two hundred thousand seven thousand four hundred eighty-one"));
    }
    @Test
    public void parse1207481WithAnd() {
        assertEquals(1207481, Parser.parseInt("one million and two hundred thousand and seven thousand and four hundred eighty-one"));
    }
    @Test
    public void parse26739() {
        assertEquals(26739 , Parser.parseInt("twenty Six Thousand Seven Hundred Thirty Nine"));
    }

    @Test
    public void parse276739() {
        assertEquals(276739 , Parser.parseInt("Two Hundred Seventy Six Thousand Seven Hundred Thirty Nine"));
    }
}
