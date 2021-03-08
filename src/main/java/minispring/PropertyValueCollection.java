package minispring;

import java.util.Collection;

public interface PropertyValueCollection {

    public void addPropertyValue(PropertyValue pv);

    public Collection<PropertyValue> getPropertyValues();
}
