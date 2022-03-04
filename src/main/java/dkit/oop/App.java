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
        initialise(playersList);
        displayPlayerList(playersList);
    }

    private static void initialise(List list)
    {
        list.add(new Player("Kevin Sanjaya Sukamuljo", "Indonesia", 1995, 8, 2, 1.7, Sector.MENS_DOUBLE, 1));
        list.add(new Player("Viktor Axelsen", "Denmark", 1994, 1, 4, 1.94, Sector.MENS_SINGLES, 1));
        list.add(new Player("Tai Tzu Ying", "Chinese Taipei", 1994, 6, 20, 1.63, Sector.WOMENS_SINGLE, 1));
        list.add(new Player("Praveen Jordan", "Indonesia", 1993, 4, 26, 1.63, Sector.WOMENS_SINGLE, 1));
        list.add(new Player("Carolina Marin", "Spain", 1993, 6, 15, 1.72, Sector.WOMENS_SINGLE, 6));
        list.add(new Player("Chou Tien Chen", "Chinese Taipei", 1990, 1, 8, 1.8, Sector.MENS_SINGLES, 4));
        list.add(new Player("Anders Antonsen", "Denmark", 1997, 4, 27, 1.83, Sector.MENS_SINGLES, 3));
        list.add(new Player("Chen Qing Chen", "China", 1997, 6, 23, 1.64, Sector.WOMENS_DOUBLE, 1));
        list.add(new Player("Lee Zii Jia", "Malaysia", 1998, 3, 29, 1.86, Sector.MENS_SINGLES, 7));
        list.add(new Player("Greysia Polii", "Indonesia", 1987, 8, 11, 1.6, Sector.WOMENS_DOUBLE, 6));

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
