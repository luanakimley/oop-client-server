package dkit.oop.DTOs;

import java.time.LocalDate;
import java.util.Objects;

public class Player
{
    private int id;
    private String name;
    private String nationality;
    private LocalDate dateOfBirth;
    private double height;
    private Sector sector;
    private int worldRank;

    public Player(int id, String name, String nationality, int year, int month, int date, double height, Sector sector, int worldRank)
    {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = LocalDate.of(year, month, date);
        this.height = height;
        this.sector = sector;
        this.worldRank = worldRank;
    }

    public Player(String name, String nationality, int year, int month, int date, double height, Sector sector, int worldRank)
    {
        this.id = 0;
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = LocalDate.of(year, month, date);
        this.height = height;
        this.sector = sector;
        this.worldRank = worldRank;
    }

    public Player(int id, String name, String nationality, LocalDate date, double height, Sector sector, int worldRank)
    {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = date;
        this.height = height;
        this.sector = sector;
        this.worldRank = worldRank;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return worldRank == player.worldRank && name.equals(player.name) && Objects.equals(nationality, player.nationality) && Objects.equals(dateOfBirth, player.dateOfBirth);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, nationality, dateOfBirth, worldRank);
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
