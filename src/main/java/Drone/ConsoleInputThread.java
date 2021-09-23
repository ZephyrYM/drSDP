package Drone;

import ServerAdmin.DroneInfo;
import Util.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.grpc.internal.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleInputThread extends Thread{
    private DroneData droneData;
    private boolean exit;
    private BufferedReader reader;





    public ConsoleInputThread(DroneData data){
        droneData = data;
        exit=false;

        reader = new BufferedReader(new InputStreamReader(System.in));

        //reader = new BufferedReader(socket.getInputStream());
    }
    @Override
    public void run()  {
        while(!exit){
            try{
                String input = reader.readLine();
                switch (input){
                    case "quit":
                        if(!droneData.isExiting() && droneData.client!=null && !droneData.isRecharging()) {
                            droneData.exit();
                            exit();
                        }
                        break;
                    case "recharge":
                        if(!droneData.isRecharging() && !droneData.isExiting())
                            droneData.startCharge();
                        break;
                    default:
                        break;
                }

            }catch (IOException e){
                e.printStackTrace();
                exit=true;
            }

        }

        //if(droneData.client!=null)
          //  new ExitNetworkThread(droneData).start();

    }


    public void exit(){
        System.out.println("Ending ConsoleInputThread");
        exit=true;
    }
}
