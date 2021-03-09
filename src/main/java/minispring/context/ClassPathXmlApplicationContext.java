package minispring.context;

import minispring.beans.BeanDefinition;
import minispring.factory.AbstractBeanFactory;
import minispring.factory.AutowireCapableBeanFactory;
import minispring.io.ResourceLoader;
import minispring.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String xmlLocation;

    public ClassPathXmlApplicationContext(String xmlLocation) throws Exception {
        this(new AutowireCapableBeanFactory(), xmlLocation);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String xmlLocation) throws Exception {
        super(beanFactory);
        this.xmlLocation = xmlLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions(xmlLocation);
        beanFactory.registerBeanDefinition(xmlReader.getRegistry());
    }
}
