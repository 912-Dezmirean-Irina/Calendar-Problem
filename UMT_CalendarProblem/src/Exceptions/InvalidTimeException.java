package Exceptions;

public class InvalidTimeException extends Exception
{
    public InvalidTimeException(int h, int m)
    {
        super("Invalid input time "+h+":"+m);
    }
}
