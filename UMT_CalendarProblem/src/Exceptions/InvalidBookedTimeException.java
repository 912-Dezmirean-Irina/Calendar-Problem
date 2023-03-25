package Exceptions;
import Model.Interval;

public class InvalidBookedTimeException extends Exception
{
    public InvalidBookedTimeException (Interval timeInterval)
    {
        super("Time interval "+timeInterval.toString()+" exceeds schedule limits.");
    }
}
