package Drone;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerGrpcDrone extends Thread{
    private DroneData drone;
    private Server server;

    public ServerGrpcDrone(DroneData drone){
        this.drone = drone;
    }

    @Override
    public void run() {
        try{
            DroneMessagesImpl impl = new DroneMessagesImpl();
            impl.setDrone(drone);
            server = ServerBuilder.forPort(drone.port).addService(impl).build();
            server.start();
            System.out.println("Listening server started");
            server.awaitTermination();

        }catch (IOException e){
            System.out.println("Error1");
            e.printStackTrace();
        } catch (InterruptedException e){
            System.out.println("Error2");
            e.printStackTrace();
        }
    }

    public void shutdown(){
        if(server == null) return;
        server.shutdown();
    }



}
