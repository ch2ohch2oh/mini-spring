package minispring.factory;

import minispring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The `AbstractBeanFactory` does not know how to instantiate a bean.
 * The instantiation of the bean is delegated to the subclass.
 * Why? Creating the bean can be complex. Maybe we need to read from XML etc.
 * Let the subclass do the job!
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = createBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
    }

    protected abstract Object createBean(BeanDefinition beanDefinition);
}
