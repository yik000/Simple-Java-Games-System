package javaGames;

public abstract class Game {

    // Method run game
    public abstract void runGame(Player player);

    // Method to check input from given regex string
    public static boolean checkInput(Character userInput, String validInput) {
        // Return true if the input is valid
        return Character.toString(userInput).matches(validInput);
    }

}
