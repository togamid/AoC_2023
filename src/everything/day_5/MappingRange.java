package everything.day_5;

import java.util.ArrayList;
import java.util.List;

public class MappingRange {

    private static List<Range> handledRanges = new ArrayList<>();
    long sourceStart;
    long destStart;
    long length;

    public MappingRange(long destStart, long sourceStart, long length) {
        this.sourceStart = sourceStart;
        this.destStart = destStart;
        this.length = length;
    }

    private long getSourceEnd() {
        return sourceStart + length;
    }

    public boolean isInRange(long value) {
        return value >= sourceStart && value < sourceStart + length;
    }

    public boolean isInRange(Range range) {
        return range.getStart() >= this.sourceStart && range.getStart() < this.getSourceEnd() ||
        range.getEnd()<= this.getSourceEnd() && range.getEnd() > this.sourceStart;
    }

    public long getMapping(long value) {
        return destStart + (value - sourceStart);
    }

    public Range getMapping(Range range) {
        if(!isInRange(range)) {
            return null;
        }
        Range newRange;
        if(range.getStart() >= this.sourceStart && range.getStart() < this.getSourceEnd()) {
            if(range.getEnd() <= this.getSourceEnd()) {
                newRange = Range.fromStartAndEnd(range.getStart(),  range.getEnd());
            }
            else {
                newRange = Range.fromStartAndEnd(range.getStart(), this.getSourceEnd());
            }
        }
        else if(range.getEnd()<= this.getSourceEnd() && range.getEnd() > this.sourceStart) {
            newRange = Range.fromStartAndEnd(this.sourceStart, range.getEnd());
        }
        else {
            return null;
        }
        handledRanges.add(newRange);
        return Range.fromStartAndEnd(getMapping(newRange.getStart()), getMapping(newRange.getEnd()));
    }

    public static List<Range> getUnmappedRanges(Range completeRange) {
        List<Range> additionalRanges = new ArrayList<>();
        handledRanges.sort(Range::compareTo);
        if(handledRanges.size() == 0) {
            additionalRanges.add(Range.fromStartAndEnd(completeRange.getStart(), completeRange.getEnd()));
        }
        else {
            if (handledRanges.get(0).getStart() != completeRange.getStart()) {
                additionalRanges.add(Range.fromStartAndEnd(completeRange.getStart(), handledRanges.get(0).getStart()));
            }
            long endOfLastHandledRange = handledRanges.get(handledRanges.size() - 1).getEnd();
            if (endOfLastHandledRange != completeRange.getEnd()) {
                additionalRanges.add(Range.fromStartAndEnd(endOfLastHandledRange, completeRange.getEnd()));
            }
            for (int i = 0; i < handledRanges.size() - 1; i++) {
                Range range1 = handledRanges.get(i);
                Range range2 = handledRanges.get(i + 1);
                if (range1.getEnd() != range2.getStart()) {
                    additionalRanges.add(new Range(range1.getEnd(), range2.getStart() - range1.getEnd()));
                }
            }
        }
        handledRanges = new ArrayList<>();
        return additionalRanges;
    }
}
