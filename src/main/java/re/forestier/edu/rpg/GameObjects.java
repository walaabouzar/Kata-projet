package re.forestier.edu.rpg;

import java.util.List;
import java.util.Random;

public class GameObjects {
    
    private static final List<GestionObjets> objectList = List.of(
        new GestionObjets("Lookout Ring", "Prevents surprise attacks", 1, 50),
        new GestionObjets("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 20),
        new GestionObjets("Draupnir", "Increases XP gained by 100%", 2, 100),
        new GestionObjets("Magic Charm", "Magic +10 for 5 rounds", 1, 40),
        new GestionObjets("Rune Staff of Curse", "May burn your enemies... or yourself", 4, 120),
        new GestionObjets("Combat Edge", "Well, that's an edge", 2, 60),
        new GestionObjets("Holy Elixir", "Recover your HP", 1, 30)
    );

    private static final Random RANDOM = new Random();

    private GameObjects() {}

    public static GestionObjets giveRandomObject() {
        return objectList.get(RANDOM.nextInt(objectList.size()));
    }

}
