/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobin;

import RR.controller.Controller;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class simulation extends Thread {

    private Queue<RProcess> readyQueue = new LinkedList<>();
    private Queue<RProcess> finishedQueue = new LinkedList<>();
    private ArrayList<RProcess> processList;
    private int t = 0;
    private int q;
    private int currentTime = -1;
    private RProcess toBeAdded = null;
    private RProcess executingProcess;
    private int totTime;
    private boolean skipTime = true;

    public void setProcessList(ArrayList<RProcess> processList, int time, int q) {
        this.processList = processList;
        this.q = q;
        totTime = time;
    }

    public void run() {
        while (skipTime) {
            currentTime+=1;
            fetchArrived();
            
        }
        while (t < totTime) {

//            try {
//                sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(simulation.class.getName()).log(Level.SEVERE, null, ex);
//            }
           
            if (!(toBeAdded == null)) {
                readyQueue.add(toBeAdded);
               
                Controller.addtoReadyQueue(toBeAdded);
            }

            execute();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
                                 
        }

    }

    public void fetchArrived() {
        
        for (RProcess process : processList) {
            if (process.getArrivalTime() == currentTime) {
                skipTime = false;
                readyQueue.add(process);
               
                Controller.addtoReadyQueue(process);
            }
        }
        
    }

    public void execute() {
        while(readyQueue.size()<1){
            fetchArrived();
            currentTime+=1;
        }
        executingProcess = readyQueue.poll();
        Controller.removeFromReadyQ();
        int remainingTime = executingProcess.getRemainingTime();
        if (remainingTime <= q) {
            for (int i = 1; i <= remainingTime; i++) {
                t += 1;
                currentTime += 1;
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                Controller.setTime(currentTime);
                Controller.setGanttchart(executingProcess.getProcessID());
                executingProcess.reduceRemainingTimeBy(1);
                fetchArrived();
            }
            toBeAdded = null;
            executingProcess.setFinishedtime(currentTime);
            finishedQueue.add(executingProcess);
            Controller.addFinished(executingProcess);
        } else {
            for (int i = 1; i <= q; i++) {
                t += 1;
                currentTime += 1;
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                Controller.setTime(currentTime);
                Controller.setGanttchart(executingProcess.getProcessID());
                executingProcess.reduceRemainingTimeBy(1);
                fetchArrived();
            }
            toBeAdded = executingProcess;
        }

    }

}
