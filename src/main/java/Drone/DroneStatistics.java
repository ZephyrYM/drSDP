package Drone;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DroneStatistics {
    private String timeStamp;
    private Pair<Integer, Integer> newPosition;
    private double km;
    //inquinamento
    private List<Double> polluting;
    private int remainingBattery;


    public DroneStatistics(){}
    public DroneStatistics(String ts, Pair<Integer, Integer> pos, double kilom, int battery){
        this.timeStamp= ts;
        this.newPosition = pos;
        this.km = kilom;
        this.remainingBattery = battery;
        polluting = new ArrayList<>();
    }

    public DroneStatistics(String ts, Pair<Integer, Integer> pos, double kilom, int battery, List<Double> pol){
        this.timeStamp= ts;
        this.newPosition = pos;
        this.km = kilom;
        this.remainingBattery = battery;
        polluting = pol;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public int getRemainingBattery() {
        return remainingBattery;
    }

    public void setRemainingBattery(int remainingBattery) {
        this.remainingBattery = remainingBattery;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setNewPosition(Pair<Integer, Integer> newPosition) {
        this.newPosition = newPosition;
    }

    public Pair<Integer, Integer> getNewPosition() {
        return newPosition;
    }

    public List<Double> getPolluting(){return polluting;}
    public void setPolluting(List<Double> pol){polluting=pol;}
}
