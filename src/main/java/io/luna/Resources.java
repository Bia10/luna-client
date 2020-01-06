package io.luna;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resources {
    public static Path find(String resource) {
        try {
            return Paths.get("./src/main/resources/" + resource); //Windows workaround
            //return Paths.get(ClassLoader.getSystemResource(resource).toURI()); //Only works on Mac
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
