package minispring.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class ResourceLoaderTest {

    @Test
    public void testResourceLoader() throws IOException {
        ResourceLoader loader = new ResourceLoader();
        Resource resource = loader.loadResource("hello.xml");
        InputStream inputStream = resource.getInputStream();

        assertNotNull(inputStream);
    }
}
