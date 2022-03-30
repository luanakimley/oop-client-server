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
                throw new DAOException("findAllPlayers() " + e.getMessage());
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

    @Override
    public void deleteById(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM badminton_db.player WHERE player_id = ?";
            ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            //Using a PreparedStatement to
            // execute SQL...
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DAOException("deleteById() " + e.getMessage());
        } finally
        {
            try
            {
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
                throw new DAOException("deleteById() " + e.getMessage());
            }
        }
    }

    @Override
    public void addPlayer(Player player) throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "INSERT INTO badminton_db.player VALUES (null, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);

            ps.setString(1, player.getName());
            ps.setString(2, player.getNationality());
            ps.setString(3, player.getDateOfBirth().toString());
            ps.setDouble(4, player.getHeight());
            ps.setString(5, player.getSector().toString());
            ps.setInt(6, player.getWorldRank());

            //Using a PreparedStatement to
            // execute SQL...
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new DAOException("addPlayer() " + e.getMessage());
        } finally
        {
            try
            {
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
                throw new DAOException("addPlayer() " + e.getMessage());
            }
        }
    }

    @Override
    public List<Player> findPlayersByNationality(String nationality) throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM player WHERE nationality = ?";
            ps = connection.prepareStatement(query);

            ps.setString(1, nationality);


            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("player_id");
                String name = resultSet.getString("name");
                String playerNationality = resultSet.getString("nationality");
                LocalDate date_of_birth = LocalDate.parse(resultSet.getString("date_of_birth"));
                double height = resultSet.getDouble("height");
                Sector sector = Sector.valueOf(resultSet.getString("sector"));
                int worldRank = resultSet.getInt("world_rank");
                Player p = new Player(playerId, name, playerNationality, date_of_birth, height, sector, worldRank);
                playersList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findPlayersByNationality() " + e.getMessage());
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
                throw new DAOException("findPlayersByNationality() " + e.getMessage());
            }
        }
        return playersList;     // may be empty
    }


}
