package dkit.oop;

import java.util.Comparator;

public class ComparatorPlayerRankWithinNationality implements Comparator<Player>
{
    @Override
    public int compare(Player p1, Player p2)
    {
        boolean sameNationality = p1.getNationality().equalsIgnoreCase(p2.getNationality());

        if(sameNationality) {
            return p1.getWorldRank() - p2.getWorldRank();
        }
        else {
            return p1.getNationality().compareTo(p2.getNationality());
        }


    }
}
