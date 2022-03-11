package javaGames;

public class Player implements Comparable<Player> {

    // Instance variables
    private String name;
    private int points = 0;

    // Constructor method
    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    // Method to return player name
    public String getName() {
        return this.name;
    }

    // Method to return player points
    public int getPoints() {
        return this.points;
    }

    // Method to set player points
    public void setPoints(int gamePoints) {
        if(this.points == 0 && gamePoints < 0) {
            // do nothing - points can't go below 0
        } else {
            this.points += gamePoints;
        }
    }

    // Method to compare players based on points
    @Override
    public int compareTo(Player p) {
        if (getPoints() < p.getPoints())
            return 1;
        else if (getPoints() > p.getPoints())
            return -1;
        else
            return 0;
    }

}
