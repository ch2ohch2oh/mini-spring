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
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");

        assertEquals("Hello", helloService.hello());
    }
}
