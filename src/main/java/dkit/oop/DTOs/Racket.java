package dkit.oop.DTOs;

import java.util.Objects;

public class Racket
{
    private String brand;
    private String type;

    public Racket(String brand, String type)
    {
        this.brand = brand;
        this.type = type;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racket racket = (Racket) o;
        return brand.equals(racket.brand) && Objects.equals(type, racket.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(brand, type);
    }

    @Override
    public String toString()
    {
        return "Racket{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
