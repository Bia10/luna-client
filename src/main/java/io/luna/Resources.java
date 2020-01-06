package io.luna;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resources {
    public static Path find(String resource) {
        try {
            return Paths.get(ClassLoader.getSystemResource(resource).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
