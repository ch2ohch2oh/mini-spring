package minispring.beans;

/**
 * The `BeanDefinition` class holds information about bean.
 * For example, the class of the bean, so that we know how
 * to instantiate a bean. It also hold other meta information
 * of the bean as properties.
 */
public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String beanClassName;

    private PropertyValueCollection properties = new PropertyValueList();

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        // Also update the `beanClass` for consistency
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValueCollection getProperties() {
        return properties;
    }

    public void setProperties(PropertyValueCollection properties) {
        this.properties = properties;
    }

    public Object getBean() {
        return bean;
    }
}
