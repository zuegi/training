package ch.wesr.training.kata;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopWords {

    public static List<String> top3Eingereicht(String string) {

        Map<String, Integer> freq = new HashMap<String, Integer>();
        String s1 = string.toLowerCase();
        String s2 = s1.replaceAll("[^a-zA-Z\\d\\s']", " ");

        String regex = "(\\s)";
        Pattern.compile(regex)
                .splitAsStream(s2)
                .filter(s -> Pattern.compile("[[a-z]+\\'[a-z]+]+").matcher(s).matches())
                .map(s -> s.replaceAll("^'+", ""))
                .forEach(s -> {
                    int count = freq.containsKey(s) ? freq.get(s) : 0;
                    freq.put(s, count + 1);
                });


        Map collect = freq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(s -> !s.getKey().isBlank())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return new ArrayList<>(collect.keySet());

    }

    public static List<String> top3(String string) {
        // in dieser Lösung muss ich die gefiltertete Sonderzeichen nicht wissen
        String s2 = string.replaceAll("[^a-zA-Z\\d\\s']", " ");
        return Stream.of(s2.split("\\s"))
                .map(String::toLowerCase)
                .filter(word -> !word.replace("'", "").trim().isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> top3Cooler(String s) {
        // in dieser Lösung muss ich die Sonderzeichen wissen
        return Stream.of(s.split("[\\s\n/.,:;@&?!\\-_]+"))
                .map(String::toLowerCase)
                .filter(word -> !word.replace("'", "").trim().isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
