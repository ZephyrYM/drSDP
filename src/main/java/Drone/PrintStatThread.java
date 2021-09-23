package Drone;

public class PrintStatThread extends Thread{
    private DroneData droneData;
    private boolean close= false;

    public PrintStatThread(DroneData drone){
        droneData = drone;
    }

    @Override
    public void run() {
        while(!close){
            try{
                Thread.sleep(10000);
            }
            catch (InterruptedException e){e.printStackTrace();}
            //print data
            droneData.printStats();
        }
    }

    public void close(){
        close= true;
    }
}
