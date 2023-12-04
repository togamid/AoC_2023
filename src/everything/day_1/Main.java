package everything.day_1;

import everything.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> inputString = Util.loadFile("src/everything/day_1/input.txt");
        Set<String> searchFor = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine");
        int sum = 0;
        for (String string : inputString) {

            String firstDigit = findFirstOccurenceFromSet(searchFor, string);

            String secondDigit = findLastOccurenceFromSet(searchFor, string);

            sum += Integer.parseInt(stringToDigit(firstDigit) + stringToDigit(secondDigit));
        }

        System.out.println("Result: " + sum);
    }
    private static String findFirstOccurenceFromSet(Set<String> set, String string) {
        int firstIndex = string.length() +1;
        String substring = "error";
        for(String s : set) {
            int index = string.indexOf(s);
            if(index != -1 && index < firstIndex) {
                firstIndex = index;
                substring = s;
            }
        }
        return substring;
    }

    private static String findLastOccurenceFromSet(Set<String> set, String string) {
        int lastIndex = -1;
        String substring = "error";
        for(String s : set) {
            int index = string.lastIndexOf(s);
            if( index > lastIndex) {
                lastIndex = index;
                substring = s;
            }
        }
        return substring;
    }

    private static String stringToDigit(String s) {
        switch(s) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0":
                return s;
            case "one":
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
            default:
                throw new RuntimeException(s);
        }
    }
}
