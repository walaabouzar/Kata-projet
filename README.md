# Projet RPG Java – Kata amélioré

## Description
Ce projet est un **jeu de rôle (RPG) codé en Java**, développé dans le cadre d’un TP de Génie Logiciel.  
Objectifs : refactorer un code existant, ajouter des fonctionnalités, et tester le tout avec la méthodologie **TDD**.

---

## Fonctionnalités principales
- Gestion des personnages (`player`) : nom, titre, classe, argent, inventaire, compétences et niveau.
- Gestion des niveaux et des compétences via `UpdatePlayer` : ajout d’expérience, montée de niveau, récompenses.
- Gestion des objets : inventaire, poids maximal, vente.
- Affichage du joueur en **texte** et en **Markdown**.
- Ajout du rôle **Gobelin** avec ses niveaux et compétences.

---
Pourquoi les mutations changent après refactoring ?

PIT modifie légèrement le code pour tester si les tests détectent les changements.

Après refactoring, certaines conditions ou blocs ont été modifiés, regroupés ou supprimés, et des portions identiques ont été mutualisées.

Les tests existants détectent toujours les erreurs possibles, mais certaines mutations que PIT générait avant ne sont plus présentes car le code est maintenant plus simple et efficace.

## Tests et TDD
- Tous les tests sont réalisés avec **JUnit 5** et **ApprovalTests**.
- Couverture du code : 100% avec **JaCoCo**.
- Démarche TDD :
  1. Écriture du test → test rouge
  2. Implémentation minimale → compilation OK
  3. Implémentation complète → test vert
  4. Commit final

Commandes utiles :
```bash
./gradlew test
./gradlew jacocoTestReport
