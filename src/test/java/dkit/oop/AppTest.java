package dkit.oop;

import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    MySqlPlayerDAO IPlayerDAO = new MySqlPlayerDAO();

    @Test
    public void findPlayerByIdTest()
    {
        try
        {
            System.out.println("Find player by ID DAO test");

            Player p = IPlayerDAO.findPlayerById(2);

            assertEquals(p.getId(), 2);
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void addPlayerTest()
    {
        try
        {
            System.out.println("Add player DAO test");

            int prevSize = IPlayerDAO.findAllPlayers().size();

            Player p = new Player("Luana Kimley", "Indonesia", 2002, 8, 27, 1.62, Sector.MIXED_DOUBLES, 7);
            IPlayerDAO.addPlayer(p);

            Player newP = IPlayerDAO.findAllPlayers().get(prevSize);

            assertEquals(prevSize + 1, IPlayerDAO.findAllPlayers().size());
            assertEquals("Luana Kimley", newP.getName());
            assertEquals("Indonesia", newP.getNationality());
            assertEquals(LocalDate.of(2002,8,27), newP.getDateOfBirth());
            assertEquals(1.62, newP.getHeight(), 0.01);
            assertEquals(Sector.MIXED_DOUBLES, newP.getSector());
            assertEquals(7, newP.getWorldRank());
        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePlayerByIdTest()
    {
        try
        {
            System.out.println("Delete player by ID DAO test");

            int prevSize = IPlayerDAO.findAllPlayers().size();

            IPlayerDAO.deletePlayerById(4);

            Player p = IPlayerDAO.findPlayerById(4);

            assertNull(p);
            assertEquals(prevSize-1, IPlayerDAO.findAllPlayers().size());


        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void findPlayerByNationalityTest()
    {
        try
        {
            System.out.println("Find player by nationality DAO test");

            List<Player> playerList = IPlayerDAO.findPlayersByNationality("Denmark");

            for (Player p : playerList)
            {
                assertEquals("Denmark", p.getNationality());
            }

        }
        catch (DAOException e)
        {
            e.printStackTrace();
        }
    }
}
