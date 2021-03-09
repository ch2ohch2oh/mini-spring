# Mini-spring

This is a mock of the famous spring framework. 
It is mostly made following the wonderful tutorial from xxxx. 

## IOC Container

The main job of an ioc container is to create the beans 
(from XML configuration) and wire them up according to the dependencies. 

There are a few points to note for the implementation of the ioc container.

- Program to the interface!
- `BeanDefinition` holds the properties of the bean 
  read from XML file as well as an instance of the bean.
- Cyclic dependencies are handled.

Some design patterns involved:

- Factory pattern. Yes there are factories everywhere.s
- Template method pattern. The `AbstractBeanFactory` 
  makes use of the `createBean` method, 
  which is implemented by the subclass.
- Proxy pattern. The `AbstractApplicationContext` contains 
  an instance of `AbstractBeanFactory` so that we do not have to 
  deal with setting up the bean factory ourselves.

