package javaGames;

public class LimitedPlayer extends Player{

    private int totalGames = 15;

    // Limited player get (Ltd.) extension to their name and can only play 15 games
    public LimitedPlayer(String playerName) {
        super(playerName + " (Ltd.)");
    }

    // Method to decrement number of games left
    public void reduce(){
        this.totalGames -= 1;
    }

    // Method to return number of games left
    public int getGamesLeft() {
        return this.totalGames;
    }

}
