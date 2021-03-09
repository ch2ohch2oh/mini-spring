package minispring;

import minispring.context.ApplicationContext;
import minispring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ApplicationContextTest {

    @Test
    public void testClassPathXmlApplicationContext() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
        HelloService service = context.getBean("helloService", HelloService.class);

        assertEquals("Hello", service.hello());
    }
}
