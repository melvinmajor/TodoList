package argparsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

/**
 * Implementation of ArgSupplier
 * the file should be one line long with a list of arguments
 * ex: "-s --something=abcd"
 */
public class FileArgSupplier implements ArgSupplier {
    private final Path path;


    public FileArgSupplier(String path) {
        try {
            this.path = Path.of(path);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path");
        }

        // TODO better validation
    }

    @Override
    public String[] get() {
        if (!Files.exists(path)) return new String[0];
        List<String> strings;
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            return new String[0];
        }

        if (strings.size() != 1) return new String[0];

        return strings.get(0).split(" ");
    }
}
