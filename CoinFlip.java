package javaGames;

import java.util.Random;
import java.util.Scanner;

public class CoinFlip extends Game {

    public void runGame(Player player){

        // print starting statement
        System.out.println();
        System.out.println("~ Playing Coin Flip ~");

        // initialise coin array
        String[] coin = {"Heads", "Tails"};

        // prompt user for input
        System.out.println("Enter H (Heads) or T (Tails), or Q to quit:");
        Scanner input = new Scanner(System.in);
        char playerChar = input.next().charAt(0);
        playerChar = Character.toUpperCase(playerChar);

        // check input is valid
        while ( !checkInput(playerChar, "[HTQ]") ) {
            System.out.println("InputError: Your input was invalid");
            System.out.println("Please enter H (Heads) or T (Tails), or Q to quit:");
            playerChar = input.next().charAt(0);
            playerChar = Character.toUpperCase(playerChar);
        }

        String playerChoice = Character.toString(playerChar);

        // while user wants to continue playing
        while (!playerChoice.equals("Q")) {

            // if player is limited decrement total games
            if (player instanceof LimitedPlayer) {
                ((LimitedPlayer) player).reduce();
            }

            // flip the coin - create a random int and select from coin array
            Random random = new Random();
            int randomInt = random.nextInt(2);
            String coinStr = coin[randomInt];

            // get player pick - convert player char to relevant string
            String playerStr = (checkInput(playerChar, "[H]")) ? "Heads" : "Tails";

            // print the outcome
            System.out.println("You picked " + playerStr + ", The coin landed on " + coinStr);
            if (playerStr.matches(coinStr)) {
                System.out.println("You guessed correct, you win!");
                player.setPoints(5);
            } else {
                System.out.println("You were wrong, better luck next time!");
                player.setPoints(-5);
            }

            // if player is limited check they still have games left
            if (player instanceof LimitedPlayer) {
                if ( ((LimitedPlayer) player).getGamesLeft() == 0) {
                    break;
                }
            }

            // prompt the user for input
            System.out.println("Enter H (Heads) or T (Tails), or Q to quit:");
            playerChar = input.next().charAt(0);
            playerChar = Character.toUpperCase(playerChar);

            // check input is valid
            while (!checkInput(playerChar, "[HTQ]")) {
                System.out.println("InputError: Your input was invalid");
                System.out.println("Please enter H (Heads) or T (Tails), or Q to quit:");
                playerChar = input.next().charAt(0);
                playerChar = Character.toUpperCase(playerChar);
            }

            playerChoice = Character.toString(playerChar);

        }

    }

}
