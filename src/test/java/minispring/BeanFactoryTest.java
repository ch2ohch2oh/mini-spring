package minispring;

import minispring.factory.AutowireCapableBeanFactory;
import minispring.factory.BeanFactory;
import minispring.io.ResourceLoader;
import minispring.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.time.Year;
import java.util.Map;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    @Test
    public void testHelloService() {
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("minispring.HelloService");

        // Setup bean properties
        PropertyValueCollection pvList = new PropertyValueList();
        pvList.addPropertyValue(new PropertyValue("message", "omg"));
        beanDefinition.setProperties(pvList);

        // Register the bean
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // Instantiate the bean using the factory
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");

        assertEquals("Hello", helloService.hello());
        assertEquals("omg", helloService.getMessage());
    }

    @Test
    public void testHelloServiceXml() throws Exception{
        // First we get read the bean definitions from xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("hello.xml");

        // Set up the bean factory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for(Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        // Instantiate the bean using the factory
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        HelloService helloService2 = (HelloService) beanFactory.getBean("helloService2");

        assertEquals("hello", helloService.getMessage());
        assertEquals("bonjour", helloService2.getMessage());

    }

    @Test
    public void testBeanInjectionXml() throws Exception {
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("beanRef.xml");

        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition(xmlReader.getRegistry());

        Resource resource = beanFactory.getBean("resource", Resource.class);
        ResourceReader resourceReader = beanFactory.getBean("resourceReader", ResourceReader.class);

        assertEquals("tres bien", resource.getValue());
        assertEquals("tres bien", resourceReader.read());
    }

    @Test
    public void testCyclicDependency() throws Exception {
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("cycle.xml");

        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition(xmlReader.getRegistry());

        Cycle c1 = beanFactory.getBean("c1", Cycle.class);
        Cycle c2 = beanFactory.getBean("c2", Cycle.class);

        assertEquals("c1", c1.getText());
        assertEquals("c2", c1.getBuddy().getText());
        assertEquals("c2", c2.getText());
        assertEquals("c1", c2.getBuddy().getText());


    }
}
