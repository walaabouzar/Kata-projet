package re.forestier.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.GestionObjets;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste l'affichage des informations d'un joueur via la classe {@link Affichage}.
 * Vérifie que les détails (nom, capacités, inventaire) sont correctement inclus,
 * même quand ceux-ci sont vides, et couvre le constructeur de la classe.
 */
public class AffichageTest {

    private player adventurer;

    @BeforeEach
    void setup() {
        adventurer = new player("Alice", "HeroA", "ADVENTURER", 100, new ArrayList<>());

        // Ajouter des objets au lieu de String
        adventurer.inventory.add(new GestionObjets("Épée", "Une épée de base", 2, 50));
        adventurer.inventory.add(new GestionObjets("Bouclier", "Un bouclier robuste", 3, 40));

        if (adventurer.abilities == null) {
            adventurer.abilities = new HashMap<>();
            adventurer.abilities.put("Force", 1);
        }
    }

    @Test
    void testAfficherJoueur() {
        String output = Affichage.afficherJoueur(adventurer);

        assertTrue(output.contains("Joueur HeroA joué par Alice"));
        assertTrue(output.contains("Niveau :"));
        assertTrue(output.contains("XP totale :"));
        adventurer.abilities.forEach((name, level) -> {
            assertTrue(output.contains(name + " : " + level));
        });

        // Vérifie le nom des objets
        adventurer.inventory.forEach(item -> {
            assertTrue(output.contains(item.getName()));
        });
    }

    @Test
    void testAfficherJoueurVide() {
        // Joueur avec inventaire et capacités vides
        player p = new player("Bob", "HeroB", "ARCHER", 50, new ArrayList<>());
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