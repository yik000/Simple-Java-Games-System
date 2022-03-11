package javaGames;

public class VIPPlayer extends Player {

    // VIP Player gets (VIP) extension to their name
    public VIPPlayer(String playerName, int points) {
        super(playerName + " (VIP)");
        super.setPoints(points);
    }

    // Method to set player points - VIPs get double game points
    @Override
    public void setPoints(int gamePoints) {
        // call super method but with double points
        super.setPoints(gamePoints * 2);
    }

}
