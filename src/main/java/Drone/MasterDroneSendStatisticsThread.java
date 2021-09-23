package Drone;

import ServerAdmin.GlobalStat;
import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

public class MasterDroneSendStatisticsThread extends Thread{
    private DroneData drone;
    private boolean close = false;


    public MasterDroneSendStatisticsThread(DroneData d){
        drone=d;
    }

    @Override
    public void run() {
        while(!close){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            GlobalStat stat = drone.getGlobalStat();

            ClientResponse r = DroneMain.SendStats(drone.client, stat);
            System.out.println(r.toString());

        }
    }

    public void close(){
        close=true;
    }
}
