package dkit.oop.BusinessObjects;

import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppV2
{
    public static void main( String[] args ) throws DAOException
    {
        System.out.println("Project part 2 - CA5");



        menu();

    }

    public static void menu() 
    {
        MySqlPlayerDAO IPlayerDAO = new MySqlPlayerDAO();

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Find all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Delete by ID\n"
                + "4. Add player\n"
                + "5. Exit\n"
                + "Enter Option [1,5]";

        final int FIND_ALL_PLAYERS = 1;
        final int RETRIEVE_PLAYER_BY_ID = 2;
        final int DELETE_BY_ID = 3;
        final int ADD_PLAYER = 4;
        final int EXIT = 5;

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
                        System.out.println("Add player option chosen");
                        addPlayerOption(IPlayerDAO);
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
                IPlayerDAO.deleteById(id);
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
}
