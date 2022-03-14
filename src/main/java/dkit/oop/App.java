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
        System.out.println("Project part 1 - CA5");
        menu();
    }

    public static void menu() {
        List<Player> playersList = new ArrayList<>();

        Map<Integer, Player> playerIdMap = new HashMap<>();

        Map<Player, Racket> playerRacketMap = new TreeMap<>(new ComparatorPlayerNationality());

        Queue<Player> playerPriorityQueue1 = new PriorityQueue<>(new ComparatorPlayerRank());

        Queue<Player> playerPriorityQueue2 = new PriorityQueue<>(new ComparatorPlayerRankWithinNationality());

        initialise(playersList, playerIdMap, playerRacketMap, playerPriorityQueue2);


        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Display players and racket in order of nationality\n"
                + "4. PriorityQueue Sequence Simulation\n"
                + "5. PriorityQueue Two-Field Comparison Demo (rank within nationality)\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int DISPLAY_ALL_PLAYERS = 1;
        final int RETRIEVE_PLAYER_BY_ID = 2;
        final int DISPLAY_PLAYERS_IN_ORDER = 3;
        final int PRIORITY_QUEUE_SIMULATION = 4;
        final int PRIORITY_QUEUE_TWO_FIELD = 5;
        final int EXIT = 6;

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
                        display(playersList);
                        break;
                    case RETRIEVE_PLAYER_BY_ID:
                        System.out.println("\nRetrieve player by ID option chosen");
                        retrievePlayerByIdOption(playerIdMap);
                        break;
                    case DISPLAY_PLAYERS_IN_ORDER:
                        System.out.println("\nDisplay players and the racket they use in order of player nationality");
                        display(playerRacketMap);
                        break;
                    case PRIORITY_QUEUE_SIMULATION:
                        priorityQueueSequenceSimulation(playerPriorityQueue1);
                        break;
                    case PRIORITY_QUEUE_TWO_FIELD:
                        System.out.println("\nPriorityQueue Two-Field Comparison Demo (rank within nationality)");
                        display(playerPriorityQueue2);
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

    private static void initialise(List<Player> list, Map<Integer, Player> playerIdMap, Map<Player, Racket> playerRacketMap, Queue<Player> playerPriorityQueue)
    {
        Player p1 = new Player("Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1);
        Player p2 = new Player("Viktor Axelsen", "Denmark", 1994, 1, 4, 1.94, Sector.MENS_SINGLES, 1);
        Player p3 = new Player("Tai Tzu Ying", "Chinese Taipei", 1994, 6, 20, 1.63, Sector.WOMENS_SINGLE, 1);
        Player p4 = new Player("Praveen Jordan", "Indonesia", 1993, 4, 26, 1.63, Sector.MIXED_DOUBLES, 5);
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

        playerIdMap.put(1001, p1);
        playerIdMap.put(1002, p2);
        playerIdMap.put(1003, p3);
        playerIdMap.put(1004, p4);
        playerIdMap.put(1005, p5);
        playerIdMap.put(1006, p6);
        playerIdMap.put(1007, p7);
        playerIdMap.put(1008, p8);
        playerIdMap.put(1009, p9);
        playerIdMap.put(1010, p10);

        Racket yonexAstrox88s = new Racket("Yonex", "Astrox 88s");
        Racket yonexDuoraZStrike = new Racket("Yonex", "Duora Z Strike");
        Racket victorThrusterF = new Racket("Victor", "Thruster F");
        Racket liningTurbo = new Racket("Lining", "Turbo Charging 75 EX");
        Racket victorAuraspeed = new Racket("Victor", "Auraspeed 90K");
        Racket liningFlame = new Racket("Lining", "Flame N50");
        Racket yonexAstrox99Pro = new Racket("Yonex", "Astrox 99 Pro");
        Racket victorDX9X = new Racket("Victor", "DX-9X");

        playerRacketMap.put(p1, yonexAstrox88s);
        playerRacketMap.put(p2, yonexDuoraZStrike);
        playerRacketMap.put(p3, victorThrusterF);
        playerRacketMap.put(p4, liningTurbo);
        playerRacketMap.put(p5, yonexDuoraZStrike);
        playerRacketMap.put(p6, yonexDuoraZStrike);
        playerRacketMap.put(p7, victorAuraspeed);
        playerRacketMap.put(p8, liningFlame);
        playerRacketMap.put(p9, yonexAstrox99Pro);
        playerRacketMap.put(p10, victorDX9X);

        playerPriorityQueue.add(p1);
        playerPriorityQueue.add(p2);
        playerPriorityQueue.add(p3);
        playerPriorityQueue.add(p4);
        playerPriorityQueue.add(p5);
        playerPriorityQueue.add(p6);
        playerPriorityQueue.add(p7);
        playerPriorityQueue.add(p8);
        playerPriorityQueue.add(p9);
        playerPriorityQueue.add(p10);

    }



    private static void display(List<Player> list) {
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

    private static void display(Map<Player, Racket> map) {
        System.out.printf("%-40s%-30s%-18s%-14s%-20s%-16s%-21s\n", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank", "Racket Used");
        System.out.println("====================================    ==========================    ==============    ==========    ================    ============    ==========================");

        for (Map.Entry<Player, Racket> entry : map.entrySet()) {
            Player p = entry.getKey();
            Racket r = entry.getValue();
                System.out.printf("%-40s%-30s%-18s%-14s%-20s%-16s%-21s\n",
                        p.getName(),
                        p.getNationality(),
                        p.getDateOfBirth(),
                        p.getHeight() + " m",
                        p.getSector(),
                        p.getWorldRank(),
                        r.getBrand() + " - " + r.getType()
                        );

        }
    }

    private static void priorityQueueSequenceSimulation(Queue<Player> playerPriorityQueue) {
        Player p1 = new Player("Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1);
        Player p5 = new Player("Carolina Marin", "Spain", 1993, 6, 15, 1.72, Sector.WOMENS_SINGLE, 6);
        Player p6 = new Player("Chou Tien Chen", "Chinese Taipei", 1990, 1, 8, 1.8, Sector.MENS_SINGLES, 4);
        Player p7 = new Player("Anders Antonsen", "Denmark", 1997, 4, 27, 1.83, Sector.MENS_SINGLES, 3);
        Player p10 = new Player("Greysia Polii", "Indonesia", 1987, 8, 11, 1.6, Sector.WOMENS_DOUBLE, 6);

        System.out.println("\nPriority queue sequence simulation option chosen");

        // add 2 third-priority elements
        System.out.println("- Add 2 third-priority elements");
        playerPriorityQueue.add(p5);
        playerPriorityQueue.add(p10);

        // add 2 second-priority elements
        System.out.println("- Add 2 second-priority elements");
        playerPriorityQueue.add(p6);
        playerPriorityQueue.add(p7);

        // remove and display one element
        System.out.println("- Remove and display one element");
        System.out.println(playerPriorityQueue.remove());

        // add 1 top-priority element
        System.out.println("- Add 1 top-priority element");
        playerPriorityQueue.add(p1);

        // remove and display all elements in priority order
        System.out.println("- Remove and display all elements in priority order");
        display(playerPriorityQueue);

    }

    private static void display(Queue<Player> playerPriorityQueue) {
        Iterator<Player> iterator = playerPriorityQueue.iterator();

        while (iterator.hasNext()) {
            System.out.println(playerPriorityQueue.remove());
        }
    }


}
