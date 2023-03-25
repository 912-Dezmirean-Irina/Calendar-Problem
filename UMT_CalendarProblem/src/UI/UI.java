package UI;

import Controller.Controller;
import Exceptions.InvalidBookedTimeException;
import Exceptions.InvalidTimeException;
import Model.Time;
import Repo.Calendar;

import java.util.Scanner;

public class UI
{
    private Controller ctrl;

    public UI(){}

    public Time getMinRange()
    {

        int minH = -1,minM = -1;
        Time min = null;

        Scanner scanner = new Scanner(System.in);

        while(minH==-1 || minM == -1 || min == null)
        {

            System.out.println("1) The minimum time range: ");
            String minS = scanner.nextLine();

            try
            {
                minH = Integer.parseInt(minS.substring(0, 2));
                minM = Integer.parseInt(minS.substring(3, 5));

                try
                {
                    min = new Time(minH, minM);
                    return min;
                }catch (InvalidTimeException e)
                {
                    System.out.println("Invalid input ---> "+e.getMessage());
                }

            }catch (Exception e)
            {
                System.out.println("Invalid input ---> "+e.getMessage());
            }
        }
        return null;
    }

    public Time getMaxRange()
    {

        int maxH = -1,maxM = -1;
        Time max = null;

        Scanner scanner = new Scanner(System.in);

        while(maxH==-1 || maxM == -1 || max == null)
        {

            System.out.println("2) The maximum time range: ");
            String minS = scanner.nextLine();

            try
            {
                maxH = Integer.parseInt(minS.substring(0, 2));
                maxM = Integer.parseInt(minS.substring(3, 5));

                try
                {
                    max = new Time(maxH, maxM);
                    return max;
                }catch (InvalidTimeException e)
                {
                    System.out.println("Invalid input ---> "+e.getMessage());
                }

            }catch (Exception e)
            {
                System.out.println("Invalid input ---> "+e.getMessage());
            }
        }
        return null;
    }

    public Calendar getInput()
    {
        Scanner scanner = new Scanner(System.in);

        Time min = getMinRange();
        Time max = getMaxRange();

        Calendar calendar = new Repo.Calendar(min,max);

        System.out.println("Next provide the booked time intervals in the format: hh:mm,hh:mm. Type 'ok' when you are done.");
        while(true)
        {
            System.out.println("Booked interval: ");
            String interval = scanner.nextLine();
            if(interval.equals("ok"))
                return calendar;
            Time low,up;
            int H=0,M=0;
            try
            {
                H = Integer.parseInt(interval.substring(0,2));
                M = Integer.parseInt(interval.substring(3,5));
                low = new Time(H,M);
                H = Integer.parseInt(interval.substring(6,8));
                M = Integer.parseInt(interval.substring(9,11));
                up = new Time(H,M);
                try
                {
                    calendar.addTimeInterval(low,up);

                }catch (InvalidBookedTimeException t)
                {
                    System.out.println(t.getMessage());
                }
            }catch (Exception e) {
                System.out.println("Previous interval is not valid and it won't be added.");
            }

        }
    }

    public void start()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!\nPlease provide the required inputs in the following format: hh:mm (09:00).");
        System.out.println("For 1ST CALENDAR:");
        Calendar calendar1 = getInput();
        System.out.println("For 2ND CALENDAR:");
        Calendar calendar2 = getInput();
        System.out.println("The meeting time in minutes: ");
        String mT = scanner.nextLine();
        int meetingTime = Integer.parseInt(mT);

        this.ctrl = new Controller(calendar1,calendar2,meetingTime);

        try
        {
            Calendar free = ctrl.findAvailableMeetingTimes();
            if(free.getCalendarIntervals().size() == 0)
            {
                System.out.println("Sorry, no available time for meeting ... ");
            }
            else {
                System.out.println(free.getCalendarIntervals().toString());
            }
        }catch (Exception t)
        {
            System.out.println(t.getMessage());
        }
    }
}
