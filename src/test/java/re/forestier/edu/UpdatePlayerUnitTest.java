package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdatePlayerUnitTest {
    @Test
    void testUpdatePlayercreate8object() {
        // Test pour couvrir la déclaration de classe
        UpdatePlayer updatePlayer = new UpdatePlayer();
        assertNotNull(updatePlayer, "UpdatePlayer devrait pouvoir être instancié");
    }
    @Test
    @DisplayName("test d'addition de la valeur xp")
    void testaddXpAdditionToPlayer() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player, 15);
        assertEquals(15,player.getXp()); 
    }
    @Test
    @DisplayName("test d'égalite des niveaux and return false")
    void testaddXpLevelToPlayer() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        int currentLevel = player.retrieveLevel(); 
        boolean res = UpdatePlayer.addXp(player, 5);

        int newLevel = player.retrieveLevel(); 
        assertEquals(currentLevel, newLevel);//cas ou le joueur ne monte pas de niveau
        assertFalse(res);

    }
    @Test
    @DisplayName("addXp returns true on level-up")
    void testAddXpLevelUp() {
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200); // ça fera passer p.xp > 10 → lvl up
        int newLevel = p.retrieveLevel();

        assertTrue(newLevel > currentLevel); // le joueur a monté de niveau
        assertTrue(result);                  // couvre le return true
        assertFalse(p.inventory.isEmpty());  // vérifie qu'un objet a été ajouté
    }
    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourKo() {
        
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 0; // statut KO

        UpdatePlayer.majFinDeTour(p);

        // Vérifie que currenthealthpoints reste à 0
        assertEquals(0, p.currenthealthpoints);
    }
        void TestMajFinDeTourArcherCPLessHalfHP() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        assertFalse(p.inventory.contains("Holy Elixir"));


        UpdatePlayer.majFinDeTour(p);
        

        // Vérifie que currenthealthpoints reste à 0
        assertEquals(50, p.currenthealthpoints);

    }
    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourCPLessHalfHP() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.inventory = new ArrayList<>();        // création de la liste
        p.inventory.add("Holy Elixir");               // ajout de l'élément
        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO

        UpdatePlayer.majFinDeTour(p);

        // Vérifie que currenthealthpoints reste à 0
        assertEquals(51, p.currenthealthpoints);

    }
    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourARCHER() {
        
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        assertFalse(p.inventory.contains("Magic Bow"), "L'inventaire ne doit pas contenir Magic Bow au départ");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);


    }
    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourARCHERMagicbow() {
        
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 47; // statut KO
        p.inventory = new ArrayList<>();        // création de la liste
        p.inventory.add("Magic Bow");  

        UpdatePlayer.majFinDeTour(p);
        assertEquals(53, p.currenthealthpoints);
        // faut que le resultat soit int dans refactoring tu le fait car ya devision il faut faire le cast 

    }
    @Test
    void TestMajFinDeTourADVENTURER() {
        
        player p = new player("John", "Avatar", "ADVENTURER", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        UpdatePlayer.addXp(p, 200);

        assertTrue(p.retrieveLevel() >= 3, "le niv devrait etre sup a 3");

        UpdatePlayer.majFinDeTour(p);
        
        
        assertEquals(51, p.currenthealthpoints);


    }
    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourADVENTURERLevel3() {
        
        player p = new player("John", "Avatar", "ADVENTURER", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO

        UpdatePlayer.majFinDeTour(p);
        assertTrue(p.retrieveLevel() < 3);
        assertEquals(50, p.currenthealthpoints);
        

    }

    @Test
    @DisplayName("test if  player is ko should not change health")
    void TestMajFinDeTourSupOuEgale() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.healthpoints = 50;
        p.currenthealthpoints = 51; // statut KO 

        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);
        player p1 = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p1.healthpoints = 50;
        p1.currenthealthpoints = 50; // statut KO 

        UpdatePlayer.majFinDeTour(p1);
        assertEquals(50, p1.currenthealthpoints);



    }






   




        

    }



    

    

