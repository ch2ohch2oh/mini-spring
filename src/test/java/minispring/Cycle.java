package minispring;

/**
 * A Cycle depends on another Cycle.
 * This is mainly used to test cyclic dependency.
 */
public class Cycle {

    private String text;

    private Cycle buddy;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Cycle getBuddy() {
        return buddy;
    }

    public void setBuddy(Cycle buddy) {
        this.buddy = buddy;
    }
}
