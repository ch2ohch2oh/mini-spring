package minispring.context;

import minispring.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {

    }


    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class T) {
        return (T) beanFactory.getBean(name);
    }
}
