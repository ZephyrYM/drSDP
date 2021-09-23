package AdminInterface;

import ServerAdmin.DroneInfo;
import ServerAdmin.DroneInfos;
import ServerAdmin.GlobalStat;
import ServerAdmin.GlobalStats;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdminInterface {
    public static String serverAddress = "http://localhost:1337";


    public static void main(String args[]) throws Exception{
        Client client= Client.create();

        boolean exit=false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //printCommand();
        int n= 0;
        long t1 = 0;
        long t2 = 0;
        while(!exit){
            printCommand();
            String command = reader.readLine().trim();
            String[] split = command.split(" ");

            try {
                switch (split[0]) {
                    case "exit":
                        exit = true;
                        break;
                    case "network":
                        if (split.length != 1) {
                            System.out.println("Not correct command");
                            break;
                        }
                        GetDrones(client);
                        break;
                    case "stats":
                        if (split.length != 2) {
                            System.out.println("Not correct command");
                            break;
                        }
                        n = Integer.parseInt(split[1]);
                        if(n<0) {
                            System.out.println("You can't use negative numbers");
                            break;
                        }
                        GetStats(client, n);
                        break;
                    case "delivery":
                        if (split.length != 3) {
                            System.out.println("Not correct command");
                            break;
                        }
                        t1 = Long.parseLong(split[1]);
                        t2 = Long.parseLong(split[2]);
                        if(t1<0 || t2<0) {
                            System.out.println("You can't use negative numbers");
                            break;
                        }
                        GetAverageDelivery(client, t1, t2);
                        break;
                    case "km":
                        if (split.length != 3) {
                            System.out.println("Not correct command");
                            break;
                        }
                        t1 = Long.parseLong(split[1]);
                        t2 = Long.parseLong(split[2]);
                        if(t1<0 || t2<0) {
                            System.out.println("You can't use negative numbers");
                            break;
                        }
                        GetAverageKm(client, t1, t2);
                        break;
                    default:
                        System.out.println("Command doesn't exist\n");
                        //printCommand();
                        break;

                }
            }catch (NumberFormatException e){
                System.out.println("Parameters passed are not numbers");
            }
        }

        System.out.println("Bye Bye");

    }

    public static void printCommand(){
        System.out.println("\nCommands:\nnetwork\nstats [positive Number]" +
                "\ndelivery [positiveNumber] [positiveNumber]\nkm [positiveNumber] [positiveNumber]\n");
    }


    public static  void GetDrones(Client client){
        System.out.println("Get drones");
        String getPath = "/admin/get_drones";
        ClientResponse clientResponse = getRequest(client, serverAddress+getPath);
        System.out.println(clientResponse.toString());
        DroneInfos droneInfos = clientResponse.getEntity(DroneInfos.class);
        for(DroneInfo d: droneInfos.getDronesList()){
            System.out.println("Id: "+d.getId()+ " Port: "+d.getPort());
        }
    }

    public static  void GetStats(Client client){
        System.out.println("Get stats");
        String getPath = "/admin/get_stats";
        ClientResponse clientResponse = getRequest(client, serverAddress+getPath);
        System.out.println(clientResponse.toString());
        GlobalStats stats = clientResponse.getEntity(GlobalStats.class);
        for(GlobalStat s: stats.getStatsList()){
            System.out.println("Timestamp: "+s.getTimestamp()+
                    " AverageDelivery: "+s.getAverageDelivery()+ " AverageKilometers: "+s.getAverageKm()+
                    " AveragePollution: "+s.getAveragePolluting()+ " AverageBattery: "+s.getAverageBattery());
        }
    }

    public static  void GetStats(Client client, int n){
        System.out.println("Get stats");
        String getPath = "/admin/get_stats/"+n;
        ClientResponse clientResponse = getRequest(client, serverAddress+getPath);
        System.out.println(clientResponse.toString());
        if(clientResponse.getStatus()!= Response.Status.OK.getStatusCode()) return;
        String statsString =  clientResponse.getEntity(String.class);
        List<GlobalStat> stats = new Gson().fromJson(statsString, new TypeToken<ArrayList<GlobalStat>>(){}.getType());
        System.out.println("HEre ");
        for(GlobalStat s: stats){
            System.out.println("Timestamp: "+s.getTimestamp()+
                    " AverageDelivery: "+s.getAverageDelivery()+ " AverageKilometers: "+s.getAverageKm()+
                    " AveragePollution: "+s.getAveragePolluting()+ " AverageBattery: "+s.getAverageBattery());
        }
    }

    public static void GetAverageDelivery(Client client, long t1, long t2){
        System.out.println("Get average delivery");
        String path = "/admin/get_average_delivery/"+t1+"/"+t2;
        ClientResponse clientResponse = getRequest(client, serverAddress+path);
        System.out.println(clientResponse.toString());
        if(clientResponse.getStatus()!= Response.Status.OK.getStatusCode()) return;
        String s = clientResponse.getEntity(String.class);
        System.out.println("AverageDelivery: "+ s);

    }
    public static void GetAverageKm(Client client, long t1, long t2){
        System.out.println("Get average kilometers");
        String path = "/admin/get_average_km/"+t1+"/"+t2;
        ClientResponse clientResponse = getRequest(client, serverAddress+path);
        System.out.println(clientResponse.toString());
        if(clientResponse.getStatus()!= Response.Status.OK.getStatusCode()) return;
        String s = clientResponse.getEntity(String.class);
        System.out.println("AverageKM: "+ s);
    }

    public static ClientResponse getRequest(Client client, String url){
        WebResource webResource = client.resource(url);
        try {
            return  webResource.type("application/json").get(ClientResponse.class);
        }
        catch(ClientHandlerException e){
            System.out.println("Server non disponibile");
            return null;
        }
    }
}
