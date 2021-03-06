package dkit.oop.BusinessObjects;

import dkit.oop.Comparators.ComparatorPlayerNationality;
import dkit.oop.Comparators.ComparatorPlayerRank;
import dkit.oop.Comparators.ComparatorPlayerRankWithinNationality;
import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Racket;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;


import java.time.LocalDate;
import java.util.*;

/**
 * Luana Kimley
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("OOP Project - CA4");

        mainMenu();
    }
    
    public static void mainMenu()
    {
        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Part 1 Deliverable\n"
                + "2. Part 2 Deliverable\n"
                + "3. Exit\n"
                + "Enter Option [1,3]";

        final int PART_1 = 1;
        final int PART_2 = 2;
        final int EXIT = 3;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println(MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case PART_1:
                        System.out.println("\nPart 1 deliverable option chosen");
                        part1Menu();
                        break;
                    case PART_2:
                        System.out.println("\nPart 2 deliverable option chosen");
                        part2Menu();
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

    public static void part1Menu() {
        List<Player> playersList = new ArrayList<>();

        Map<Integer, Player> playerIdMap = new HashMap<>();

        Map<Player, Racket> playerRacketMap = new TreeMap<>(new ComparatorPlayerNationality());

        Queue<Player> playerPriorityQueue1 = new PriorityQueue<>(new ComparatorPlayerRank());

        Queue<Player> playerPriorityQueue2 = new PriorityQueue<>(new ComparatorPlayerRankWithinNationality());

        initialise(playersList, playerIdMap, playerRacketMap, playerPriorityQueue2);


        final String MENU_ITEMS = "\n*** PART 1 DELIVERABLE ***\n"
                + "1. Display all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Display players and racket in order of nationality\n"
                + "4. PriorityQueue Sequence Simulation\n"
                + "5. PriorityQueue Two-Field Comparison Demo (rank within nationality)\n"
                + "6. Back to main menu\n"
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
            System.out.println(MENU_ITEMS);
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
                        System.out.println("\nBack to main menu option chosen.");
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

    public static void part2Menu()
    {
        MySqlPlayerDAO IPlayerDAO = new MySqlPlayerDAO();

        final String MENU_ITEMS = "\n*** PART 2 DELIVERABLE ***\n"
                + "1. Find all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Delete by ID\n"
                + "4. Add player\n"
                + "5. Find players by nationality\n"
                + "6. Retrieve all players as JSON\n"
                + "7. Retrieve player by ID as JSON\n"
                + "8. Back to main menu\n"
                + "Enter Option [1,8]";

        final int FIND_ALL_PLAYERS = 1;
        final int RETRIEVE_PLAYER_BY_ID = 2;
        final int DELETE_BY_ID = 3;
        final int ADD_PLAYER = 4;
        final int FIND_PLAYERS_BY_NATIONALITY = 5;
        final int RETRIEVE_ALL_PLAYERS_JSON = 6;
        final int RETRIEVE_PLAYER_BY_ID_JSON = 7;
        final int EXIT = 8;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println(MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case FIND_ALL_PLAYERS:
                        System.out.println("\nFind all players option chosen");
                        findAllPlayersOption(IPlayerDAO);
                        break;
                    case RETRIEVE_PLAYER_BY_ID:
                        System.out.println("\nRetrieve player by ID option chosen");
                        retrievePlayerByIdOption(IPlayerDAO);
                        break;
                    case DELETE_BY_ID:
                        System.out.println("\nDelete by ID option chosen");
                        deleteByIdOption(IPlayerDAO);
                        break;
                    case ADD_PLAYER:
                        System.out.println("\nAdd player option chosen");
                        addPlayerOption(IPlayerDAO);
                        break;
                    case FIND_PLAYERS_BY_NATIONALITY:
                        System.out.println("\nFind players by nationality option chosen");
                        findPlayersByNationalityOption(IPlayerDAO);
                        break;
                    case RETRIEVE_ALL_PLAYERS_JSON:
                        System.out.println("\nRetrieve all players ad JSON option chosen");
                        retrieveAllPlayersJsonOption(IPlayerDAO);
                        break;
                    case RETRIEVE_PLAYER_BY_ID_JSON:
                        System.out.println("Retrieve player by ID as JSON option chosen");
                        retrievePlayerByIdJsonOption(IPlayerDAO);
                        break;
                    case EXIT:
                        System.out.println("\nBack to main menu option chosen.");
                        break;
                    default:
                        System.out.print("\nInvalid input - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.print("\nInvalid input - please enter valid input");
            }
        } while (option != EXIT);

    }

    private static void initialise(List<Player> list, Map<Integer, Player> playerIdMap, Map<Player, Racket> playerRacketMap, Queue<Player> playerPriorityQueue)
    {
        Player p1 = new Player(1,"Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1);
        Player p2 = new Player(2,"Viktor Axelsen", "Denmark", 1994, 1, 4, 1.94, Sector.MENS_SINGLES, 1);
        Player p3 = new Player(3,"Tai Tzu Ying", "Chinese Taipei", 1994, 6, 20, 1.63, Sector.WOMENS_SINGLE, 1);
        Player p4 = new Player(4,"Praveen Jordan", "Indonesia", 1993, 4, 26, 1.63, Sector.MIXED_DOUBLES, 5);
        Player p5 = new Player(5,"Carolina Marin", "Spain", 1993, 6, 15, 1.72, Sector.WOMENS_SINGLE, 6);
        Player p6 = new Player(6,"Chou Tien Chen", "Chinese Taipei", 1990, 1, 8, 1.8, Sector.MENS_SINGLES, 4);
        Player p7 = new Player(7,"Anders Antonsen", "Denmark", 1997, 4, 27, 1.83, Sector.MENS_SINGLES, 3);
        Player p8 = new Player(8,"Chen Qing Chen", "China", 1997, 6, 23, 1.64, Sector.WOMENS_DOUBLE, 1);
        Player p9 = new Player(9,"Lee Zii Jia", "Malaysia", 1998, 3, 29, 1.86, Sector.MENS_SINGLES, 7);
        Player p10 = new Player(10,"Greysia Polii", "Indonesia", 1987, 8, 11, 1.6, Sector.WOMENS_DOUBLE, 6);

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
        Player p1 = new Player(1,"Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1);
        Player p5 = new Player(2,"Carolina Marin", "Spain", 1993, 6, 15, 1.72, Sector.WOMENS_SINGLE, 6);
        Player p6 = new Player(3,"Chou Tien Chen", "Chinese Taipei", 1990, 1, 8, 1.8, Sector.MENS_SINGLES, 4);
        Player p7 = new Player(4,"Anders Antonsen", "Denmark", 1997, 4, 27, 1.83, Sector.MENS_SINGLES, 3);
        Player p10 = new Player(5,"Greysia Polii", "Indonesia", 1987, 8, 11, 1.6, Sector.WOMENS_DOUBLE, 6);

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

    private static void findAllPlayersOption(MySqlPlayerDAO IPlayerDAO)
    {
        try
        {
            List<Player> playersList = IPlayerDAO.findAllPlayers();

            System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n", "ID", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
            System.out.println("====   ====================================    ==========================    ==============    ==========    ================    ============");
            for(Player p : playersList)
            {
                System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n",
                        p.getId(),
                        p.getName(),
                        p.getNationality(),
                        p.getDateOfBirth(),
                        p.getHeight() + " m",
                        p.getSector(),
                        p.getWorldRank());
            }

        }
        catch( DAOException e )
        {
            e.printStackTrace();
        }
    }

    private static void retrievePlayerByIdOption(MySqlPlayerDAO IPlayerDAO) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player ID: ");
        int id = keyboard.nextInt();

        try
        {

            Player player = IPlayerDAO.findPlayerById(id);
            if (player != null)
            {
                System.out.println("Player found:");
                System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n", "ID", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
                System.out.println("====   ====================================    ==========================    ==============    ==========    ================    ============");
                System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n",
                        player.getId(),
                        player.getName(),
                        player.getNationality(),
                        player.getDateOfBirth(),
                        player.getHeight() + " m",
                        player.getSector(),
                        player.getWorldRank());
            }
            else
                System.out.println("Player with id " + id + " not found");
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    private static void deleteByIdOption(MySqlPlayerDAO IPlayerDAO)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player ID: ");
        int id = keyboard.nextInt();
        try
        {
            Player found = IPlayerDAO.findPlayerById(id);

            if(found != null) {
                IPlayerDAO.deletePlayerById(id);
                System.out.println("Player with id " + id + " deleted");
            }
            else {
                System.out.println("Player with id " + id + " not found");
            }
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    private static void addPlayerOption(MySqlPlayerDAO IPlayerDAO)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player name:");
        String name = keyboard.nextLine();

        while (!name.matches("^[A-Za-z' ]+$")) {
            System.out.println("Invalid name");
            System.out.println("Enter player name:");
            name = keyboard.nextLine();
        }

        System.out.println("Enter player nationality:");
        String nationality = keyboard.nextLine();

        while (!nationality.matches("^[A-Za-z' ]+$")) {
            System.out.println("Invalid nationality");
            System.out.println("Enter player nationality:");
            nationality = keyboard.nextLine();
        }

        System.out.println("Enter player date of birth");
        int date = keyboard.nextInt();
        while (date > 31 || date < 1)
        {
            System.out.println("Invalid date");
            System.out.println("Enter player date of birth");
            date = keyboard.nextInt();
        }

        System.out.println("Enter player month of birth (1-12)");
        int month = keyboard.nextInt();
        while (month > 12 || month < 1)
        {
            System.out.println("Invalid month");
            System.out.println("Enter player month of birth (1-12)");
            month = keyboard.nextInt();
        }

        System.out.println("Enter player year of birth (format: YYYY)");
        int year = keyboard.nextInt();
        while (year > LocalDate.now().getYear() || year < 1940)
        {
            System.out.println("Invalid year");
            System.out.println("Enter player year of birth (format: YYYY)");
            year = keyboard.nextInt();
        }

        System.out.println("Enter player height (in metres)");
        double height = keyboard.nextDouble();
        while (height < 1 || height > 2.5)
        {
            System.out.println("Invalid height");
            System.out.println("Enter player height (in metres)");
            height = keyboard.nextDouble();
        }

        System.out.println("Enter player sector (MS/MD/WS/WD/XD)");
        String sector = keyboard.next();
        while(!sector.toLowerCase().matches("^(ms|md|ws|wd|xd)$"))
        {
            System.out.println("Invalid sector");
            System.out.println("Enter player sector (MS/MD/WS/WD/XD)");
            sector = keyboard.nextLine();
        }

        Sector playerSector = null;
        switch (sector.toUpperCase()) {
            case "MS":
                playerSector = Sector.MENS_SINGLES;
                break;
            case "MD":
                playerSector = Sector.MENS_DOUBLE;
                break;
            case "WS":
                playerSector = Sector.WOMENS_SINGLE;
                break;
            case "WD":
                playerSector = Sector.WOMENS_DOUBLE;
                break;
            case "XD":
                playerSector = Sector.MIXED_DOUBLES;
                break;
        }

        System.out.println("Enter player world rank");
        int worldRank = keyboard.nextInt();
        while (worldRank < 1) {
            System.out.println("Invalid world rank");
            System.out.println("Enter player world rank");
            worldRank = keyboard.nextInt();
        }

        try
        {
            Player p = new Player(name, nationality, year, month, date, height, playerSector, worldRank);

            IPlayerDAO.addPlayer(p);

            System.out.println("\nPlayer added to database");
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    private static void findPlayersByNationalityOption(MySqlPlayerDAO IPlayerDAO)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player nationality:");
        String nationality = keyboard.nextLine();

        while (!nationality.matches("^[A-Za-z' ]+$")) {
            System.out.println("Invalid nationality");
            System.out.println("Enter player nationality:");
            nationality = keyboard.nextLine();
        }

        try
        {
            List<Player> players = IPlayerDAO.findPlayersByNationality(nationality);
            if (players != null)
            {
                System.out.println("Player(s) with nationality " + nationality + " found (sorted by world rank)");
                System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n", "ID", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
                System.out.println("====   ====================================    ==========================    ==============    ==========    ================    ============");
                for(Player player : players)
                {
                    System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n",
                            player.getId(),
                            player.getName(),
                            player.getNationality(),
                            player.getDateOfBirth(),
                            player.getHeight() + " m",
                            player.getSector(),
                            player.getWorldRank());
                }
            }
            else
                System.out.println("Player with nationality " + nationality + " not found");
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    private static void retrieveAllPlayersJsonOption(MySqlPlayerDAO IPlayerDAO)
    {
        try
        {
            System.out.println("Displaying all players as JSON string:");
            System.out.println(IPlayerDAO.findAllPlayersJson());

        }
        catch( DAOException e )
        {
            e.printStackTrace();
        }
    }

    private static void retrievePlayerByIdJsonOption(MySqlPlayerDAO IPlayerDAO)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player ID: ");
        int id = keyboard.nextInt();

        try
        {

            Player player = IPlayerDAO.findPlayerById(id);
            if (player != null)
            {
                System.out.println("Player found, displaying player as JSON string:");
                System.out.println(IPlayerDAO.findPlayerByIdJson(id));
            }
            else
                System.out.println("Player with id " + id + " not found");
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }
}

