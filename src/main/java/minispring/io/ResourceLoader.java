package minispring.io;

import java.net.URL;

/**
 * A `Resource` is loaded using a `ResourceLoader`.
 */
public class ResourceLoader {

    public Resource loadResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
