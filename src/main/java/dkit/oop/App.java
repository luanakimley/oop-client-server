package dkit.oop;

import java.util.*;

/**
 * Luana Kimley
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("Project part 1 - CA5");
        List<Player> playersList = new ArrayList<>();

        Map<Integer, Player> playersHashMap = new HashMap<>();

        initialise(playersList, playersHashMap);


        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Display players in order of...\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int DISPLAY_ALL_PLAYERS = 1;
        final int RETRIEVE_PLAYER_BY_ID = 2;
        final int DISPLAY_PLAYERS_IN_ORDER = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n" + MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case DISPLAY_ALL_PLAYERS:
                        System.out.println("\nDisplay all players option chosen");
                        displayPlayerList(playersList);
                        break;
                    case RETRIEVE_PLAYER_BY_ID:
                        System.out.println("\nRetrieve player by ID option chosen");
                        retrievePlayerByIdOption(playersHashMap);
                        break;
                    case DISPLAY_PLAYERS_IN_ORDER:
                        System.out.println("\nDisplay players in order option chosen");
                        break;
                    case EXIT:
                        System.out.println("\nExiting app, goodbye.");
                        break;
                    default:
                        System.out.print("Invalid input - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.print("Invalid input - please enter number in range");
            }
        } while (option != EXIT);


    }

    private static void displayPlayerList(List<Player> list) {
        System.out.printf("%-40s%-30s%-18s%-14s%-20s%-17s\n", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
        System.out.println("====================================    ==========================    ==============    ==========    ================    ============");
        for(Player p : list) {
            System.out.printf("%-40s%-30s%-18s%-14s%-20s%-17s\n",
                    p.getName(),
                    p.getNationality(),
                    p.getDateOfBirth(),
                    p.getHeight() + " m",
                    p.getSector(),
                    p.getWorldRank());
        }
    }

}
