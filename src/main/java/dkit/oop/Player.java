package dkit.oop;

import java.time.LocalDate;

public class Player
{
    private String name;
    private String nationality;
    private LocalDate dateOfBirth;
    private double height;
    private Sector sector;
    private int worldRank;

    public Player()
    {
    }

    public Player(String name, String nationality, int year, int month, int date, double height, Sector sector, int worldRank)
    {
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = LocalDate.of(year, month, date);
        this.height = height;
        this.sector = sector;
        this.worldRank = worldRank;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(int year, int month, int date)
    {
        this.dateOfBirth = LocalDate.of(year, month, date);
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public Sector getSector()
    {
        return sector;
    }

    public void setSector(Sector sector)
    {
        this.sector = sector;
    }

    public int getWorldRank()
    {
        return worldRank;
    }

    public void setWorldRank(int worldRank)
    {
        this.worldRank = worldRank;
    }

    @Override
    public String toString()
    {
        return "Player{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", height=" + height +
                ", sector=" + sector +
                ", worldRank=" + worldRank +
                '}';
    }
}
