package dkit.oop.DAOs;

import dkit.oop.DTOs.Player;
import dkit.oop.Exceptions.DAOException;

import java.util.List;

public interface PlayerDAOInterface
{
    public List<Player> findAllPlayers() throws DAOException;

    public Player findPlayerById(int id) throws DAOException;

    public void deleteById(int id) throws DAOException;

    public void addPlayer(Player player) throws  DAOException;

}
