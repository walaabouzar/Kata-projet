package re.forestier.edu.rpg;

public class HealthManager {

    public static void regenerate(player p) {



        // Régénération seulement si PV < moitié PV max
        if (p.currenthealthpoints < p.healthpoints / 2) {

            // ----- CAS : la classe n'est PAS ADVENTURER -----
            if (!p.getAvatarClass().equals("ADVENTURER")) {

                // ---- DWARF ----
                if (p.getAvatarClass().equals("DWARF")) {

                    if (p.inventory.contains("Holy Elixir")) {
                        p.currenthealthpoints += 1;  // bonus elixir
                    }

                    p.currenthealthpoints += 1;      // heal nain
                }

                // ---- (Bloc ADVENTURER impossible mais présent dans ton code 2) ----
                else if (p.getAvatarClass().equals("ADVENTURER")) {
                    p.currenthealthpoints += 2;
                }

                // ---- ARCHER ----
                if (p.getAvatarClass().equals("ARCHER")) {

                    p.currenthealthpoints += 1;  // heal de base

                    if (p.inventory.contains("Magic Bow")) {
                        // REPRODUCTION EXACTE du code 2 (même bug)
                        p.currenthealthpoints += p.currenthealthpoints / 8 - 1;
                    }
                }
            }

            // ----- CAS : ADVENTURER (logique du else dans code 2) -----
            else {
                p.currenthealthpoints += 2;

                if (p.retrieveLevel() < 3) {
                    p.currenthealthpoints -= 1;
                }
            }
        }

        // Si PV >= moitié → seulement clamp si besoin
        else if (p.currenthealthpoints >= p.healthpoints / 2) {

            if (p.currenthealthpoints >= p.healthpoints) {
                p.currenthealthpoints = p.healthpoints;
                return;
            }
        }

        // Clamp final → PV max
        if (p.currenthealthpoints >= p.healthpoints) {
            p.currenthealthpoints = p.healthpoints;
        }
    }
}
