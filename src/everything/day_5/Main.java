package everything.day_5;

import everything.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<List <String>> inputString = Util.asPackets(Util.loadFile("src/everything/day_5/input.txt"));
        List<List<MappingRange>> mappings = inputString.subList(1, inputString.size()).stream()
                .map(list -> list.subList(1, list.size()))
                .map(list -> list.stream()
                        .map(s -> s.split(" "))
                        .map(Util::parseArrayLong)
                        .map(array -> new MappingRange(array[0], array[1], array[2]))
                        .collect(Collectors.toList()))
                .toList();

        List<Long> seeds = Arrays.stream(inputString.get(0).get(0).split(":")[1].split(" "))
                .filter(s -> !s.isEmpty()).map(Long::parseLong).toList();

        part1(seeds, mappings);
        part2(seeds, mappings);

    }

    private static void part1(List<Long> seeds, List<List<MappingRange>> mappings) {
        List<Long> seedValues = new ArrayList<>();
        for(Long seed : seeds) {
            Long seedValue = seed;
            for(List<MappingRange> mapping: mappings) {
                for(MappingRange range : mapping) {
                    if(range.isInRange(seedValue)) {
                        seedValue = range.getMapping(seedValue);
                        break;
                    }
                }
            }
            seedValues.add(seedValue);
        }

        long result = seedValues.stream().mapToLong(i -> i).min().orElseThrow();
        System.out.println(result);
    }


    private static void part2(List<Long> seedsLong, List<List<MappingRange>> stages) {
        List<Range> seeds = new ArrayList<>();
        for(int i = 0; i+1 < seedsLong.size(); i+=2) {
            seeds.add(new Range(seedsLong.get(i), seedsLong.get(i+1)));
        }
        long minimumSeedValue = Long.MAX_VALUE;
        for(Range seed : seeds) {
            List<Range> rangesToMap = new ArrayList<>();
            rangesToMap.add(seed);

            for(List<MappingRange> currentMappings: stages) {
                List<Range> mappedRanges = new ArrayList<>();
                for(Range rangeToMap : rangesToMap) {
                    for (MappingRange mapping : currentMappings) {
                        if (mapping.isInRange(rangeToMap)) {
                            mappedRanges.add(mapping.getMapping(rangeToMap));
                        }
                    }
                    List<Range> unhandledRanges = MappingRange.getUnmappedRanges(rangeToMap);
                    mappedRanges.addAll(unhandledRanges);
                    mappedRanges.sort(Range::compareTo);
                }
                rangesToMap = mappedRanges;
            }
            rangesToMap.sort(Range::compareTo);
            if(minimumSeedValue > rangesToMap.get(0).getStart()) {
                minimumSeedValue = rangesToMap.get(0).getStart();
            }
        }

        System.out.println(minimumSeedValue);
    }

}
