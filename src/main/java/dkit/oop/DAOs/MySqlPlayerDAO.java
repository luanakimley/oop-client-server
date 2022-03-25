package dkit.oop.DAOs;

import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerDAO extends MySqlDAO implements PlayerDAOInterface
{
    @Override
    public List<Player> findAllPlayers() throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM player";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("player_id");
                String name = resultSet.getString("name");
                String nationality = resultSet.getString("nationality");
                LocalDate date_of_birth = LocalDate.parse(resultSet.getString("date_of_birth"));
                double height = resultSet.getDouble("height");
                Sector sector = Sector.valueOf(resultSet.getString("sector"));
                int worldRank = resultSet.getInt("world_rank");
                Player p = new Player(playerId, name, nationality, date_of_birth, height, sector, worldRank);
                playersList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findAllPlayers() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DAOException("findAllUsers() " + e.getMessage());
            }
        }
        return playersList;     // may be empty
    }

    @Override
    public Player findPlayerById(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM player WHERE player_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int playerId = resultSet.getInt("player_id");
                String name = resultSet.getString("name");
                String nationality = resultSet.getString("nationality");
                LocalDate date_of_birth = LocalDate.parse(resultSet.getString("date_of_birth"));
                double height = resultSet.getDouble("height");
                Sector sector = Sector.valueOf(resultSet.getString("sector"));
                int worldRank = resultSet.getInt("world_rank");

                player = new Player(playerId, name, nationality, date_of_birth, height, sector, worldRank);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findPlayerById() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DAOException("findPlayerById() " + e.getMessage());
            }
        }
        return player;     // reference to Player object, or null value
    }
}
