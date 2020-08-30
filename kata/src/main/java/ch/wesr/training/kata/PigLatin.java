package ch.wesr.training.kata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {

    // Lösung
    public static String pigIt(String str) {
        return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
    }

    public static String meinePigIt(String str) {
        String ay = "ay";
        String identity = ".";
        String[] splittedIntoWords = str.split(" ");
        for (int i = 0; i< splittedIntoWords.length; i++) {
            // trim leading ... and trailing...
            String word = "";
            String leadingDots = "";
            String trailingDots = "";
            String splittedWord = splittedIntoWords[i];
            String REGEX = "(^\\.*)?(\\w*)?(\\.+$)?";
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(splittedWord);
            if (matcher.matches()) {
                if(matcher.group(1)!= null)
                    leadingDots = matcher.group(1);
                if(matcher.group(2) != null)
                    word = matcher.group(2);
                if(matcher.group(3) != null)
                    trailingDots = matcher.group(3);
            }

            if (word.length() > 0) {
                String firstLetter = word.substring(0, 1);
                String wordWithoutStartingLetter = word.substring(1);
                splittedIntoWords[i] = leadingDots + wordWithoutStartingLetter + firstLetter + ay + trailingDots;
            }
        }
        return String.join(" ", splittedIntoWords);
    }

//    public static String pigIt(String str) {
//        String ay = "ay";
//        String identity = ".";
//        String[] splitted = str.split(" ");
//        return Arrays.stream(splitted)
//                .map(word -> {
//                    System.out.println("word: " +word);
//                    String[] letters = word.split("(?!^)");
//                    String dots = "";
//                    String withoutDots = "";
//                    for (String letter : letters) {
//                        System.out.println("letter: " +letter);
//                        if (letter.contains(identity)) {
//                           dots = dots.concat(letter);
//                        } else {
//                            withoutDots = withoutDots.concat(letter);
//                        }
//                    }
//                    System.out.println("dots: " +dots +", withoutDots: " +withoutDots);
//                    String firstLetter = withoutDots.substring(0,1);
//                    String wordWithoutStartingLetter = withoutDots.substring(1);
//                    return dots.concat(wordWithoutStartingLetter.concat(firstLetter).concat(ay));
//                })
//                .collect(joining(" "));
//    }

//    public static String pigIt(String str) {
//        String ay = "ay";
//        String[] splitted = str.split(" ");
//        for (int i = 0; i< splitted.length; i++) {
//            String word = splitted[i];
//            String[] letters = word.split("(?!^)");
//            for (String letter : letters) {
//                if (letter.startsWith(".")) {
//
//                }
//            }
//            String firstLetter = word.substring(0,1);
//            String wordWithoutStartingLetter = word.substring(1);
//            splitted[i] = wordWithoutStartingLetter.concat(firstLetter).concat(ay);
//        }
//
//        return String.join(" ", splitted);
//    }
}
