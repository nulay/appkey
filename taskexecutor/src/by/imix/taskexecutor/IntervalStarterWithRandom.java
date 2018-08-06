package by.imix.taskexecutor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Class starting task with interval
 */
public class IntervalStarterWithRandom {
    private static final Logger _log= LoggerFactory.getLogger(IntervalStarterWithRandom.class);
    private Task task;
    private int minTime;
    private int maxRandomTime;

    /**
     *
     * @param task
     * @param minTime min time in sec minimum = 1
     * @param maxRandomTime max time in min minimum = 1
     */
    public IntervalStarterWithRandom(Task task, int minTime, int maxRandomTime) {
        this.task = task;
        this.minTime = minTime;
        if(this.minTime<1){
            this.minTime = 1;
        }
        this.maxRandomTime=maxRandomTime;
        if(this.maxRandomTime<1){
            this.maxRandomTime = 1;
        }
    }

    /**
     * Execute task by Scheduler
     */
    public void execute(){
        Thread thLike = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    _log.debug("execute IntervalStarterWithRandom");
                    int time = minTime * new Random().nextInt(maxRandomTime);
                    _log.debug("next task will start in " + time + " sec");
                    sleepOn(time*1000);
                    _log.debug("execute task");
                    task.execute();
                }
            }
        });
        thLike.start();
    }

    /**
     * Sleep on millisec time
     * @param millisecSleep sec for sleep
     */
    public static void sleepOn(int millisecSleep) {
        try {
            Thread.sleep(millisecSleep);
        } catch (InterruptedException e1) {
        }
    }
}
