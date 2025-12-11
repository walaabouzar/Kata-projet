package re.forestier.edu.rpg;

public class HealthManager {

    public static void regenerate(player p) {
        if (p.currenthealthpoints <= 0) return; // KO, rien à faire

        int heal = 0;

        switch (p.getAvatarClass()) {
            case "ADVENTURER":
                heal = 2;
                if (p.retrieveLevel() < 3) heal -= 1;
                break;

            case "ARCHER":
                heal = 1;
                if (p.inventory.contains("Magic Bow")) {
                    // S'assurer que le calcul donne un int correct
                    heal += Math.max((int)(p.currenthealthpoints / 8.0), 1);
                }
                break;

            case "DWARF":
                heal = 1;
                if (p.inventory.contains("Holy Elixir")) {
                    heal += 1;
                }
                break;

            default:
                heal = 0;
        }

        // Régénération seulement si < moitié PV
        if (p.currenthealthpoints < p.healthpoints / 2) {
            p.currenthealthpoints += heal;
        }

        // Limiter à la santé max
        if (p.currenthealthpoints > p.healthpoints) {
            p.currenthealthpoints = p.healthpoints;
        }
    }
}
