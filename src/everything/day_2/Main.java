package everything.day_2;

import everything.Util;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        List<String> inputString = Util.loadFile("src/everything/day_2/input.txt");

        int result = inputString.stream().mapToInt(Main::getPower).sum();

        System.out.println(result);
    }

    private static boolean isPossible(String game) {

        String patternRed = "([0-9]+) red";
        String patternBlue = "([0-9]+) blue";
        String patternGreen = "([0-9]+) green";
        Pattern rRed = Pattern.compile(patternRed);
        Pattern rBlue = Pattern.compile(patternBlue);
        Pattern rGreen = Pattern.compile(patternGreen);
        String[] draws = game.split(";");

        for(String draw : draws) {
            Matcher matcherRed = rRed.matcher(draw);
            if(matcherRed.find() && Integer.parseInt(matcherRed.group(1)) > 12) {
                return false;
            }
            Matcher matcherGreen = rGreen.matcher(draw);
            if(matcherGreen.find() && Integer.parseInt(matcherGreen.group(1)) > 13) {
                return false;
            }
            Matcher matcherBlue = rBlue.matcher(draw);
            if(matcherBlue.find() && Integer.parseInt(matcherBlue.group(1)) > 14) {
                return false;
            }
        }

        return true;
    }

    private static int getGameNumber(String game) {
        String pattern = "Game ([0-9]+):";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(game);
        if(m.find()) {
            return Integer.parseInt(m.group(1));
        }
        else {
            throw new RuntimeException("No game number found for game String:  " + game);
        }
    }

    private static int getPower(String game) {

        String patternRed = "([0-9]+) red";
        String patternBlue = "([0-9]+) blue";
        String patternGreen = "([0-9]+) green";
        Pattern rRed = Pattern.compile(patternRed);
        Pattern rBlue = Pattern.compile(patternBlue);
        Pattern rGreen = Pattern.compile(patternGreen);
        String[] draws = game.split(";");

        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;


        for(String draw : draws) {
            Matcher matcherRed = rRed.matcher(draw);
            if(matcherRed.find() && Integer.parseInt(matcherRed.group(1)) > minRed) {
                minRed = Integer.parseInt(matcherRed.group(1));
            }
            Matcher matcherGreen = rGreen.matcher(draw);
            if(matcherGreen.find() && Integer.parseInt(matcherGreen.group(1)) > minGreen) {
                minGreen = Integer.parseInt(matcherGreen.group(1));
            }
            Matcher matcherBlue = rBlue.matcher(draw);
            if(matcherBlue.find() && Integer.parseInt(matcherBlue.group(1)) > minBlue) {
                minBlue = Integer.parseInt(matcherBlue.group(1));
            }
        }

        return minRed*minGreen*minBlue;
    }
}
