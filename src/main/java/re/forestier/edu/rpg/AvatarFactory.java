package re.forestier.edu.rpg;

public class AvatarFactory {
    public static Avatar create(String avatarClassName) {
        return switch (avatarClassName.toUpperCase()) {
            case "ARCHER" -> new Archer();
            case "ADVENTURER" -> new Adventurer();
            case "DWARF" -> new Dwarf();
            default -> throw new IllegalArgumentException("Unknown avatar " + avatarClassName);
        };
    }
}
