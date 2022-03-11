package javaGames;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors extends Game {

    public void runGame(Player player){

        // print starting statement
        System.out.println();
        System.out.println("~ Playing Rock, Paper, Scissors ~");

        // initialise choices array
        String[] choices = {"R", "P", "S"};

        // prompt user for input
        System.out.println("Enter R (rock), P (paper), or S (scissors) or Q to quit:");
        Scanner input = new Scanner(System.in);
        char playerChar = input.next().charAt(0);
        playerChar = Character.toUpperCase(playerChar);

        // check input is valid
        while ( !checkInput(playerChar, "[RPSQ]") ) {
            System.out.println("InputError: Your input was invalid");
            System.out.println("Please enter R (rock), P (paper), or S (scissors) or Q to quit:");
            playerChar = input.next().charAt(0);
            playerChar = Character.toUpperCase(playerChar);
        }

        String playerChoice = Character.toString(playerChar);

        // while users wants to continue playing
        while (!playerChoice.equals("Q")) {

            // if player is limited decrement total games
            if (player instanceof LimitedPlayer) {
                ((LimitedPlayer) player).reduce();
            }

            // make Bot choice - create a random int and select from choices array
            Random random = new Random();
            int randomInt = random.nextInt(3);
            String botChoice = choices[randomInt];

            String playerStr = charToString(playerChoice);
            String botStr = charToString(botChoice);

            // find who won - Print the outcome
            System.out.println("You picked " + playerStr + ", I chose " + botStr);
            if (playerChoice.equals(botChoice)) {
                System.out.println("Its a tie!");
            } else if (playerChoice.equals("R") && botChoice.equals("S")) {
                System.out.println("You win!");
                player.setPoints(5);
            } else if (playerChoice.equals("S") && botChoice.equals("P")) {
                System.out.println("You win!");
                player.setPoints(5);
            } else if (playerChoice.equals("P") && botChoice.equals("R")) {
                System.out.println("You win!");
                player.setPoints(5);
            } else {
                System.out.println("I win!");
                player.setPoints(-5);
            }

            // if player is limited check they still have games left
            if (player instanceof LimitedPlayer) {
                if ( ((LimitedPlayer) player).getGamesLeft() == 0) {
                    break;
                }
            }

            // prompt the user for input
            System.out.println("Enter R (rock), P (paper), or S (scissors) or Q to quit:");
            playerChar = input.next().charAt(0);
            playerChar = Character.toUpperCase(playerChar);

            // check input is valid
            while ( !checkInput(playerChar, "[RPSQ]") ) {
                System.out.println("InputError: Your input was invalid");
                System.out.println("Please enter R (rock), P (paper), or S (scissors) or Q to quit:");
                playerChar = input.next().charAt(0);
                playerChar = Character.toUpperCase(playerChar);
            }

            playerChoice = Character.toString(playerChar);

        }

    }

    // Method to convert char to string
    public static String charToString(String c) {
        String word = "placeholder";
        switch (c) {
            case "R":
                word = "Rock";
                break;

            case "P":
                word = "Paper";
                break;

            case "S":
                word = "Scissors";
                break;

        }
        return word;
    }

}
