package ch.wesr.training.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PigLatinTest {

    @Test
    public void FixedTests() {
        assertEquals("igPay atinlay siay oolcay", PigLatin.pigIt("Pig latin is cool"));
        assertEquals("hisTay siay ymay tringsay", PigLatin.pigIt("This is my string"));
        assertEquals("....hisTay siay ymay tringsay", PigLatin.pigIt("....This is my string"));
        assertEquals("hisTay siay... ymay tringsay", PigLatin.pigIt("This is... my string"));
        assertEquals("hisTay ... ymay tringsay", PigLatin.pigIt("This ... my string"));
        // und dann noch meine Lösung
        assertEquals("igPay atinlay siay oolcay", PigLatin.meinePigIt("Pig latin is cool"));
        assertEquals("hisTay siay ymay tringsay", PigLatin.meinePigIt("This is my string"));
        assertEquals("....hisTay siay ymay tringsay", PigLatin.meinePigIt("....This is my string"));
        assertEquals("hisTay siay... ymay tringsay", PigLatin.meinePigIt("This is... my string"));
        assertEquals("hisTay ... ymay tringsay", PigLatin.meinePigIt("This ... my string"));
    }

    @Test
    public void testSplitWithRegex() {
        String REGEX = "(^\\.*)?(\\w*)?(\\.+$)?";
        Pattern pattern = Pattern.compile(REGEX);

        String testString = "....This";
        Matcher matcher = pattern.matcher(testString);
        assertTrue(matcher.matches());
        assertEquals("....", matcher.group(1));
        assertEquals("This", matcher.group(2));
        assertNull(matcher.group(3));


    }

    @Test
    public void removeDots() {
        List<String> ignore = Arrays.asList(".");
        String word = ".....This";
        String[] letters = word.split("(?!^)");
        String identity = ".";
        String result = Arrays.stream(letters)
                .reduce(identity, (partialString, element) -> partialString.replace(identity, "") + element);
        assertEquals("This", result);
    }

    @Test
    public void appendAyWithFirstLetter() {
        String ay = "ay";
        String myString = "This is my string";
        String[] words = myString.split(" ");
        String reduce = Arrays.stream(words)
                .map(word -> {
                    String firstLetter = word.substring(0,1);
                    String wordWithoutStartingLetter = word.substring(1);
                    return wordWithoutStartingLetter.concat(firstLetter).concat(ay);
                })
                .collect(Collectors.joining(" "));
        assertEquals("hisTay siay ymay tringsay", PigLatin.pigIt("This is my string"));

    }

    @Test
    public void appendAy() {
        String ay = "ay";
        String myString = "This is my string";
        String[] words = myString.split(" ");

        String reduce = Arrays.stream(words)
                .map(s -> s.concat(ay))
                .collect(Collectors.joining(" "));

        System.out.println(reduce);

    }

    @Test
    public void firstReduce() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
        assertEquals(result, "abcde");
    }

    @Test
    public void IgnoreLetters() {
        List<String> ignore = Arrays.asList("r", "2", "3");
        String text = "Hello world 1 2 3";
        text = ignore.stream().map(toRem-> (Function<String,String>) s->s.replaceAll(toRem, "")).reduce(Function.identity(), Function::andThen).apply(text);
        System.out.println(text);
    }
}
