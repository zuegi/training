package ch.wesr.training.kata;


import java.util.Map;
import static java.util.Map.entry;

/*
    In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.

    Examples:

    "one" => 1
    "twenty" => 20
    "two hundred forty-six" => 246
    "seven hundred eighty-three thousand nine hundred and nineteen" => 783919
    Additional Notes:

    The minimum number is "zero" (inclusively)
    The maximum number, which must be supported is 1 million (inclusively)
    The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
    All tested numbers are valid, you don't need to validate them
 */
public class Parser {

    // to convert words to numbers revert this one
    // https://knpcode.com/java-programs/java-program-to-convert-numbers-to-words/
    private static final Map<String, Integer> units = Map.ofEntries(
            entry("zero", 0),
            entry("one", 1),
            entry("two", 2),
            entry("three", 3),
            entry("four", 4),
            entry("five", 5),
            entry("six", 6),
            entry("seven", 7),
            entry("eight", 8),
            entry("nine", 9),
            entry("ten", 10),
            entry("eleven", 11),
            entry("twelve", 12),
            entry("thirteen", 13),
            entry("fourteen", 14),
            entry("fifteen", 15),
            entry("sixteen", 16),
            entry("seventeen", 17),
            entry("eighteen", 18),
            entry("nineteen", 19),
            entry("twenty", 20),
            entry("thirty", 30),
            entry("forty", 40),
            entry("fifty", 50),
            entry("sixty", 60),
            entry("seventy", 70),
            entry("eighty", 80),
            entry("ninety", 90)
    );

    private static final java.util.Map<String, Integer> upperUnits = Map.ofEntries(
            entry("hundred", 100),
            entry("thousand", 1000),
            entry("hundred thousand", 100000),
            entry("million", 1000000)
    );

    public static int parseInt(String numStr) {
        int sum = 0;
        String[] split = numStr.toLowerCase().replaceAll("\\band\\b ", "")
                .split("(\\W)");
        int previous = 0;
        int subSum = 0;
        for (String s : split) {
            if (upperUnits.containsKey(s)) {
                previous = upperUnits.get(s);
                subSum *= previous;
            } else if(units.containsKey(s)) {
                if (subSum > 100 && previous > 100) {
                    sum += subSum;
                    subSum = 0;
                }
                previous = units.get(s);
                subSum += previous;
            }
        }
        sum += subSum;
        return sum;
    }

//    public static int parseInt1(String numStr) {
//        int sum = 0;
//        numStr = numStr.toLowerCase().replaceAll("\\band\\b ", "");
//        String regex = "(\\W million)|(\\W hundred thousand)|(\\W thousand)|(\\W)(?!million|hundred thousand|thousand|hundred)";
//        String[] split = numStr.split(regex);
//
//        for (String s : split) {
//            String[] stringNumbers = s.split(" |(\\W)");
//            if (stringNumbers.length > 1) {
//                sum += Arrays.stream(stringNumbers).flatMapToInt(num -> IntStream.of(numbers.get(num))).reduce(1, (a, b) -> a * b);
//            } else {
//                sum += numbers.get(s);
//            }
//        }
//
//        return sum;
//    }
}
