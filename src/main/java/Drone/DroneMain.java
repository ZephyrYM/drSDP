package Drone;

import ServerAdmin.DroneInfo;
import ServerAdmin.DroneInfos;
import ServerAdmin.GlobalStat;
import ServerAdmin.RegistrationMessage;
import Simulators.PM10Simulator;
import Util.Constants;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javafx.util.Pair;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DroneMain {
    public final static String serverAddress = Constants.SERVER_ADMIN_ADDRESS;

    public static void main (String args[]) throws Exception {

        Client client= Client.create();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert Id...");
        int id = Integer.parseInt(reader.readLine());

        System.out.println("Insert Port Number...");
        int port =Integer.parseInt( reader.readLine());

        System.out.println("Id "+id+" Port "+port);
        DroneData d = new DroneData(id, port, serverAddress, client);

        DroneInfo droneInfo = new DroneInfo(d.id, d.IP, d.port);


        ClientResponse clientResponse = null;
        //GetDrones(client);


        clientResponse = AddDrone(client, droneInfo);
        System.out.println(clientResponse.toString());
        if(clientResponse.getStatus() != Response.Status.OK.getStatusCode()){
            System.out.println("There is already another drone with same id");
            return;
        }
        RegistrationMessage regMessage = clientResponse.getEntity(RegistrationMessage.class);

        d.networkDrones = regMessage.getDrones();

        d.position= new Pair<>(regMessage.getPositionX(), regMessage.getPositionY());
        d.sortDrone();
        d.setMyPosition();
        d.updateBattery(100);
        System.out.println("Drones in network " + d.networkDrones.size() + " position " + d.position);
        d.printNetworkDrones();


        d.consoleInputThread = new ConsoleInputThread(d);
        d.serverGrpcDrone = new ServerGrpcDrone(d);

        if(d.networkDrones.size()==1) d.becomeMaster();

        d.consoleInputThread.start();
        d.serverGrpcDrone.start();

        List<DroneInfo> copy = d.GetNetworkDrones();
        List<DroneCommunicationThread> drones = new ArrayList<DroneCommunicationThread>();
        if(copy!=null) {
            for (DroneInfo nD : copy) {
                if(nD.getId() == d.id) continue;
                DroneCommunicationThread threadComm= new DroneCommunicationThread(d, nD);
                drones.add(threadComm);
                threadComm.start();
            }
        }

        for(DroneCommunicationThread comm: drones){
            comm.join();
        }

        System.out.println("//////////////////////////////////////////////////////\n");

        d.simulatorThread = new PM10Simulator(new DronePMBuffer(d));
        d.simulatorThread.start();

        if(d.getMaster()==null && !d.election && !d.participant){
            d.startElection();
        }

        d.pingMasterThread= new PingMasterThread(d);
        d.pingMasterThread.start();

        d.printStatThread = new PrintStatThread(d);
        d.printStatThread.start();


    }

    public static ClientResponse AddDrone(Client client, DroneInfo d){
        String postPath = "/drones/add_drone";
        return postRequest(client, serverAddress+postPath, d);

    }

    public static ClientResponse SendStats(Client client, GlobalStat stat){
        String postPath = "/drones/add_stat";
        return postRequest(client, serverAddress+postPath,stat );
    }

    public static ClientResponse postRequest(Client client, String url, DroneInfo d){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return  webResource.type("application/json").post(ClientResponse.class, input);
        }
        catch(ClientHandlerException e){
            System.out.println("Server non disponibile");
            return null;
        }
    }
    public static ClientResponse postRequest(Client client, String url, GlobalStat d){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return  webResource.type("application/json").post(ClientResponse.class, input);
        }
        catch(ClientHandlerException e){
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public static void RemoveDrone(Client client, DroneInfo d){
        System.out.println("Delete drone");
        String getPath = "/drones/remove";

        ClientResponse clientResponse = deleteRequest(client, serverAddress+getPath, d);
        System.out.println(clientResponse.toString());
    }

    public static ClientResponse deleteRequest(Client client, String url, DroneInfo d){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return  webResource.type("application/json").delete(ClientResponse.class, input);
        }
        catch(ClientHandlerException e){
            System.out.println("Server non disponibile");
            return null;
        }
    }




}
