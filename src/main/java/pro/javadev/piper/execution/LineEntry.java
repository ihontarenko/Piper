package pro.javadev.piper.execution;

import static java.lang.String.format;

public final class LineEntry implements Entry {

    private final String line;
    private final int    ordinal;

    public LineEntry(String line, int ordinal) {
        this.line = line;
        this.ordinal = ordinal;
    }

    @Override
    public String line() {
        return line;
    }

    @Override
    public int ordinal() {
        return ordinal;
    }

    @Override
    public boolean isFirst() {
        return ordinal == 0;
    }

    @Override
    public boolean isBlank() {
        return line == null || line.isBlank();
    }

    @Override
    public String toString() {
        return format("LINE_ENTRY['%s' #%d]", line, ordinal);
    }

}
