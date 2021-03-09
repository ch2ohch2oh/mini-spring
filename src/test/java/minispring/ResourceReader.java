package minispring;

/**
 * A `ResourceReader` reads a String from the Resource.
 */
public class ResourceReader {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String read() {
        return resource.getValue();
    }
}
