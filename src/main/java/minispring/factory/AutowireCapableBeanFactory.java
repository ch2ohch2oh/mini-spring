package minispring.factory;

import minispring.BeanDefinition;
import minispring.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            setPropertyValues(bean, beanDefinition);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Field injection for the bean
     * @param beanInstance
     * @param beanDefinition
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    protected void setPropertyValues(Object beanInstance, BeanDefinition beanDefinition)
            throws NoSuchFieldException, IllegalAccessException {
        for(PropertyValue pv: beanDefinition.getProperties().getPropertyValues()) {
            Field field = beanInstance.getClass().getDeclaredField(pv.getName());
            field.setAccessible(true);
            field.set(beanInstance, pv.getValue());
        }
    }
}
