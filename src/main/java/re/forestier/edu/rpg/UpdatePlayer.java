package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

import re.forestier.edu.rpg.GameObjects;

import re.forestier.edu.rpg.*;



public class UpdatePlayer {

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        return AbilityRepository.getAbilities();
    }

    public static boolean addXp(player player, int xp) {
    int currentLevel = player.retrieveLevel();
    player.xp += xp;
    int newLevel = player.retrieveLevel();

    if (newLevel != currentLevel) {
        // Player leveled-up!
        Random random = new Random();
        player.inventory.add(GameObjects.OBJECT_LIST[random.nextInt(GameObjects.OBJECT_LIST.length)]);

        // Add/upgrade abilities using AbilityProvider
        HashMap<String, Integer> newAbilities = AbilityProvider.getAbilitiesFor(player.getAvatarClass(), newLevel);
        newAbilities.forEach((ability, level) -> {
            player.abilities.put(ability, level);
        });

        return true;
    }
    return false;
}


    // majFinDeTour met à jour les points de vie
public static void majFinDeTour(player player) {
    if (player.currenthealthpoints == 0) {
        System.out.println("Le joueur est KO !");
        return;
    }

    HealthManager.regenerate(player);
}

}