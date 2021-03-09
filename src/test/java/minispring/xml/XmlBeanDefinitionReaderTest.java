package minispring.xml;

import minispring.BeanDefinition;
import minispring.io.ResourceLoader;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void testXmlDefinitionReader() throws Exception {
        // Yes we are injecting dependencies manually
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("hello.xml");
        Map<String, BeanDefinition> registry = reader.getRegistry();

        // Check the number of beans
        assertEquals(2, registry.size());
    }
}
