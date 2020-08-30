package ch.wesr.training.kata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static HashSet<String> stringSet;

    public static List<String> singlePermutations(String s) {
        // HashSet to avoid duplicates
        stringSet = new HashSet<>();
        printPermutn(s, "");
        return new ArrayList<>(stringSet);
    }
    static void printPermutn(String str, String ans)  {

        // Termination condition when string is empty
        if (str.length() == 0) {
            stringSet.add(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String restOfString = str.substring(0, i) +
                    str.substring(i + 1);
            // Recurvise call
            printPermutn(restOfString, ans + ch);
        }
    }
}
