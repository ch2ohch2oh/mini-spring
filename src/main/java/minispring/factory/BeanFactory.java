package minispring.factory;

import minispring.BeanDefinition;

import java.util.Map;

/**
 * The Spring framework have the interface with the same name.
 * However, there are no methods like `registerBeanDefinition`.
 *
 */
public interface BeanFactory {

    public Object getBean(String name);

    public <T> T getBean(String name, Class T);

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    public void registerBeanDefinition(Map<String, BeanDefinition> beanDefinitionMap);

}
