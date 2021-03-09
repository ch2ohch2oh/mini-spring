package minispring;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
