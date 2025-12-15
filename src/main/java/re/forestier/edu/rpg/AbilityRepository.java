package re.forestier.edu.rpg;

import java.util.HashMap;

public class AbilityRepository {

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> ABILITIES
           = new HashMap<>();
    
    static{
        // ADVENTURER
        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
        HashMap<String, Integer> adventurerLevel1 = new HashMap<>();
        adventurerLevel1.put("INT", 1);
        adventurerLevel1.put("DEF", 1);
        adventurerLevel1.put("ATK", 3);
        adventurerLevel1.put("CHA", 2);
        adventurerMap.put(1, adventurerLevel1);

        HashMap<String, Integer> adventurerLevel2 = new HashMap<>();
        adventurerLevel2.put("INT", 2);
        adventurerLevel2.put("CHA", 3);
        adventurerMap.put(2, adventurerLevel2);

        HashMap<String, Integer> adventurerLevel3 = new HashMap<>();
        adventurerLevel3.put("ATK", 5);
        adventurerLevel3.put("ALC", 1);
        adventurerMap.put(3, adventurerLevel3);

        HashMap<String, Integer> adventurerLevel4 = new HashMap<>();
        adventurerLevel4.put("DEF", 3);
        adventurerMap.put(4, adventurerLevel4);

        HashMap<String, Integer> adventurerLevel5 = new HashMap<>();
        adventurerLevel5.put("VIS", 1);
        adventurerLevel5.put("DEF", 4);
        adventurerMap.put(5, adventurerLevel5);

        ABILITIES.put("ADVENTURER", adventurerMap);


        // ARCHER
        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
        HashMap<String, Integer> archerLevel1 = new HashMap<>();
        archerLevel1.put("INT", 1);
        archerLevel1.put("ATK", 3);
        archerLevel1.put("CHA", 1);
        archerLevel1.put("VIS", 3);
        archerMap.put(1, archerLevel1);

        HashMap<String, Integer> archerLevel2 = new HashMap<>();
        archerLevel2.put("DEF", 1);
        archerLevel2.put("CHA", 2);
        archerMap.put(2, archerLevel2);

        HashMap<String, Integer> archerLevel3 = new HashMap<>();
        archerLevel3.put("ATK", 3);
        archerMap.put(3, archerLevel3);

        HashMap<String, Integer> archerLevel4 = new HashMap<>();
        archerLevel4.put("DEF", 2);
        archerMap.put(4, archerLevel4);

        HashMap<String, Integer> archerLevel5 = new HashMap<>();
        archerLevel5.put("ATK", 4);
        archerMap.put(5, archerLevel5);

        ABILITIES.put("ARCHER", archerMap);


        // DWARF
        HashMap<Integer, HashMap<String, Integer>> dwarf = new HashMap<>();
        HashMap<String, Integer> dwarfLevel1 = new HashMap<>();
        dwarfLevel1.put("ALC", 4);
        dwarfLevel1.put("INT", 1);
        dwarfLevel1.put("ATK", 3);
        dwarf.put(1, dwarfLevel1);

        HashMap<String, Integer> dwarfLevel2 = new HashMap<>();
        dwarfLevel2.put("DEF", 1);
        dwarfLevel2.put("ALC", 5);
        dwarf.put(2, dwarfLevel2);

        HashMap<String, Integer> dwarfLevel3 = new HashMap<>();
        dwarfLevel3.put("ATK", 4);
        dwarf.put(3, dwarfLevel3);

        HashMap<String, Integer> dwarfLevel4 = new HashMap<>();
        dwarfLevel4.put("DEF", 2);
        dwarf.put(4, dwarfLevel4);

        HashMap<String, Integer> dwarfLevel5 = new HashMap<>();
        dwarfLevel5.put("CHA", 1);
        dwarf.put(5, dwarfLevel5);

        ABILITIES.put("DWARF", dwarf);
        
        // GOBLIN
        HashMap<Integer, HashMap<String, Integer>> GoblinMap = new HashMap<>();
        HashMap<String, Integer> GoblinLevel1 = new HashMap<>();
        GoblinLevel1.put("INT", 2);
        GoblinLevel1.put("ATK", 2);
        GoblinLevel1.put("ALC", 1);
        GoblinMap.put(1, GoblinLevel1);

        HashMap<String, Integer> GoblinLevel2 = new HashMap<>();
        GoblinLevel2.put("ATK", 3);
        GoblinLevel2.put("ALC", 4);
        GoblinMap.put(2, GoblinLevel2);

        HashMap<String, Integer> GoblinLevel3 = new HashMap<>();
        GoblinLevel3.put("VIC", 1);
        GoblinMap.put(3, GoblinLevel3);

        HashMap<String, Integer> GoblinLevel4 = new HashMap<>();
        GoblinLevel4.put("DEF", 1);
        GoblinMap.put(4, GoblinLevel4);

        HashMap<String, Integer> GoblinLevel5 = new HashMap<>();
        GoblinLevel5.put("DEF", 2);
        GoblinLevel5.put("ATK", 4);
        GoblinMap.put(5, GoblinLevel5);

        ABILITIES.put("GOBLIN", GoblinMap);
    }
    private AbilityRepository() {
        // empêche l’instanciation
    }

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> getAbilitiesPerTypeAndLevel() {
        return ABILITIES;
    }

    public static HashMap<String, Integer> getAbilities(String avatarClass, int level) {
        return ABILITIES
                .getOrDefault(avatarClass, new HashMap<>())
                .getOrDefault(level, new HashMap<>());
    }
}


