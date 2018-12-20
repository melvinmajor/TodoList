package argparsing;

public class OptionFactory {

    public static Option newFlag(String name) {
        return isShort(name)
                ? new Option(name, null, true)
                : new Option(null, name, true);
    }

    public static Option newFlag(String a, String b) {
        return isShort(a)
                ? new Option(a, b, true)
                : new Option(b, a, true);
    }

    public static Option newOption(String name) {
        return isShort(name)
                ? new Option(name, null, false)
                : new Option(null, name, false);
    }

    public static Option newOption(String a, String b) {
        return isShort(a)
                ? new Option(a, b, false)
                : new Option(b, a, false);
    }

    private static boolean isShort(String s) {
        return s.length() == 1;
    }

}
