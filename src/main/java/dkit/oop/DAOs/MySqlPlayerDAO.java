package dkit.oop.DAOs;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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
    public void deletePlayerById(int id) throws DAOException
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
    public int addPlayer(Player player) throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int playerId = 0;

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

            String selectQuery = "SELECT player_id FROM player ORDER BY player_id DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                playerId = resultSet.getInt("player_id");
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

        return playerId;
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

            String query = "SELECT * FROM player WHERE nationality = ? ORDER BY world_rank";
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

    @Override
    public String findAllPlayersJson() throws DAOException
    {
        Gson gsonParser =  Converters.registerLocalDate(new GsonBuilder()).create();

        return gsonParser.toJson(findAllPlayers());
    }

    @Override
    public String findPlayerByIdJson(int id) throws DAOException
    {
        Gson gsonParser =  Converters.registerLocalDate(new GsonBuilder()).create();

        return gsonParser.toJson(findPlayerById(id));
    }

    @Override
    public String getStatisticsJson() throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Map<String, Object> statistics = new LinkedHashMap<>();
        String statisticsJson = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();



            String[] columnLabels = {"Average height of MS players", "Average height of WS players", "Average height of MD players", "Average height of WD players", "Average height of XD players"};
            String[] sectors = {"MENS_SINGLES", "WOMENS_SINGLE", "MENS_DOUBLE","WOMENS_DOUBLE", "MIXED_DOUBLES"};

            for(int i=0; i<sectors.length; i++)
            {
                String averageHeightQuery = "SELECT AVG(height) ? FROM player WHERE sector= ? ";
                ps = connection.prepareStatement(averageHeightQuery);

                ps.setString(1, columnLabels[i]);
                ps.setString(2, sectors[i]);

                resultSet = ps.executeQuery();
                resultSet.next();

                double averageHeight = resultSet.getDouble(columnLabels[i]);

                statistics.put(columnLabels[i], averageHeight);
            }

            String query = "SELECT name, MAX(TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) 'Youngest player age' FROM player WHERE date_of_birth = (SELECT MAX(date_of_birth) from player)";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            int age = resultSet.getInt("Youngest player age");
            statistics.put("Youngest player name", name);
            statistics.put("Youngest player age", age);

            query = "SELECT name, MIN(TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) 'Oldest player age' FROM player WHERE date_of_birth = (SELECT MIN(date_of_birth) from player)";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
            age = resultSet.getInt("Oldest player age");
            statistics.put("Oldest player name", name);
            statistics.put("Oldest player age", age);

            query = "SELECT AVG(TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) 'Average age' FROM player";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            resultSet.next();
            double avgAge = resultSet.getDouble("Average age");
            statistics.put("Average age of players", avgAge);

            query = "SELECT nationality, COUNT(*) AS 'count' FROM player GROUP BY nationality";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while(resultSet.next())
            {
                String key = "Count of players from " + resultSet.getString("nationality");
                statistics.put(key, resultSet.getInt("count"));
            }

            query = "SELECT COUNT(*) AS 'total' FROM player";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            resultSet.next();
            int total = resultSet.getInt("total");
            statistics.put("Total number of players", total);

            Gson gsonParser = new Gson();

            statisticsJson = gsonParser.toJson(statistics);
            
        } catch (SQLException e)
        {
            throw new DAOException("getStatisticsJson() " + e.getMessage());
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
                throw new DAOException("getStatisticsJson() " + e.getMessage());
            }
        }

        return statisticsJson;
    }


}
