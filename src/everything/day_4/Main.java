package everything.day_4;

import everything.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Set<Integer> tmp;

    public static void main(String[] args) {
        List<String> inputString = Util.loadFile("src/everything/day_4/input.txt");

        List<Integer> input = inputString.stream()
                .map(s -> s.split(":")[1])
                .map(s -> s.split("\\|"))
                .map(array1 -> Arrays.stream(array1)
                        .map(s -> s.split(" "))
                        .map(array2 -> Arrays.stream(array2)
                                .filter(s -> !s.isEmpty())
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .collect(Collectors.toSet()))
                        .collect(Collectors.toList()))
                .peek(list -> list.get(1).retainAll(list.get(0)))
                .map(list -> list.get(1))
                .mapToInt(Set::size)
                .boxed()
                .toList();

        long result1 = input.stream()
                .mapToLong(l -> (1L << l) >> 1)
                .sum();

        System.out.println(result1);

        List<Long> list2 = new ArrayList<Long>(input.stream().mapToLong(i -> (long) i).boxed().toList());

        for(int i = list2.size() -1; i >= 0;i-- ) {
            long numAdditionalCardsPrev = list2.get(i);
            long sum = numAdditionalCardsPrev;
            for(int j = 1; j <= numAdditionalCardsPrev; j++) {
                sum += list2.get(i + j);
            }
            list2.set(i, sum);
        }

        long result2 = list2.stream().mapToLong(Long::longValue).sum() + list2.size();

        System.out.println(result2);

    }

}
