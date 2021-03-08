package minispring;

public class HelloService {

    // Field to be injected
    private String message;

    public String hello() {
        return "Hello";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
