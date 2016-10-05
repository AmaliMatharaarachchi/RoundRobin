/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobin;

/**
 *
 * @author ASUS
 */
public class RProcess {

    private String ProcessID;
    private int serviceTime;
    private int arrivalTime;
    private int remainingTime;    
    private int finishedtime;
    private double turnAroundTime;

    

    public RProcess(String processID, int arrivalTime, int serviceTime, int q) {
        this.ProcessID = processID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;

    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime() {
       this.turnAroundTime=finishedtime-arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void reduceRemainingTimeBy(int remainingTime) {
        this.remainingTime -= remainingTime;
    }

    public int getFinishedtime() {
        return finishedtime;
    }

    public void setFinishedtime(int finishedtime) {
        this.finishedtime = finishedtime;
        setTurnAroundTime();
    }

    public String getProcessID() {
        return ProcessID;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
