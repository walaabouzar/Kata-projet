package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;
import re.forestier.edu.rpg.GameObjects;

import re.forestier.edu.rpg.*;



public class UpdatePlayer {

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        return AbilityRepository.getAbilitiesPerTypeAndLevel();
    }

    public static boolean addXp(player player, int xp) {
    int currentLevel = player.retrieveLevel();
    player.xp += xp;
    int newLevel = player.retrieveLevel();

    if (newLevel != currentLevel) {
        levelUp(player, newLevel);
        return true;
    }
    return false;
}

    private static void levelUp(player p, int level) {
        GestionObjets reward = GameObjects.giveRandomObject();
        p.addObject(reward);
        boolean addobj = p.addObject(reward);
        if(addobj){
            p.inventory.add(reward);}
        

        HashMap<String, Integer> newAbilities =
                abilitiesPerTypeAndLevel().get(p.getAvatarClass()).get(level);
        p.abilities.putAll(newAbilities);
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