package Controller;

import Exceptions.InvalidBookedTimeException;
import Model.Time;
import Repo.Calendar;
import Model.Interval;


public class Controller
{
    private Calendar calendar1;
    private Calendar calendar2;

    private int meetingTime;

    public Controller(Calendar c1, Calendar c2, int mT)
    {
        this.calendar1 = c1;
        this.calendar2 = c2;
        this.meetingTime = mT;
    }

    public Interval computeMeetingTimeLimits(Calendar calendar1,Calendar calendar2)  {

        Time min = max(calendar1.getMinRange(),calendar2.getMinRange());
        Time max = min(calendar1.getMaxRange(),calendar2.getMaxRange());
        return  new Interval(min,max);
    }

    public Calendar computeFreeTime(Calendar calendar) throws InvalidBookedTimeException {
        Calendar freeTime = new Calendar(calendar.getMinRange(),calendar.getMaxRange());
        Time previous = calendar.getMinRange();
        for(Interval i: calendar.getCalendarIntervals())
        {
            Time current = i.getLowerBound();
            if(previous.smallerThan(current))
                freeTime.addTimeInterval(previous,current);
            previous =i.getUpperBound();
        }
        if(previous.smallerThan(calendar.getMaxRange()))
            freeTime.addTimeInterval(previous,calendar.getMaxRange());
        return freeTime;
    }

    public Time max(Time t1, Time t2)
    {
        if(t1.greaterThanOrEqual(t2))
            return t1;
        else
            return t2;
    }

    public Time min(Time t1, Time t2)
    {
        if(t1.smallerThanOrEqual(t2))
            return t1;
        else
            return t2;
    }

    public Calendar findAvailableMeetingTimes() throws InvalidBookedTimeException{

        Interval limits = computeMeetingTimeLimits(calendar1,calendar2);

        Calendar availableTime = new Calendar(limits.getLowerBound(),limits.getUpperBound());

        Calendar freeTime1 = computeFreeTime(calendar1);
        Calendar freeTime2 = computeFreeTime(calendar2);

        for(Interval free1: freeTime1.getCalendarIntervals())
        {
            for(Interval free2: freeTime2.getCalendarIntervals())
            {
                if(free1.overlap(free2) || free2.overlap(free1))
                {
                    Time low = max(free1.getLowerBound(),free2.getLowerBound());
                    Time up = min(free1.getUpperBound(),free2.getUpperBound());
                    if (up.minus(low) >= meetingTime)
                        availableTime.addTimeInterval(low, up);
                }
            }
        }
        return availableTime;
    }
}
