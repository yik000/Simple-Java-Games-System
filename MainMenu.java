package javaGames;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenu {

    // Main method
    public static void main(String[] args) {

        System.out.println("\n~~ Main Menu ~~");

        // create scanner for user input
        Scanner input = new Scanner(System.in);

        // display main menu prompt and get user option
        int option = mainMenuPrompt(input);

        // while quit is not chosen - create new player, display and run games
        while (option != 2) {

            // prompt for player name and create new player
            System.out.println("Please enter a name:");
            String playerName = input.nextLine();
            Player player = new Player(playerName);

            // prompt for play style
            System.out.println("Would you like to play limited to 15 games? (Enter Y/N):");
            char playStyle = input.next().charAt(0);
            playStyle = Character.toUpperCase(playStyle);
            // check input
            while(!Character.toString(playStyle).matches("[YN]")) {
                System.out.println("InputError: Please enter either Y or N:");
                playStyle = input.next().charAt(0);
                playStyle = Character.toUpperCase(playStyle);
            }
            if(Character.toString(playStyle).matches("Y")) {
                player = new LimitedPlayer(playerName);
            }

            // display game selection - Choose a game
            displayGameSelection( player.getName() );
            int gameChoice = input.nextInt();
            input.nextLine();

            // While the player has not chosen to return to main menu, run games and display game choices
            while (gameChoice != -1) {

                // if player scores more than 50 points they become VIP
                if (!(player instanceof VIPPlayer) && player.getPoints() >= 50) {
                    player = new VIPPlayer(player.getName(), player.getPoints());
                }

                // run selected game
                switch (gameChoice) {

                    case 1:
                        CoinFlip CF = new CoinFlip();
                        CF.runGame(player);
                        break;

                    case 2:
                        RockPaperScissors RPS = new RockPaperScissors();
                        RPS.runGame(player);
                        break;

                }

                // if player is limited check they still have games left
                if (player instanceof LimitedPlayer) {
                    if ( ((LimitedPlayer) player).getGamesLeft() == 0) {
                        System.out.println("\nLooks like you are out of games!");
                        break;
                    }
                }

                // display game selection and prompt for input
                System.out.println();
                displayGameSelection( player.getName() );
                gameChoice = input.nextInt();
                input.nextLine();

            }

            // Write player details to file
            writePlayerDetails(player);

            // Display main menu prompt
            System.out.println("\n~~ Back to the Main Menu ~~");
            option = mainMenuPrompt(input);

        }

        // Display leaderboard and exit system
        displayLeaderboard();
        input.close();
        System.exit(0);

    }


    // Method for main menu prompt
    public static int mainMenuPrompt(Scanner input) {

        // Prompt for new player or quit
        System.out.println("Please choose an option:");
        System.out.println("1. New Player");
        System.out.println("2. Exit the system");
        int option = input.nextInt();
        input.nextLine();
        // check input
        while (option != 1 && option != 2) {
            System.out.println("InputError: Please enter either 1 or 2:");
            option = input.nextInt();
            input.nextLine();
        }
        return option;

    }


    // Method to display player leaderboard
    public static void displayLeaderboard() {

        // display leaderboard
        System.out.println("\n~~ Leaderboard ~~");
        System.out.printf("%-10s : %-10s", "Player", "Points");
        System.out.printf("\n---------- : ----------");
        Player[] playersArr = readPlayerDetails(); // read player details stored in external csv file
        Arrays.sort(playersArr); // sort player in descending order of points
        for (Player p : playersArr) {
            System.out.printf("\n%-10s : %-10d",p.getName(), p.getPoints());
        }
        System.out.println();

    }


    // Method to display game selection
    public static void displayGameSelection(String playerName) {

        // print game selection
        System.out.println("Hello " + playerName + ". Please choose a game, or -1 to return to the Main Menu:");
        System.out.println("1: Coin Flip");
        System.out.println("2: Rock Paper Scissors");

    }


    // Method to write player details to players file
    public static void writePlayerDetails(Player player) {

        File file = new File("players.csv");
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(file, true));
            output.println(player.getName() + "," + player.getPoints());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }

    }


    // Method to read player details from players file - returns array
    public static Player[] readPlayerDetails() {

        File file = new File("players.csv");
        Scanner input = null;

        // Count number of player records
        int count = 0;
        try {
            input = new Scanner(file);
            while(input.hasNext()) {
                String line = input.nextLine();
                count++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // create player array using count
        Player[] playerList = new Player[count];
        int index = 0; // array index
        try {
            input = new Scanner(file);
            while(input.hasNext()) {
                String line = input.nextLine();
                String[] playerDetails = line.split(",");
                Player p = new Player(playerDetails[0]);
                p.setPoints(Integer.parseInt(playerDetails[1]));
                playerList[index] = p;
                index++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

        return playerList;

    }


}
