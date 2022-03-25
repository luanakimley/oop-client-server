package dkit.oop.BusinessObjects;

import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.DTOs.Player;
import dkit.oop.Exceptions.DAOException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppV2
{
    public static void main( String[] args )
    {
        System.out.println("Project part 2 - CA5");
        menu();

    }

    public static void menu() {
        MySqlPlayerDAO IPlayerDAO = new MySqlPlayerDAO();

        final String MENU_ITEMS = "*** MAIN MENU OF OPTIONS ***\n"
                + "1. Find all players\n"
                + "2. Retrieve player by ID\n"
                + "3. Exit\n"
                + "Enter Option [1,3]";

        final int FIND_ALL_PLAYERS = 1;
        final int RETRIEVE_PLAYER_BY_ID = 2;
        final int EXIT = 3;

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
}
