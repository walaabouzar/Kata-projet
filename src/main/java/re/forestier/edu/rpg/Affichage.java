package re.forestier.edu.rpg;


public class Affichage {

    public static String afficherJoueur(player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Joueur ").append(player.Avatar_name)
          .append(" joué par ").append(player.playerName)
          .append("\nNiveau : ").append(player.retrieveLevel())
          .append(" (XP totale : ").append(player.xp).append(")")
          .append("\n\nCapacités :");

        player.abilities.forEach((name, level) ->
            sb.append("\n   ").append(name).append(" : ").append(level)
        );

        sb.append("\n\nInventaire :");
        player.inventory.forEach(obj ->
            sb.append("\n   ")
            .append(obj.getName())
            .append(" — ")
            .append(obj.getDescription())
            .append(" | poids: ")
            .append(obj.getWeight())
            .append(" | valeur: ")
            .append(obj.getValue())
        );


        return sb.toString();
    }

    public static String afficherJoueurMarkdown(player player) {
    return "";
}

}
