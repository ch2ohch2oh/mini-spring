package minispring.factory;

import minispring.BeanDefinition;

public interface BeanFactory {

    public Object getBean(String name);

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
