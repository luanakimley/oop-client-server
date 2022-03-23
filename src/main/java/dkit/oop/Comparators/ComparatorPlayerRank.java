package dkit.oop.Comparators;

import dkit.oop.DTOs.Player;

import java.util.Comparator;

public class ComparatorPlayerRank implements Comparator<Player>
{
    @Override
    public int compare(Player p1, Player p2)
    {
        return p1.getWorldRank() - p2.getWorldRank();
    }
}
