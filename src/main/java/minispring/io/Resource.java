package minispring.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * What is a resource?
 * A resource is something that can be read from.
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
