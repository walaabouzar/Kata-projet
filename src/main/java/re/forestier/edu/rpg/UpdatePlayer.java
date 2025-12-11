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
        player.inventory.add(
            GameObjects.OBJECT_LIST[random.nextInt(GameObjects.OBJECT_LIST.length)]
        );

        // Add/upgrade abilities
        player.abilities.putAll(
            AbilityRepository.getAbilities()
                .get(player.getAvatarClass())
                .get(newLevel)
        );

        return true;
    }
    return false;
}

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(player player) {
        if(player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if(player.currenthealthpoints < player.healthpoints/2) {
            if(!player.getAvatarClass().equals("ADVENTURER")) {
                if(player.getAvatarClass().equals("DWARF")) {
                    if(player.inventory.contains("Holy Elixir")) {
                        player.currenthealthpoints+=1;
                    }
                    player.currenthealthpoints+=1;
                } else if(player.getAvatarClass().equals("ADVENTURER")) {
                    player.currenthealthpoints+=2;
                }


                if(player.getAvatarClass().equals("ARCHER")) {
                    player.currenthealthpoints+=1;
                    if(player.inventory.contains("Magic Bow")) {
                        player.currenthealthpoints+=player.currenthealthpoints/8-1;
                    }
                }
            } else {
                player.currenthealthpoints+=2;
                if(player.retrieveLevel() < 3) {
                    player.currenthealthpoints-=1;
                }
            }
        } else if(player.currenthealthpoints >= player.healthpoints/2){
            if(player.currenthealthpoints >= player.healthpoints) {
                player.currenthealthpoints = player.healthpoints;
                return;
            }
        }


        if(player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
        }
    }
}