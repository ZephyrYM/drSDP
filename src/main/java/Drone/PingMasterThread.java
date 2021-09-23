package Drone;

import Util.Constants;

public class PingMasterThread  extends Thread{
    private DroneData drone;
    private boolean close=false;

    public PingMasterThread(DroneData drone){
        this.drone=drone;
    }
    @Override
    public void run() {
        while(!close){
            try {
                Thread.sleep(Constants.PING_TIME_TO_SLEEP);
                System.out.println("PING");
                System.out.println(!drone.isMaster+ " "+ (drone.getMaster()==null)+" "+ !drone.election+ " "+!drone.participant);
                if(!drone.isMaster && drone.getMaster()!=null && !drone.election && !drone.participant){
                    System.out.println("pinging");
                    new DroneCommunicationThread(drone).start();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        System.out.println("Close Ping thread");
    }

    public void close(){
        close=true;
    }
}
