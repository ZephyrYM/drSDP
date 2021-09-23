package Drone;

import Dronazon.Order;
import ServerAdmin.DroneInfo;
import ServerAdmin.GlobalStat;
import Util.Constants;
import com.sun.jersey.api.client.ClientResponse;

public class ExitNetworkThread  extends Thread{

    private DroneData droneData;


    public ExitNetworkThread(DroneData d){
        droneData = d;
    }

    @Override
    public void run() {
        System.out.println("Starting quit...");
        System.out.println("IS MAster "+ droneData.isMaster);
        System.out.println("Is null "+droneData.masterDroneThread!=null);
        droneData.consoleInputThread.exit();
        if(droneData.pingMasterThread!=null)
            droneData.pingMasterThread.close();

        if(droneData.printStatThread!=null){
            droneData.pingMasterThread.close();
        }

        if(droneData.simulatorThread!= null){
            droneData.simulatorThread.stopMeGently();
        }

        if(droneData.isMaster && droneData.masterDroneThread!=null ) {
            droneData.masterDroneSendStatisticsThread.close();
            droneData.masterDroneThread.disconnect();
            try {
                droneData.masterDroneThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("quit Dronazon");

        }

        droneData.checkParticipant();

        if(droneData.isDelivering() && droneData.getDeliveryThread()!=null ){
            try{
                droneData.getDeliveryThread().join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Remaining Order "+ droneData.getOrderQueue().size());
        while(droneData.getOrderQueue().size()!=0) {
            Order order = droneData.getFirstOrder();
            DroneInfo droneInfo = droneData.findFreeDrone(order.getPickupPoint(), true);
            System.out.println("Got drone");
            if (droneInfo == null || droneInfo.getId() == droneData.id) {
                System.out.println("add to queue " + droneInfo == null);
                droneData.addOrderToQueue(order);
                droneData.printOrderQueue();
                try {
                    synchronized (droneData.lockEndingOrderQueue) {
                        droneData.lockEndingOrderQueue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            DroneCommunicationThread thread = new DroneCommunicationThread(droneData, droneInfo, order);
            System.out.println("Sending order...");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(droneData.getChargeThread()!=null){
            System.out.println("Waiting charge ");
            try{
                droneData.getChargeThread().join();
            }catch (InterruptedException e){e.printStackTrace();}
        }



        droneData.masterDroneThread=null;
        droneData.isMaster=false;


        droneData.checkParticipant();

        if(droneData.serverGrpcDrone!=null)
            droneData.serverGrpcDrone.shutdown();

        droneData.serverGrpcDrone=null;
        droneData.deliveryThread=null;

        GlobalStat stat = droneData.getGlobalStat();
        ClientResponse response = DroneMain.SendStats(droneData.client, stat);
        System.out.println(response.toString());

        if (droneData.client != null) {
            DroneMain.RemoveDrone(droneData.client, new DroneInfo(droneData.id, Constants.IP_DRONES, droneData.port));
        }

        droneData.client = null;

        System.out.println("Not in network");
        System.exit(0);
    }
}
