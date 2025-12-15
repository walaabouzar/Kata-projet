package re.forestier.edu.rpg;

public class HealthManager {

    public static void regenerate(player p) {
        if (p.currenthealthpoints < p.healthpoints/2) { 

            switch (p.getAvatarClass()) {
                case "ADVENTURER":
                    p.currenthealthpoints+=2;     
                    if (p.retrieveLevel() < 3) p.currenthealthpoints -= 1;
                    break;

                case "ARCHER":
                    p.currenthealthpoints+=1;
                    if (p.inventory.stream().anyMatch(o -> "Magic Bow".equals(o.getName()))) {
                        // S'assurer que le calcul donne un int correct
                        p.currenthealthpoints+=p.currenthealthpoints / 8 - 1;
                    }
                    break;

                case "DWARF":
                    p.currenthealthpoints+=1;
                    if (p.inventory.stream().anyMatch(o -> "Holy Elixir".equals(o.getName()))) {
                        p.currenthealthpoints+=1;
                    }
                    break;

                default:
                    break;
            }
        }

        // Limiter à la santé max
        if (p.currenthealthpoints > p.healthpoints) {
            p.currenthealthpoints = p.healthpoints;
        }
    }
}
