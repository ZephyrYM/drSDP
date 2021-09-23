package Drone;

import Dronazon.Order;
import Util.Constants;
import Util.Utility;
import com.google.gson.Gson;

import java.sql.Timestamp;

public class DeliveryThread extends Thread{
    private DroneData drone;
    private Order order;

    public DeliveryThread(DroneData d, Order order){
        this.drone = d;
        this.order = order;
    }

    @Override
    public void run() {
        drone.setDelivering(true);
        System.out.println("Starting delivery "+order.getId()+
                " from "+order.getPickupPoint()+
                " to "+order.getDeliveryPoint());
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Processing data...");

        double km = Utility.distance(drone.position, order.getPickupPoint()) +
                Utility.distance(order.getPickupPoint(), order.getDeliveryPoint());

        drone.setPosition( order.getDeliveryPoint());
        int new_battery = drone.batteryLevel-15;
        //drone.batteryLevel-=15;

        System.out.println(new_battery);
        if(new_battery< Constants.BATTERY_LEVEL_RED_LIMIT){
            System.out.println("Entered exit");
            //new ExitNetworkThread(drone).start();
            //drone.consoleInputThread.interrupt();
            drone.exit();
        }

        DroneStatistics stat = new DroneStatistics(new Timestamp(System.currentTimeMillis()).toString(), drone.position,
                km, new_battery, drone.getAveragePolluting());
        String s = new Gson().toJson(stat);
        drone.addKm(km);
        drone.raiseDelivery();
        System.out.println(s);


        //sendMessage to master
        DroneCommunicationThread thread = new DroneCommunicationThread(drone, drone.getMaster(), stat);
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("__________________________________________\n");

        drone.setDelivering(false);
        drone.updateBattery( new_battery);

    }
}
