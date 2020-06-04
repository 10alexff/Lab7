package com.company;

public class Performer implements Runnable
{
    private long threadIterations = 0;
    private long suitablePoints = 0;

    public Performer(long iterations)
    {
        this.threadIterations = iterations;
    }

    public final long GetThreadIteration()
    {
        return threadIterations;
    }

    public final long GetSuitablePoints()
    {
        return suitablePoints;
    }


    public void run()
    {

        int currentIterations=0;
        while (currentIterations < threadIterations)
        {
            double x = Math.random();
            double y = Math.random();
            double distance = Math.sqrt((x*x) + (y*y));
            if (distance <= 1)
                suitablePoints++;
            currentIterations++;
        }
    }
}
