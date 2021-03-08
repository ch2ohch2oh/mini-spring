package minispring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PropertyValueList implements PropertyValueCollection {

    private List<PropertyValue> pvList = new ArrayList<>();

    @Override
    public void addPropertyValue(PropertyValue pv) {
        // TODO: check for duplicate properties
        this.pvList.add(pv);
    }

    @Override
    public Collection<PropertyValue> getPropertyValues() {
        return this.pvList;
    }
}
