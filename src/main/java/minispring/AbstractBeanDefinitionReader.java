package minispring;

import minispring.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * The `AbstractBeanDefinitionReader` maintains a registry of the beans
 * and the bean definitions are loaded with a `ResourceLoader`.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * Each bean in the register is uniquely identified.
     */
    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
