package re.forestier.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerUnitTest {

    private Player adventurer;
    private Player archer;
    private Player dwarf;

    @BeforeEach
    void setup() {
        // Prépare trois joueurs de classes différentes pour les tests
        adventurer = new Player("Alice", "HeroA", "ADVENTURER", 100, new ArrayList<>());
        archer = new Player("Robin", "HeroB", "ARCHER", 50, new ArrayList<>());
        dwarf = new Player("Gimli", "HeroC", "DWARF", 70, new ArrayList<>());
    }

    // === Vérifie qu’un joueur est bien créé avec les bonnes infos de base ===
    @Test
    void testValidPlayerCreation() {
        assertEquals("Alice", adventurer.playerName);
        assertEquals("HeroA", adventurer.Avatar_name);
        assertEquals("ADVENTURER", adventurer.getAvatarClass());
        assertEquals(100, adventurer.money);
        assertNotNull(adventurer.abilities);    // Les capacités doivent exister
        assertNotNull(adventurer.inventory);    // L’inventaire doit exister
    }

    // === Vérifie que chaque classe de joueur a bien ses capacités initialisées ===
    @Test
    void testAllClassesAbilities() {
        assertNotNull(adventurer.abilities);
        assertEquals("ADVENTURER", adventurer.getAvatarClass());

        assertNotNull(archer.abilities);
        assertEquals("ARCHER", archer.getAvatarClass());

        assertNotNull(dwarf.abilities);
        assertEquals("DWARF", dwarf.getAvatarClass());
    }

    // === Vérifie le comportement si on essaie de créer un joueur avec une classe invalide ===
    @Test
    void testInvalidAvatarClass() {
        Player invalid = new Player("X", "Y", "INVALID", 0, null);
        assertNull(invalid.getAvatarClass());   // La classe doit être rejetée
        assertNull(invalid.inventory);          // Pas d’inventaire créé
        assertNull(invalid.abilities);          // Pas de capacités créées
    }

    // === Vérifie l’ajout d’argent (positif, nul ou négatif) ===
    @Test
    void testAddMoney() {
        adventurer.addMoney(50);
        assertEquals(150, adventurer.money);    // +50 → 150

        adventurer.addMoney(0);
        assertEquals(150, adventurer.money);    // +0 → inchangé

        adventurer.addMoney(-20);
        assertEquals(130, adventurer.money);    // -20 → 130 (fonctionne comme une réduction)
    }

    // === Vérifie le retrait normal d’argent ===
    @Test
    void testRemoveMoney() {
        adventurer.removeMoney(40);
        assertEquals(60, adventurer.money);     // 100 - 40 = 60
    }

    // === Vérifie qu’on ne peut pas retirer plus d’argent qu’on en a ===
    @Test
    void testRemoveMoneyTooHigh() {
        // Doit lever une exception si on essaie de retirer 200 alors qu’on n’a que 100
        assertThrows(IllegalArgumentException.class, () -> adventurer.removeMoney(200));
    }

    @Test
    void testRemoveMoneyNull() {
        // doit passer si on retire une valeur = 0
        adventurer.removeMoney(100);
        assertEquals(0 , adventurer.money);
    }

    // === Vérifie le retrait d’un montant négatif (ce qui revient à ajouter de l’argent) ===
    @Test
    void testRemoveMoneyNegative() {
        int before = adventurer.money;
        adventurer.removeMoney(-10);            // Retirer -10 = ajouter 10
        assertEquals(before + 10, adventurer.money);
    }

    
    // === Vérifie que le niveau correspond bien à l’XP selon les seuils définis ===
    @Test
    void testRetrieveLevelThresholds() {
        // Tableau : {XP, niveau attendu}
        int[][] thresholds = {{0,1},{9,1},{10,2},{26,2},{27,3},{56,3},{57,4},{110,4},{111,5},{160,5}};
        for (int[] t : thresholds) {
            Player p = new Player("Test", "T", "ADVENTURER", 0, new ArrayList<>());
            UpdatePlayer.addXp(p, t[0]);
            assertEquals(t[1], p.retrieveLevel(), "Pour XP=" + t[0] + ", le niveau devrait être " + t[1]);
        }
    }

    // === Vérifie qu’un joueur commence au niveau 1 s’il n’a pas d’XP ===
    @Test
    void testRetrieveLevelWithoutXp() {
        Player p = new Player("Test", "T", "ADVENTURER", 0, new ArrayList<>());
        assertEquals(1, p.retrieveLevel());
    }

    // === Vérifie qu’ajouter assez d’XP fait bien monter le joueur d’un niveau ===
    @Test
    void testAddXpLevelUp() {
        boolean leveledUp = UpdatePlayer.addXp(adventurer, 20); // Suffisant pour passer niveau 2
        assertTrue(leveledUp);                  // Doit indiquer une montée de niveau
        assertEquals(2, adventurer.retrieveLevel());
    }

    // === Vérifie qu’ajouter peu d’XP ne fait pas monter de niveau ===
    @Test
    void testAddXpNoLevelUp() {
        int lvl = adventurer.retrieveLevel();   // Niveau initial
        boolean leveledUp = UpdatePlayer.addXp(adventurer, 5); // Pas assez pour monter
        assertFalse(leveledUp);                 // Pas de montée de niveau
        assertEquals(lvl, adventurer.retrieveLevel());
    }

    // === Vérifie la régénération de santé en fin de tour (via majFinDeTour) ===
    @Test
    void testMajFinDeTourVariousCases() {
        adventurer.healthpoints = 10; // Santé max = 10

        // Cas 1 : joueur KO → reste à 0
        adventurer.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(adventurer);
        assertEquals(0, adventurer.currenthealthpoints);

        // Cas 2 : santé basse → régénère un peu
        adventurer.currenthealthpoints = 4;
        UpdatePlayer.majFinDeTour(adventurer);
        assertTrue(adventurer.currenthealthpoints > 4);

        // Cas 3 : santé moyenne → ne dépasse pas la santé max
        adventurer.currenthealthpoints = 8;
        UpdatePlayer.majFinDeTour(adventurer);
        assertTrue(adventurer.currenthealthpoints <= adventurer.healthpoints);

        // Cas 4 : santé pleine → reste à 10
        adventurer.currenthealthpoints = 10;
        UpdatePlayer.majFinDeTour(adventurer);
        assertEquals(10, adventurer.currenthealthpoints);
    }

    // === Vérifie que la méthode getAvatarClass renvoie bien la classe du joueur ===
    @Test
    void testGetAvatarClass() {
        assertEquals("ADVENTURER", adventurer.getAvatarClass());
        assertEquals("ARCHER", archer.getAvatarClass());
        assertEquals("DWARF", dwarf.getAvatarClass());
    }

    // === Vérifie qu’un joueur commence avec 0 XP ===
    @Test
    void testGetXpInitial() {
        assertEquals(0, adventurer.getXp());
    }

    // === Vérifie que les capacités sont bien initialisées (non vides) pour chaque classe ===
    @Test
    void testAbilitiesInitialization() {
        assertFalse(adventurer.abilities.isEmpty());
        assertFalse(archer.abilities.isEmpty());
        assertFalse(dwarf.abilities.isEmpty());
    }

    // === Vérifie que l’inventaire est bien initialisé (non null) pour chaque joueur ===
    @Test
    void testInventoryInitialization() {
        assertNotNull(adventurer.inventory);
        assertNotNull(archer.inventory);
        assertNotNull(dwarf.inventory);
    }

    @Test 
    void testGetterXpAfterGain() {
       // XP initial = 0
         assertEquals(0, adventurer.getXp());

    // On ajoute 30 XP
         UpdatePlayer.addXp(adventurer, 30);

    // Maintenant, getXp doit renvoyer 30
         assertEquals(30, adventurer.getXp());
}

}