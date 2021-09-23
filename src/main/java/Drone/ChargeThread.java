package Drone;

import ServerAdmin.DroneInfo;
import javafx.util.Pair;

import java.util.*;

public class ChargeThread extends Thread{
    private DroneData drone;
    private Map<Integer,Boolean> confirmDictionary;
    private int numberOK=0;

    public ChargeThread(DroneData d){
        drone = d;
        confirmDictionary= new HashMap<>();
    }

    public void addAnswer(int id){
        synchronized (confirmDictionary){
            confirmDictionary.put(id, true);
            numberOK++;
        }
    }

    public boolean check(){
        synchronized (confirmDictionary){
            return numberOK<confirmDictionary.size();
        }
    }

    @Override
    public void run() {
        if(drone.exitNetworkThread!= null ) return;
        List<DroneInfo> list = drone.GetNetworkDrones();
        List<DroneCommunicationThread> threadList =new ArrayList<>();

        for (DroneInfo d: list) {
            confirmDictionary.put(d.getId(), false);
            DroneCommunicationThread t = new DroneCommunicationThread(drone,d,drone.getMyChargeRequest() );
            threadList.add(t);
            t.start();
        }

        try {
            for (DroneCommunicationThread comT : threadList
            ) {
                comT.join();
            }
            while(check()){
                synchronized (drone.lockChargeThread){

                    drone.lockChargeThread.wait();
                }
            }
            if(drone.isDelivering())
                drone.getDeliveryThread().join();

            drone.setRecharging(true);

            new DroneCommunicationThread(drone, true).start();
            Thread.sleep(10000);
        }catch (InterruptedException e){e.printStackTrace();}

        drone.setPosition(new Pair<>(0,0));
        drone.updateBattery(100);

        drone.deleteMyChargeRequest();
        drone.setRecharging(false);
        for (ChargeRequest r: drone.getRequestsChargeReceived()
             ) {
            DroneInfo i = drone.findDrone(r.getId());
            if(i==null) continue;
            new DroneCommunicationThread(drone, i, false).start();
        }
        if(!drone.isMaster() && drone.getMaster()!= null)
            new DroneCommunicationThread(drone, drone.getMaster(), false).start();
        drone.emptyRequestChargeReceived();
        drone.endedChargeThread();

    }
}
