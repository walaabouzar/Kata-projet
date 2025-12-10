package re.forestier.edu.rpg;

public abstract class Avatar {

    private final String className;

    public Avatar(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
