package re.forestier.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.Affichage;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste l'affichage des informations d'un joueur via la classe {@link Affichage}.
 * Vérifie que les détails (nom, capacités, inventaire) sont correctement inclus,
 * même quand ceux-ci sont vides, et couvre le constructeur de la classe.
 */
public class AffichageTest {

    private Player adventurer;

    @BeforeEach
    void setup() {
        // Création d'un joueur avec inventaire et capacités
        adventurer = new Player("Alice", "HeroA", "ADVENTURER", 100, new ArrayList<>());
        // Ajouter un item à l'inventaire pour tester
        adventurer.inventory.add("Épée");
        adventurer.inventory.add("Bouclier");
        // Ajouter des capacités si elles sont nulles (par sécurité)
        if (adventurer.abilities == null) {
            adventurer.abilities = new HashMap<>();
            adventurer.abilities.put("Force", 1);
        }
    }

    @Test
    void testAfficherJoueur() {
        String output = Affichage.afficherJoueur(adventurer);

        // Vérifier que la sortie contient les informations de base
        assertTrue(output.contains("Joueur HeroA joué par Alice"));
        assertTrue(output.contains("Niveau :"));
        assertTrue(output.contains("XP totale :"));
        // Vérifier que les capacités sont présentes
        adventurer.abilities.forEach((name, level) -> {
            assertTrue(output.contains(name + " : " + level));
        });
        // Vérifier que les items de l'inventaire sont présents
        adventurer.inventory.forEach(item -> {
            assertTrue(output.contains(item));
        });
    }

    @Test
    void testAfficherJoueurVide() {
        // Joueur avec inventaire et capacités vides
        Player p = new Player("Bob", "HeroB", "ARCHER", 50, new ArrayList<>());
        p.abilities.clear();
        String output = Affichage.afficherJoueur(p);

        assertTrue(output.contains("Joueur HeroB joué par Bob"));
        assertTrue(output.contains("Capacités :"));
        assertTrue(output.contains("Inventaire :"));
    }

    // ===== Test pour couvrir le constructeur Affichage() =====
    @Test
    void testConstructor() {
        Affichage a = new Affichage();
        assertNotNull(a);
    }
}