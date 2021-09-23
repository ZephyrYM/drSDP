package Drone;

import Dronazon.Order;
import ServerAdmin.DroneInfo;
import Util.Constants;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.Timestamp;

public class MasterDroneThread extends Thread {
    private DroneData drone;
    private MqttClient client;

    public MasterDroneThread(DroneData drone) {
        this.drone = drone;
    }

    @Override
    public void run() {
        String broker = Constants.DRONAZON_BROKER;
        String clientId = MqttClient.generateClientId();
        String topic = Constants.DRONAZON_TOPIC;
        int qos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connectOptions);
            System.out.println(clientId + " Connected - Thread PID: " + Thread.currentThread().getId());

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println(clientId + " Connectionlost! cause:" + cause.getMessage() + "-  Thread PID: " + Thread.currentThread().getId());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String time = new Timestamp(System.currentTimeMillis()).toString();
                    String receivedMessage = new String(message.getPayload());
                    System.out.println(clientId + " Received a Message! - Callback - Thread PID: " + Thread.currentThread().getId() +
                            "\n\tTime:    " + time +
                            "\n\tTopic:   " + topic +
                            "\n\tMessage: " + receivedMessage +
                            "\n\tQoS:     " + message.getQos() + "\n");

                    Order order = new Gson().fromJson(receivedMessage, (new Order()).getClass());

                    System.out.println("ID " + order.getId() + " pickup " + order.getPickupPoint() + " delivery " + order.getDeliveryPoint());

                    DroneInfo droneInfo= drone.findFreeDrone(order.getPickupPoint());
                    System.out.println("Got drone");
                    if(droneInfo==null ){//|| droneInfo.getId()== drone.id){
                        System.out.println("add to queue "+ droneInfo==null );
                        if(droneInfo!=null) System.out.println(droneInfo.getId()== drone.id);
                        drone.addOrderToQueue(order);
                        drone.printOrderQueue();
                        return;
                    }

                    DroneCommunicationThread thread = new DroneCommunicationThread(drone, droneInfo, order);
                    System.out.println("Sending order...");
                    thread.start();

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });

            System.out.println(clientId + " Subscribing ... - Thread PID: " + Thread.currentThread().getId());
            client.subscribe(topic, qos);
            System.out.println(clientId + " Subscribed to topics : " + topic);
        } catch (MqttException me) {
            me.printStackTrace();
        }

    }


    public void disconnect() {
        try{
            client.disconnect();
        } catch(MqttException me){
            me.printStackTrace();
        }

        System.out.println("Bye Bye MQTT");
    }
}
