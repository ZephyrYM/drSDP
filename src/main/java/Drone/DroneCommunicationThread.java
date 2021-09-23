package Drone;

import Dronazon.Order;
import ServerAdmin.DroneInfo;
import Util.Constants;
import com.drones.messages.DroneMessagesOuterClass;
import com.google.gson.Gson;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.drones.messages.DroneMessagesGrpc;
import com.drones.messages.DroneMessagesGrpc.*;
import com.drones.messages.DroneMessagesOuterClass.*;
import io.grpc.stub.StreamObserver;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DroneCommunicationThread extends Thread{
    private DroneData drone;
    private DroneInfo target;
    private Order order;
    private DroneStatistics stat;
    private Constants.MessageType messageType;
    private int batteryCandidate;
    private int idCandidate;
    private String chargeRequest;

    public DroneCommunicationThread(DroneData drone, DroneInfo target){
        this.drone = drone;
        this.target = target;
        messageType =Constants.MessageType.WELCOME;
    }

    public DroneCommunicationThread(DroneData drone, DroneInfo target, Order order){
        this.drone = drone;
        this.target = target;
        this.order = order;
        messageType = Constants.MessageType.ORDER;
    }

    public DroneCommunicationThread(DroneData drone, DroneInfo target, DroneStatistics stats){
        this.drone = drone;
        this.target = target;
        this.stat = stats;
        messageType = Constants.MessageType.STATS;
    }

    public DroneCommunicationThread(DroneData drone, DroneInfo target, int battery, int id){
        this.drone=drone;
        this.target=target;
        this.batteryCandidate= battery;
        this.idCandidate=id;
        messageType= Constants.MessageType.ELECTION;
    }
    public DroneCommunicationThread(DroneData drone, DroneInfo target,  int id){
        this.drone=drone;
        this.target=target;
        this.idCandidate=id;
        messageType= Constants.MessageType.ELECTED;
    }

    public DroneCommunicationThread(DroneData drone, DroneInfo target, boolean b){
        this.drone=drone;
        this.target=target;
        messageType=b? Constants.MessageType.INFO : Constants.MessageType.ENDCHARGE;
    }

    public DroneCommunicationThread(DroneData drone){
        this.drone=drone;
        messageType= Constants.MessageType.PING;
    }

    public DroneCommunicationThread(DroneData drone, DroneInfo target, ChargeRequest request){
        this.drone=drone;
        this.target= target;
        chargeRequest = new Gson().toJson(request);
        messageType= Constants.MessageType.ASKCHARGE;
    }

    public DroneCommunicationThread(DroneData drone, boolean b){
        this.drone= drone;
        messageType = Constants.MessageType.CONFIRMCHARGE;
    }







    @Override
    public void run() {
        if(target!=null)
            System.out.println("Starting communication with "+target.getId());
       try{
           switch (messageType){
               case WELCOME:
                   welcomeCall();
                   break;
               case ORDER:
                   sendOrder();
                   break;
               case STATS:
                   sendStat();
                   break;
               case ELECTION:
                   sendElection();
                   break;
               case ELECTED:
                   sendElected();
                   break;
               case INFO:
                   sendInfoToNewMaster();
                   break;
               case PING:
                   pingMaster();
                   break;
               case ASKCHARGE:
                   askCharge();
                   break;
               case ENDCHARGE:
                   endedCharge();
                   break;
               case CONFIRMCHARGE:
                   infoMasterRecharging();
                   break;
           }
           //welcomeCall();
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       System.out.println("EndedCommunication");
       drone.printNetworkDrones();

    }

    public void welcomeCall() throws InterruptedException{
        System.out.println("welcomeMessage");
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        WelcomeMessage welcomeMessage = WelcomeMessage.newBuilder().setId(drone.id)
                .setPort(drone.port)
                .setPosX(drone.position.getKey())
                .setPosY(drone.position.getValue())
                .build();

        stub.welcome(welcomeMessage, new StreamObserver<AnswerWelcome>() {
            @Override
            public void onNext(AnswerWelcome value) {
                System.out.println(value.getIsMaster()+ " "+ value.getBatteryLevel());
                List<DroneInfo> copy = drone.GetNetworkDrones();
                DroneInfo info = copy.stream().filter(d->d.getId()==target.getId()).findFirst().get();
                info.setBatteryLevel(value.getBatteryLevel());
                if(!drone.election && value.getIsElection()) drone.election=true;
                //drone.election=value.getIsElection();
                if(value.getIsMaster() && drone.getMaster() == null){
                    System.out.println("Setting new master");
                    drone.setMaster(target.getId());
                }

            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error "+t.getMessage());
                drone.removeDroneInfo(target);
                drone.printNetworkDrones();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });

        channel.awaitTermination(10, TimeUnit.SECONDS);

    }

    public void sendOrder() throws InterruptedException{
        System.out.println("Assign order");
        if(drone.id==target.getId()){
            System.out.println("Communicating with myself");
            if(drone.isDelivering()) {
                drone.addOrderToQueue(order);
                return;
            }
            drone.setDeliveryThread( new DeliveryThread(drone, order));
            //drone.deliveryThread.start();
            return;
        }
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);
        String o= new Gson().toJson(order);
        OrderMessage message = OrderMessage.newBuilder().setOrder(o).build();

        stub.assignDelivery(message, new StreamObserver<OrderMessage>() {
            @Override
            public void onNext(OrderMessage value) {
                System.out.println(value.getOrder());
                if(value.getOrder().equals(Constants.NO_MESSAGE)){
                    drone.addOrderToQueue(order);
                }
                else if (value.getOrder().equals(Constants.OK_MESSAGE)) {
                    drone.updateStatusDeliveringDrone(target.getId(), true);
                    System.out.println("Status of drone " + target.getId() + " updated "+target.getIsDelivering());
                    drone.printNetworkDrones();
                    drone.notifyExit();
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error "+t.getMessage());
                drone.removeDroneInfo(target);
                drone.printNetworkDrones();
                drone.addOrderToQueue(order);
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void sendStat() throws InterruptedException{
        System.out.println("Send statistics");
        if(drone.id==target.getId()){
            System.out.println("Communicating with myself");
            drone.addDroneStatistic(stat);
            drone.updateBatteryOfDrone(drone.id, stat.getRemainingBattery(), stat.getNewPosition());
            drone.printNetworkDrones();
            return;
        }
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        String s = new Gson().toJson(stat);
        StatOrderMessage message = StatOrderMessage.newBuilder().setStatOrder(s).setId(drone.id).build();

        stub.giveStatDelivery(message, new StreamObserver<StatOrderMessage>() {
            @Override
            public void onNext(StatOrderMessage value) {
                System.out.println(value.getStatOrder());
            }

            @Override
            public void onError(Throwable t) {
                drone.setSuspendedDeliveryStat(s);
                drone.deleteMaster();
                drone.startElection();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });

        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void sendElection() throws InterruptedException{
        System.out.println("Send election to "+target.getId());
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        ElectionMessage message = ElectionMessage.newBuilder().setBattery(batteryCandidate).setId(idCandidate).build();

        stub.sendElection(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println("response "+response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                //If it is not connected remove and to new next dronet
                System.out.println("error in election");
                drone.removeDroneInfo(target);
                new DroneCommunicationThread(drone, drone.getNextDrone(), batteryCandidate, idCandidate).start();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void sendElected() throws  InterruptedException{
        System.out.println("Send elected to "+target.getId()+" "+target.getPort());
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        ElectedMessage message = ElectedMessage.newBuilder().setId(idCandidate).build();
        stub.sendElected(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response answerElected) {
                System.out.println("answer "+answerElected.toString());
                //drone.updateBatteryOfDrone(target.getId(), answerElected.getBattery(),
                  //      new Pair<>(answerElected.getPosX(), answerElected.getPosY()));
                //drone.printNetworkDrones();
            }

            @Override
            public void onError(Throwable throwable) {
                //remove from my list
                System.out.println("Error");

                drone.removeDroneInfo(target);
                System.out.println(throwable.getMessage());
                if(drone.getMaster()!=null && target.getId()==drone.getMaster().getId()){
                    System.out.println("Start new election");
                    new DroneCommunicationThread(drone, drone.getNextDrone(), drone.batteryLevel, drone.id).start();
                }
                else {
                    new DroneCommunicationThread(drone, drone.getNextDrone(), idCandidate).start();
                }
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void sendInfoToNewMaster() throws InterruptedException{
        System.out.println("Send info to"+target.getId());
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        AnswerElected message =  AnswerElected.newBuilder().setBattery(drone.batteryLevel)
                .setId(drone.id)
                .setPosX(drone.position.getValue()).setPosY(drone.position.getKey())
                .setIsDelivering(drone.isDelivering()).setStat(drone.suspendedDeliveryStat).build();

        drone.suspendedDeliveryStat="";

        stub.sendInfoToNewMaster(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println("answer "+response.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                //start new election
                drone.deleteMaster();
                drone.startElection();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);

    }

    public void pingMaster() throws InterruptedException{
        System.out.println("Ping master");
        target = drone.getMaster();
        if(target==null) return;
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        Response message = Response.newBuilder().setMessage(Constants.OK_MESSAGE).build();
        stub.pingMaster(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println(response);
            }

            @Override
            public void onError(Throwable throwable) {
                drone.deleteMaster();
                drone.startElection();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void askCharge() throws InterruptedException{
        System.out.println("Ask charge to "+target.getId());
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        DroneMessagesOuterClass.ChargeRequest message = DroneMessagesOuterClass.ChargeRequest.newBuilder()
                .setId(drone.id).setMessage(chargeRequest).build();

        stub.chargeRequest(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                //set value to charge request
                if(response.getMessage().equals(Constants.OK_MESSAGE)){
                    drone.addAnswerCharge(target.getId());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                //set ok to chargeRequest
                drone.addAnswerCharge(target.getId());
                if(drone.getMaster().getId()== target.getId()){
                    drone.deleteMaster();
                    drone.startElection();
                }
                else{
                    drone.removeDroneInfo(target);
                    drone.printNetworkDrones();
                }
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void infoMasterRecharging() throws InterruptedException{
        System.out.println("info master that i am charging");
        target = drone.getMaster();
        if(target==null) return;
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);

        IdMessage message = IdMessage.newBuilder().setId(drone.id).build();
        stub.infoMasterRecharging(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println(response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                drone.deleteMaster();
                drone.startElection();
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);

    }

    public void endedCharge() throws InterruptedException{
        System.out.println("Ended charge");
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(Constants.IP_DRONES+":"+target.getPort()).usePlaintext().build();
        DroneMessagesStub stub =  DroneMessagesGrpc.newStub(channel);
        Pair<Integer, Integer> position = drone.getPosition();
        EndedCharge message = EndedCharge.newBuilder()
                .setId(drone.id).setPosX(position.getKey()).setPosY(position.getValue())
                .setBattery(drone.getBatteryLevel()).build();

        stub.infoEndedCharge(message, new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println(response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                if(drone.getMaster().getId()== target.getId()){
                    drone.deleteMaster();
                    drone.startElection();
                }
                else{
                    drone.removeDroneInfo(target);
                    drone.printNetworkDrones();
                }
                channel.shutdownNow();
            }

            @Override
            public void onCompleted() {

            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);

    }
}
