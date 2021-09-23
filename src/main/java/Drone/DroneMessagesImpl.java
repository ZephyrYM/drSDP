package Drone;

import Dronazon.Order;
import ServerAdmin.DroneInfo;
import Util.Constants;
import com.drones.messages.DroneMessagesGrpc.*;
import com.drones.messages.DroneMessagesOuterClass;
import com.drones.messages.DroneMessagesOuterClass.*;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import javafx.util.Pair;

import java.lang.annotation.Repeatable;

public class DroneMessagesImpl extends DroneMessagesImplBase {
    private DroneData drone;

    public void setDrone(DroneData drone) {this.drone=drone;}


    @Override
    public void welcome(WelcomeMessage request, StreamObserver<AnswerWelcome> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
      System.out.println(request);
        DroneInfo droneInfo = new DroneInfo(request.getId(), Constants.IP_DRONES,request.getPort(),100);

        droneInfo.setPosition(request.getPosX(), request.getPosY());

        drone.addDroneInfo(droneInfo);
        drone.printNetworkDrones();
      AnswerWelcome answer = AnswerWelcome.newBuilder()
              .setIsMaster(drone.isMaster)
              .setBatteryLevel(drone.batteryLevel).setIsElection(drone.participant).build();
              //.setPosX(drone.position.getKey())
              //.setPosY(drone.position.getValue())
              //.build();

      responseObserver.onNext(answer);

      responseObserver.onCompleted();
      System.out.println("Welcome received");
      drone.notifyExit();
    }

    @Override
    public void assignDelivery(OrderMessage request, StreamObserver<OrderMessage> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        System.out.println(request);
        if(drone.isDelivering()){
            System.out.println("\nIt is already delivering\n");
            OrderMessage message = OrderMessage.newBuilder().setOrder(Constants.NO_MESSAGE).build();
            responseObserver.onNext(message);
            responseObserver.onCompleted();
            return;
        }
        Order order = new Gson().fromJson(request.getOrder(), Order.class);
        OrderMessage message = OrderMessage.newBuilder().setOrder(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();

        drone.setDeliveryThread(new DeliveryThread(drone, order));
        //drone.deliveryThread = new DeliveryThread(drone, order);
        //drone.deliveryThread.start();
    }

    @Override
    public void giveStatDelivery(StatOrderMessage request, StreamObserver<StatOrderMessage> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        System.out.println(request);
        DroneStatistics stat = new Gson().fromJson(request.getStatOrder(), DroneStatistics.class);

        //check if i'm master
        StatOrderMessage message = StatOrderMessage.newBuilder().setStatOrder(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();

        drone.addDroneStatistic(stat);
        drone.updateBatteryOfDrone(request.getId(), stat.getRemainingBattery(), stat.getNewPosition());
        drone.printNetworkDrones();

        if(drone.getOrderQueue().size()==0) return;

        System.out.println("Assign order after receiving");
        Order order = drone.getFirstOrder();

        DroneInfo droneInfo= drone.findFreeDrone(order.getPickupPoint());
        System.out.println("Got drone");
        if(droneInfo==null){//|| droneInfo.getId()== drone.id){
            System.out.println("add to queue "+ droneInfo==null );
            if(droneInfo!=null) System.out.println(droneInfo.getId()== drone.id);
            drone.addOrderToQueue(order);
            drone.printOrderQueue();
            drone.notifyExit();
            return;
        }

        DroneCommunicationThread thread = new DroneCommunicationThread(drone, droneInfo, order);
        System.out.println("Sending order...");
        thread.start();
    }

    @Override
    public void sendElection(ElectionMessage request, StreamObserver<Response> responseObserver) {
       try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}

        System.out.println(request.toString());
        int batteryCandidate = request.getBattery();
        int idCandidate = request.getId();
        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();
        int myBattery = !drone.isDelivering()? drone.batteryLevel : drone.batteryLevel-15;
        System.out.println("myID: "+drone.id+" myBattery: "+myBattery);
        if(idCandidate==drone.id){
            System.out.println("same Id");
            //drone.participant =false;
            drone.notParticipant();
            new DroneCommunicationThread(drone, drone.getNextDrone(), idCandidate).start();
            return;
        }
        /*if(!drone.partecipant){
            System.out.println("different Id and not partecipant");
            if (myBattery>batteryCandidate ||(myBattery==batteryCandidate && drone.id>idCandidate )){
                //send with my data
                System.out.println("i'm a better candidate");
                batteryCandidate=myBattery;
                idCandidate=drone.id;
            }
            drone.partecipant = true;
            new DroneCommunicationThread(drone, drone.getNextDrone(), batteryCandidate, idCandidate).start();
        }*/

        if (myBattery>batteryCandidate ||(myBattery==batteryCandidate && drone.id>idCandidate && !drone.participant)){
            System.out.println("i'm a better candidate");
            batteryCandidate=myBattery;
            idCandidate=drone.id;
            drone.participant = true;
            new DroneCommunicationThread(drone, drone.getNextDrone(), batteryCandidate, idCandidate).start();
        }
        else if(myBattery<batteryCandidate ||(myBattery==batteryCandidate && drone.id<idCandidate )){
            System.out.println("forward message");
            drone.participant = true;
            new DroneCommunicationThread(drone, drone.getNextDrone(), batteryCandidate, idCandidate).start();
        }



    }

    @Override
    public void sendElected(ElectedMessage request, StreamObserver<Response> responseObserver) {
       try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}

        System.out.println(request.toString());
        if(request.getId()==drone.id){
            System.out.println("ended election I am new master");
            drone.becomeMaster();
            Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
            responseObserver.onNext(message);
            responseObserver.onCompleted();
            return;
        }
        drone.setMaster(request.getId());


        System.out.println("Assign new master");
        /*AnswerElected message = AnswerElected.newBuilder().setBattery(drone.batteryLevel)
                .setPosX(drone.position.getValue()).setPosY(drone.position.getKey()).build();*/
        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();
        new DroneCommunicationThread(drone, drone.getMaster(), true).start();
        drone.notParticipant();
        new DroneCommunicationThread(drone, drone.getNextDrone(), request.getId()).start();
    }

