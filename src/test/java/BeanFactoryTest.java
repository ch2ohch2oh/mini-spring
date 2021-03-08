import org.junit.Test;

import static org.junit.Assert.*;

class HelloService {

    public String hello() {
        return "Hello";
    }
}

public class BeanFactoryTest {

    @Test
    public void testHelloService() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new HelloService());
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");

        assertEquals("Hello", helloService.hello());
    }
}
