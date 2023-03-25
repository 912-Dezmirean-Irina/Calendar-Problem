package Model;

public class Interval {
    private Time lowerBound;
    private Time upperBound;

    public Interval(Time t1, Time t2) {
        lowerBound = t1;
        upperBound = t2;
    }

    public Time getLowerBound() {
        return lowerBound;
    }

    public Time getUpperBound() {
        return upperBound;
    }

    public boolean overlap(Interval other)
    {
        if(this.lowerBound.greaterThanOrEqual(other.lowerBound) && this.upperBound.smallerThanOrEqual(other.upperBound))
            return true;
        if(this.lowerBound.smallerThanOrEqual(other.lowerBound) && this.upperBound.smallerThanOrEqual(other.upperBound))
            return true;
        if(this.lowerBound.greaterThanOrEqual(other.lowerBound) && this.upperBound.greaterThanOrEqual(other.upperBound))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "[" + lowerBound.toString() + "," + upperBound.toString() + "]";
    }
}
