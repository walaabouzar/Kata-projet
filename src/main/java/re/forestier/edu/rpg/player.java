package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import re.forestier.edu.rpg.*;

import java.util.ArrayList;

public class player {

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp = 0;

    private Avatar avatar;

    private static final TreeMap<Integer, Integer> LEVEL_THRESHOLDS = new TreeMap<>();

    static {
        LEVEL_THRESHOLDS.put(2, 10);
        LEVEL_THRESHOLDS.put(3, 27);
        LEVEL_THRESHOLDS.put(4, 57);
        LEVEL_THRESHOLDS.put(5, 111);
    }

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {

        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF")) {
            return;
        }

        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;

        this.abilities = AbilityRepository.getAbilities().get(AvatarClass).get(1);

        this.avatar = AvatarFactory.create(avatarClass);
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public String getAvatarClass() {
        return AvatarClass;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }

    public int retrieveLevel() {
        int level = 1;
        for (Map.Entry<Integer, Integer> entry : LEVEL_THRESHOLDS.entrySet()) {
            if (xp >= entry.getValue()) {
                level = entry.getKey();
            } else {
                break;
            }
        }
        return level;
    }

    public int getXp() {
        return this.xp;
    }
}
