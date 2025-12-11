package re.forestier.edu.rpg;

import java.util.HashMap;

public class AbilityProvider {

    // Récupère les abilities pour une classe et un niveau
    public static HashMap<String, Integer> getAbilitiesFor(String avatarClass, int level) {
        HashMap<Integer, HashMap<String, Integer>> classMap = AbilityRepository.getAbilities().get(avatarClass);
        if (classMap != null) {
            return classMap.getOrDefault(level, new HashMap<>());
        }
        return new HashMap<>();
    }
}
