package argparsing;

import java.util.Optional;

public class Option {
    public final String shortName;
    public final String longName;
    public final boolean isFlag;

    private boolean isPresent;
    private String value;

    public Option(String shortName, String longName, boolean isFlag) {
        if (shortName == null && longName == null) throw new IllegalArgumentException();
        if (shortName != null && shortName.length() != 1) throw new IllegalArgumentException();
        if (longName != null && longName.length() < 2) throw new IllegalArgumentException();

        this.shortName = shortName;
        this.longName = longName;
        this.isFlag = isFlag;
    }

    public boolean isPresent() {
        return isPresent;
    }

    void setPresent() {
        isPresent = true;
    }

    public Optional<String> getOptional() {
        return Optional.ofNullable(value);
    }

    public Optional<Integer> getOptionalInt() {
        if (value == null) return Optional.empty();

        try {
            var i = Integer.parseInt(value);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    void setValue(String value) {
        this.value = value;
    }
}
