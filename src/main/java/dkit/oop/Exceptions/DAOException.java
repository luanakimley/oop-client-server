package dkit.oop.Exceptions;
import java.sql.SQLException;

public class DAOException extends SQLException
{
    public DAOException()
    {
        // not used
    }

    public DAOException(String aMessage)
    {
        super(aMessage);
    }
}
