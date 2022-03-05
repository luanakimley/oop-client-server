package dkit.oop;

import java.util.Comparator;

public class ComparatorPlayerNationality implements Comparator<Player>
{
    @Override
    public int compare(Player p1, Player p2)
    {
        return p1.getNationality().compareTo(p2.getNationality());
    }
}
