package minispring.xml;

import minispring.AbstractBeanDefinitionReader;
import minispring.BeanDefinition;
import minispring.BeanReference;
import minispring.PropertyValue;
import minispring.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().loadResource(location).getInputStream();
        loadBeanDefinitionsFromInputStream(inputStream);
    }

    protected void loadBeanDefinitionsFromInputStream(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);

        registerBeanDefinitions(doc);
        inputStream.close();
    }

    protected void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
        // The NodeList is not iterable. Sad.
        NodeList nodes = root.getChildNodes();
        for(int i=0; i<nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node instanceof Element) {
                Element e = (Element) node;
                processAndRegisterBeanElement(e);
            }
        }
    }

    protected void processAndRegisterBeanElement(Element e) throws IllegalArgumentException {
        String name = e.getAttribute("name");
        String className = e.getAttribute("class");

        BeanDefinition bean = new BeanDefinition();

        // Process bean properties
        NodeList properties = e.getElementsByTagName("property");
        for(int i=0; i<properties.getLength(); i++) {
            Node node = properties.item(i);
            if(node instanceof Element) {
                Element pElement = (Element) node;
                String pName = pElement.getAttribute("name");
                String pValue = pElement.getAttribute("value");
                // If value is not null, then there should be no ref
                if(pValue != null && pValue.length() > 0) {
                    bean.getProperties().addPropertyValue(new PropertyValue(pName, pValue));
                } else {
                    String ref = pElement.getAttribute("ref");
                    if(ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref is not specified");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    bean.getProperties().addPropertyValue(new PropertyValue(pName, beanReference));
                }

            }
        }
        bean.setBeanClassName(className);

        // Register this bean
        getRegistry().put(name, bean);
    }

}
