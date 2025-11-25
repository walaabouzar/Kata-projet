package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class player {

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp = 0;

    // Niveaux triés
    private static final TreeMap<Integer, Integer> LEVEL_THRESHOLDS = new TreeMap<>();

    static {
        LEVEL_THRESHOLDS.put(2, 10);
        LEVEL_THRESHOLDS.put(3, 27);
        LEVEL_THRESHOLDS.put(4, 57);
        LEVEL_THRESHOLDS.put(5, 111);
    }

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    // === Constructeur inchangé ===
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    // === Getter pour la classe ===
    public String getAvatarClass() {
        return AvatarClass;
    }

    // === Ajouter de l’argent ===
    public void addMoney(int amount) {
        money += amount;
    }

    // === Retirer de l’argent ===
    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }

    // === Récupérer le niveau selon les seuils ===
    public int retrieveLevel() {
        int level = 1;  // niveau minimum

        for (Map.Entry<Integer, Integer> entry : LEVEL_THRESHOLDS.entrySet()) {
            if (xp >= entry.getValue()) {
                level = entry.getKey();
            } else {
                break;
            }
        }
        return level;
    }

    // === Getter XP ===
    public int getXp() {
        return this.xp;
    }
}
