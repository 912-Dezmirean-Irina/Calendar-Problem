package Repo;

import Exceptions.InvalidBookedTimeException;
import Model.Interval;
import Model.Time;

import java.util.ArrayList;
import java.util.List;

public class Calendar
{
    private Time minRange;
    private Time maxRange;

    private List<Interval> bookedCalendar = new ArrayList<>();

    public Calendar(Time min, Time max)
    {
        this.minRange = min;
        this.maxRange = max;
    }

    public Time getMinRange()
    {
        return  this.minRange;
    }

    public Time getMaxRange()
    {
        return this.maxRange;
    }

    public void setMinRange(Time min)
    {
        this.minRange = min;
    }

    public void setMaxRange(Time max)
    {
        this.maxRange = max;
    }

    public List<Interval> getCalendarIntervals()
    {
        return this.bookedCalendar;
    }

    public void setBookedCalendar(List<Interval> newBookedCalendar)
    {
        this.bookedCalendar = newBookedCalendar;
    }

    public void addTimeInterval(Time t1, Time t2) throws InvalidBookedTimeException{
        Interval timeInterval = new Interval(t1,t2);
        if(timeInterval.getLowerBound().smallerThan(minRange) || timeInterval.getUpperBound().greaterThan(maxRange))
            throw new InvalidBookedTimeException(timeInterval);
        this.bookedCalendar.add(timeInterval);
    }

    @Override
    public String toString()
    {
        return String.format("Schedule limits: "+this.minRange.toString()+" to "+this.maxRange.toString()+"\n"
                                + "Booked time: "+this.bookedCalendar.toString());
    }


}
