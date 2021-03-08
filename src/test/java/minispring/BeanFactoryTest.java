package minispring;

import minispring.factory.AutowireCapableBeanFactory;
import minispring.factory.BeanFactory;
import org.junit.Test;

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
}
