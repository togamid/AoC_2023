package everything.day_5;

public class Range implements Comparable<Range> {

    public static Range fromStartAndEnd(long start, long end) {
        return new Range(start, end-start);
    }
    private final long start;

    private final long end;

    public Range(long start, long length) {
        this.start = start;
        this.end = start + length;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    @Override
    public int compareTo(Range o) {
        return (int) ((this.start - o.start)/Math.abs(this.start - o.start));
    }
}
