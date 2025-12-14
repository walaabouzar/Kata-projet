package re.forestier.edu;

import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;

import re.forestier.edu.rpg.ObjectRewardService;
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

public class UpdatePlayerUnitTest {
    @Test
    void testUpdatePlayercreateobject() {
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
    @DisplayName("addXp retourne true on levelUp et l'ajout des abilities")
    void testAddXpLevelUp() {
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200); // ça fera passer p.xp > 10 → lvl up
        int newLevel = p.retrieveLevel();

        assertTrue(newLevel > currentLevel); // le joueur a monté de niveau
        assertTrue(result);                  // couvre le return true
        assertFalse(p.inventory.isEmpty());  // vérifie qu'un objet a été ajouté
        // abilities initiales avant level-up
        HashMap<String, Integer> oldAbilities = new HashMap<>(p.abilities);
        HashMap<String, Integer> levelAbilities = UpdatePlayer
            .abilitiesPerTypeAndLevel()
            .get("ARCHER")
            .get(newLevel);

        // Vérifie que toutes les abilities du nouveau level ont été ajoutées
        levelAbilities.forEach((ability, value) -> {
            assertEquals(value, p.abilities.get(ability));
        });

        // Vérifie que les anciennes abilities n'ont pas disparu
        oldAbilities.forEach((ability, value) -> {
            assertTrue(p.abilities.containsKey(ability));
        });


    }
    @Test
    @DisplayName("Verification de l'ajour d'un objet à partie de la liste des objets")
    void testRandomObjectAdded() {
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        int initialSize = p.inventory.size();
        int currentLevel = p.retrieveLevel();
        boolean result = UpdatePlayer.addXp(p, 200);
        int newLevel = p.retrieveLevel();
        assertTrue(newLevel > currentLevel); // le joueur a monté de niveau
        assertTrue(result);       
        assertEquals(initialSize + 1, p.inventory.size());

        //récupération de l'objet ajouté
        String addedObject = p.inventory.get(p.inventory.size() - 1);
        assertNotNull(addedObject);
        assertFalse(addedObject.isBlank());
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
    @Test
    public void testMajFinDeTourPrintsKoMessage() {
        player p1 = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());
        p1.currenthealthpoints = 0;

        // Capture console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // sauvegarde

        System.setOut(new PrintStream(outContent));

        try {
            UpdatePlayer.majFinDeTour(p1);
        } finally {
            System.setOut(originalOut); // restaure proprement
        }

        assertTrue(outContent.toString().contains("Le joueur est KO !"));
    }
    @Test
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
    @DisplayName("test d'ajout de currenthealthpoints à DWARF quand il possède Holy Elixir")
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
    @DisplayName("test d'ajout de currenthealthpoints à ARCHER quand il ne possède pas Magic Bow")
    void TestMajFinDeTourARCHER() {
        
        player p = new player("John", "Avatar", "ARCHER", 100, new ArrayList<>());


        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        assertFalse(p.inventory.contains("Magic Bow"), "L'inventaire ne doit pas avoir Magic Bow");        
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);


    }
    @Test
    @DisplayName("test d'ajout de currenthealthpoints à ARCHER quand il possède Magic Bow")
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
    @DisplayName("test d'ajout de currenthealthpoints à ADVENTURER quand level sup à 3")
    void TestMajFinDeTourADVENTURER() {
        
        player p = new player("John", "Avatar", "ADVENTURER", 100, new ArrayList<>());
        
        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        UpdatePlayer.addXp(p, 27);
        assertTrue(p.retrieveLevel() >= 3, "le niv devrait etre sup a 3");
        UpdatePlayer.majFinDeTour(p);
        assertEquals(51, p.currenthealthpoints);


    }
    @Test
    @DisplayName("test d'ajout de currenthealthpoints à ADVENTURER quand level inf à 3")
    void TestMajFinDeTourADVENTURERLevel3() {
        
        player p = new player("John", "Avatar", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 49; // statut KO
        assertTrue(p.retrieveLevel() < 3);
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);
    }

    @Test
    @DisplayName("test currenthealthpoints sup à healthpoints")
    void TestMajFinDeTourSupOuEgale() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.healthpoints = 50;
        p.currenthealthpoints = 51; // statut KO 

        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);
    }
    @Test
    @DisplayName("test currenthealthpoints inf à healthpoints ")
    void TestMajEgal() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.healthpoints = 100;
        p.currenthealthpoints = 50; // statut KO 

        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);
    }
    @Test
    @DisplayName("test currenthealthpoints egal healthpoints")
    void TestMajEgalCurrent_w_Health() {
        
        player p = new player("John", "Avatar", "DWARF", 100, new ArrayList<>());

        p.healthpoints = 50;
        p.currenthealthpoints = 50; // statut KO 
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);
        assertEquals(50, p.healthpoints);
    }
    @Test
    @DisplayName("test currenthealthpoints sup à healthpoints/2 mais inf à healthpoints")
    void TestMajCurrentInfHealth() {
        
        player p = new player("John", "Avatar", "ADVENTURER", 100, new ArrayList<>());

        p.healthpoints = 200;
        p.currenthealthpoints = 120; // statut KO 
        UpdatePlayer.majFinDeTour(p);
        assertEquals(200, p.healthpoints);
        assertEquals(120, p.currenthealthpoints);
    }
    



    }



    

    
