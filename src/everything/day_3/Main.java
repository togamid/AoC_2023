package everything.day_3;

import everything.Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        List<String> inputString = Util.loadFile("src/everything/day_3/input.txt");

        char[][] field = inputString.stream().map(String::toCharArray).toArray(char[][]::new);

        List<ArrayList<Integer>> gearNumberToNumbers = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '*') {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int u = -1; u <= 1; u++) {
                        for (int v = -1; v <= 1; v++) {
                            try {
                                int number = getNumberAtIndex(field, i + u, j + v);
                                // TODO: ugly fix, won't work if a gear has the same number twice
                                if (number != -1 && !numbers.contains(number)) {
                                    numbers.add(number);
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {

                            }
                        }
                    }
                    gearNumberToNumbers.add(numbers);
                }
            }
        }

        int sum = 0;

        sum = gearNumberToNumbers.stream()
                .peek(list -> {list.stream().forEach(x -> System.out.println(x)); System.out.println();})
                .filter(list -> list.size() == 2).mapToInt(list -> list.get(0) * list.get(1)).sum();

        System.out.println(sum);
    }

    private static int getNumberAtIndex(char[][] field, int i, int j) {

        if(!Character.isDigit(field[i][j])) {
            return -1;
        }
        // go to the front
        while(j != -1 && Character.isDigit(field[i][j])){
            j--;
        }
        j++;
        String number = "";
        //collect number
        while (j < field[i].length && Character.isDigit(field[i][j])){
            number += field[i][j];
            j++;
        }
        if(number.isEmpty()) {
            throw new RuntimeException("Error");
        }
        else {
            return Integer.parseInt(number);
        }
    }

    private static void partOne() {
        List<String> inputString = Util.loadFile("src/everything/day_3/input.txt");

        char[][] field = inputString.stream().map(String::toCharArray).toArray(char[][]::new);

        int[][] isAdjacent = new int[field.length][field[0].length];

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                for(int u = -1; u <=1; u++) {
                    for(int v = -1; v <= 1; v++) {
                        try {
                            char charAt = field[i+u][j+v];
                            if(!Character.isDigit(charAt) && !(charAt == '.')) {
                                isAdjacent[i][j] = 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored){

                        }
                    }
                }
            }
        }

        int sum = 0;

        for(int i = 0; i < field.length; i++) {
            String currentNumber = "";
            boolean adjacent = false;
            for (int j = 0; j < field[0].length; j++) {
                char currentChar = field[i][j];
                if(Character.isDigit(currentChar)) {
                    currentNumber += currentChar;
                    if(isAdjacent[i][j] == 1) {
                        adjacent = true;
                    }
                }
                else {
                    if(adjacent) {
                        sum += Integer.parseInt(currentNumber);
                    }
                    currentNumber = "";
                    adjacent = false;
                }
            }
            if(adjacent) {
                sum += Integer.parseInt(currentNumber);
            }
        }

        System.out.println(sum);
    }

}
