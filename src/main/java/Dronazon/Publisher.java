package Dronazon;

import com.google.gson.Gson;
import javafx.util.Pair;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import Util.Constants;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {
    public static void main(String[] args){
        MqttClient client;
        String broker = Constants.DRONAZON_BROKER;
        String clientId  = MqttClient.generateClientId();
        String topic = Constants.DRONAZON_TOPIC;
        int qos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connectOptions);
            System.out.println(clientId + " Connected");


            int i = 1;
            Gson gson = new Gson();
            while (true) {


                Thread.sleep(Constants.MILLISECONDS_TO_SLEEP);


                Pair<Integer, Integer> pickup =
                        new Pair
                                ((int)(0 + Math.random() * Constants.GRID_DIMENSION),
                                        (int)(0 + Math.random() * Constants.GRID_DIMENSION));
                Pair<Integer, Integer> delivery =
                        new Pair
                                ((int)(0 + Math.random() * Constants.GRID_DIMENSION),
                                        (int)(0 + Math.random() * Constants.GRID_DIMENSION));
                Order order = new Order(String.valueOf(i), pickup, delivery);

                String payload = gson.toJson(order);
                System.out.println(payload);

                MqttMessage message = new MqttMessage(payload.getBytes());

                message.setQos(qos);
                System.out.println(clientId + " Publishing message: " + payload + " ...");
                client.publish(topic, message);
                System.out.println(clientId + " Message published");
            }
        }catch(MqttException me){
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("exception " + me);
            me.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
