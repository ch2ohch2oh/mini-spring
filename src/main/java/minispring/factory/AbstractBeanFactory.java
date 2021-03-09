package minispring.factory;

import minispring.beans.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The `AbstractBeanFactory` does not know how to instantiate a bean.
 * The instantiation of the bean is delegated to the subclass.
 * Why? Creating the bean can be complex. Maybe we need to read from XML etc.
 * Let the subclass do the job!
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * Note the instantiation of the bean is delegated to subclass.
     * The bean will be instantiated if it is not already so.
     * @param name
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Object getBean(String name) throws IllegalArgumentException {
        BeanDefinition  beanDefinition = (BeanDefinition) beanDefinitionMap.get(name);
        if(beanDefinition == null) {
            throw new IllegalArgumentException("No bean with name " + name);
        }
        Object bean = beanDefinition.getBean();
        if(bean == null) {
            bean = createBean(beanDefinition);
        }
        return bean;
    }

    @Override
    public <T> T getBean(String name, Class T) {
        return (T) getBean(name);
    }

    /**
     * Register a `BeanDefinition` with a name.
     * The bean is not instantiated yet.
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        // Do NOT create the beans here. Otherwise, the cyclic dependency will
        // cause infinite loop. Delay the creation to `getBean`.
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * A convenience function to register bean definitions
     * @param beanDefinitionMap
     */
    public void registerBeanDefinition(Map<String, BeanDefinition> beanDefinitionMap) {
        for(Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Instantiate a bean and save the bean in the beanDefinition.
     * Note the `BeanDefinition` not only holds the class of the bean,
     * which can be used to create a bean, it also holds an actual bean instance.
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(BeanDefinition beanDefinition);

    /**
     * Instantiate all the beans in our `beanDefinitionsMap`.
     */
    public void preInstantiateSingletons() {
        for(Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            createBean((BeanDefinition) entry.getValue());
        }
    }
}
