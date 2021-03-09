package minispring.factory;

import minispring.beans.BeanDefinition;

import java.util.Map;

/**
 * The Spring framework have the interface with the same name.
 * However, there are no methods like `registerBeanDefinition`.
 *
 */
public interface BeanFactory {

    public Object getBean(String name);

    public <T> T getBean(String name, Class T);



}