    @Override
    public void sendInfoToNewMaster(AnswerElected request, StreamObserver<Response> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        System.out.println(request.toString());
        System.out.println("SET stat of drone "+request.getId()+ " "+ request.getPosX()+ " "+request.getPosY());
        drone.updateBatteryOfDrone(request.getId(), request.getBattery(),
                      new Pair<>(request.getPosX(), request.getPosY()), request.getIsDelivering());
        drone.printNetworkDrones();

        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();

        if(request.getStat()!=""){
            DroneStatistics stat = new Gson().fromJson(request.getStat(), DroneStatistics.class);
            drone.addDroneStatistic(stat);
        }
    }

    @Override
    public void pingMaster(Response request, StreamObserver<Response> responseObserver) {
        System.out.println(request.toString());
        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();
    }

    @Override
    public void chargeRequest(DroneMessagesOuterClass.ChargeRequest request, StreamObserver<Response> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        String m= "";
        if(drone.id==request.getId()){
            System.out.println("It's me");
            Response message = Response.newBuilder().setMessage(m).build();
            responseObserver.onNext(message);
            responseObserver.onCompleted();
            drone.addAnswerCharge(drone.id);
            return;
        }
        ChargeRequest chargeRequest = new Gson().fromJson(request.getMessage(), ChargeRequest.class);
        if(drone.isRecharging()){
            System.out.println("I'm using charger");
            drone.addRequestCharge(chargeRequest);
            Response message = Response.newBuilder().setMessage(m).build();
            responseObserver.onNext(message);
            responseObserver.onCompleted();
            return;
        }

        if(drone.getMyChargeRequest()==null){
            System.out.println("It's ok, I don't want to use it");
            m=Constants.OK_MESSAGE;
            Response message = Response.newBuilder().setMessage(m).build();
            responseObserver.onNext(message);
            responseObserver.onCompleted();
            return;
        }

        boolean b= drone.getMyChargeRequest().getTimestamp()<= chargeRequest.getTimestamp();
        if(b){
            System.out.println("I was first");
            drone.addRequestCharge(chargeRequest);
        }
        else{
            System.out.println("It was first");
            m=Constants.OK_MESSAGE;
        }

        Response message = Response.newBuilder().setMessage(m).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();


    }

    @Override
    public void infoEndedCharge(EndedCharge request, StreamObserver<Response> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        drone.addAnswerCharge(request.getId());
        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();

        if(!drone.isMaster()) return;
        drone.updateBatteryPositionRecharging(request.getId(), request.getBattery(),
                new Pair<>(request.getPosX(), request.getPosY()));

    }

    @Override
    public void infoMasterRecharging(IdMessage request, StreamObserver<Response> responseObserver) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}
        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();

        drone.setDroneRecharging(request.getId());
    }
}
