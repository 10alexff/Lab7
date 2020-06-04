package com.company;

import java.util.ArrayList;
import java.util.List;

public class Simulation
{
    private long globalSuitablePoints = 0;
    private long globalIterations = 0;
    private int threadsNumber = 0;
    public Simulation (int threadsNumber, long globalIterationsNumber) {
        if (threadsNumber > 0)
            this.threadsNumber = threadsNumber;
        if (globalIterationsNumber > 0)
            this.globalIterations = globalIterationsNumber;
    }

    private List<Performer> performers = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();

    private long calculationTime = 0;
    public final double GetCalculationTime()
    {
        return calculationTime / Math.pow(10, 9);
    }
    public final long GetIterations() {
        return globalIterations;
    }
    public final long GetSuitablePoints() {
        return globalSuitablePoints;
    }

    public double CalculatePi() throws InterruptedException {
        long startTime = System.nanoTime();
        for (int i = 0; i < threadsNumber; i++)
        {
            Performer perf = new Performer(globalIterations / threadsNumber);
            Thread thread = new Thread(perf);
            thread.start();
            performers.add(perf);
            threads.add(thread);
        }

        for (Thread thread : threads)
        {
            if (thread.isAlive())
                thread.join();
        }
        globalIterations = 0;
        for (Performer perf : performers)
        {
            globalSuitablePoints += perf.GetSuitablePoints();
            globalIterations += perf.GetThreadIteration();
        }
        calculationTime = System.nanoTime() - startTime;
        return (globalSuitablePoints * 1.0 / globalIterations) *4;
    }

}
