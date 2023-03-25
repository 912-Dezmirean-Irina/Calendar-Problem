package Model;

import Exceptions.InvalidTimeException;

public class Time
{
    private int hours;
    private int minutes;

    public void validate(int h, int m) throws InvalidTimeException {
        if(!(h>=0 && h <=23 && m>=0 && m<=59))
            throw new InvalidTimeException(h,m);

    }

    public Time(int h, int m) throws InvalidTimeException {
        validate(h,m);
        this.hours = h;
        this.minutes = m;
    }

    public int getHours()
    {
        return this.hours;
    }

    public int getMinutes()
    {
        return this.minutes;
    }

    public void setHours(int h)
    {
        this.hours = h;
    }

    public void setMinutes(int m)
    {
        this.minutes = m;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof Time))
            return false;
        Time t = (Time) o;
        return t.hours == this.hours && t.minutes == this.minutes;
    }

    public boolean greaterThanOrEqual(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof Time))
            return false;
        Time t = (Time) o;
        if(this.hours>t.hours)
            return true;
        if(this.hours==t.hours && this.minutes>=t.minutes)
            return true;
        return false;

    }

    public boolean smallerThanOrEqual(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof Time))
            return false;
        Time t = (Time) o;
        if(this.hours<t.hours)
            return true;
        if(this.hours==t.hours && this.minutes<=t.minutes)
            return true;
        return false;
    }

    public boolean greaterThan(Object o)
    {
        if(o == this)
            return false;
        if(!(o instanceof Time))
            return false;
        Time t = (Time) o;
        if(this.hours>t.hours)
            return true;
        if(this.hours==t.hours && this.minutes>t.minutes)
            return true;
        return false;

    }

    public boolean smallerThan(Object o)
    {
        if(o == this)
            return false;
        if(!(o instanceof Time))
            return false;
        Time t = (Time) o;
        if(this.hours<t.hours)
            return true;
        if(this.hours==t.hours && this.minutes<t.minutes)
            return true;
        return false;
    }

    public int minus(Time other)
    {
        int min;
        min = (this.hours - other.hours)*60;
        min = min + (this.minutes - other.minutes);
        return min;
    }


    @Override
    public String toString()
    {
        if(this.minutes<10)
        {
            String min = "0"+this.minutes;
            return String.format("%d:%s",this.hours,min);
        }
        return String.format("%d:%d",this.hours,this.minutes);
    }
}
