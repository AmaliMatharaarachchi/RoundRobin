/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RR.controller;

import RR.UI.Launcher;
import RR.UI.Simulator;
import java.util.ArrayList;
import roundrobin.RProcess;
import roundrobin.simulation;

/**
 *
 * @author ASUS
 */
public class Controller {
    public static Simulator simulator;
   
    

    public static void setProcessList(ArrayList<RProcess> processList, int tottime, int q)  {
        simulation sim= new simulation();
        sim.setProcessList(processList,tottime,q);
        simulator= new Simulator();
        simulator.setVisible(true);
        Thread t= new Thread(sim);
        sim.start();
       
        
    }
    public static void addtoReadyQueue(RProcess process){
        simulator.addtoReadyQueue(process.getProcessID(), process.getRemainingTime());
    }
    
    public static void removeFromReadyQ(){
        simulator.removeFromReadyQueue();
    }
    public static void addFinished(RProcess process){
        simulator.addFinishedProcess(process.getProcessID(), process.getFinishedtime(), process.getTurnAroundTime());
    }
    
    public static void setTime(int t){
        simulator.setTime(t);
    }
    
    public static void setGanttchart(String pid){
        simulator.setGanttChart(pid);
    }
    
    public static void launch() {
        Launcher launcher = new Launcher();
        launcher.setVisible(true);
    }
    
   
}
