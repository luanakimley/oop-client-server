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
        menu();
    }

    public static void menu() {
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

    private static void initialise(List<Player> list, Map<Integer, Player> map)
    {
        Player p1 = new Player("Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1);
        Player p2 = new Player("Viktor Axelsen", "Denmark", 1994, 1, 4, 1.94, Sector.MENS_SINGLES, 1);
        Player p3 = new Player("Tai Tzu Ying", "Chinese Taipei", 1994, 6, 20, 1.63, Sector.WOMENS_SINGLE, 1);
        Player p4 = new Player("Praveen Jordan", "Indonesia", 1993, 4, 26, 1.63, Sector.WOMENS_SINGLE, 1);
        Player p5 = new Player("Carolina Marin", "Spain", 1993, 6, 15, 1.72, Sector.WOMENS_SINGLE, 6);
        Player p6 = new Player("Chou Tien Chen", "Chinese Taipei", 1990, 1, 8, 1.8, Sector.MENS_SINGLES, 4);
        Player p7 = new Player("Anders Antonsen", "Denmark", 1997, 4, 27, 1.83, Sector.MENS_SINGLES, 3);
        Player p8 = new Player("Chen Qing Chen", "China", 1997, 6, 23, 1.64, Sector.WOMENS_DOUBLE, 1);
        Player p9 = new Player("Lee Zii Jia", "Malaysia", 1998, 3, 29, 1.86, Sector.MENS_SINGLES, 7);
        Player p10 = new Player("Greysia Polii", "Indonesia", 1987, 8, 11, 1.6, Sector.WOMENS_DOUBLE, 6);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);
        list.add(p10);

        map.put(1001, p1);
        map.put(1002, p2);
        map.put(1003, p3);
        map.put(1004, p4);
        map.put(1005, p5);
        map.put(1006, p6);
        map.put(1007, p7);
        map.put(1008, p8);
        map.put(1009, p9);
        map.put(1010, p10);


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

    private static void retrievePlayerByIdOption(Map<Integer, Player> map) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player ID: ");
        int id = keyboard.nextInt();

        Player found = retrievePlayerById(map, id);

        if(found != null) {
            System.out.println("\nPlayer found:");
            System.out.println(found);
        }
        else {
            System.out.println("\nPlayer with ID " + id + " not found.");
        }

    }

    private static Player retrievePlayerById(Map<Integer, Player> map, int id) {
        return map.get(id);
    }

}
