package Drone;

import Dronazon.Order;
import ServerAdmin.DroneInfo;
import ServerAdmin.GlobalStat;
import Simulators.Measurement;
import Simulators.PM10Simulator;
import Util.Constants;
import Util.Utility;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class DroneData {
    public final  String IP = Constants.IP_DRONES;
    public int port;
    public int id;
    public int batteryLevel = 100;
    public Object lockBatteryLevel = new Object();
    public String serverAddress;
    public List<DroneInfo> networkDrones;
    public Pair<Integer, Integer> position;
    public ConsoleInputThread consoleInputThread;
    public ServerGrpcDrone serverGrpcDrone;
    public MasterDroneThread masterDroneThread;
    public DeliveryThread deliveryThread;
    public boolean isDelivering;
    public boolean isMaster;
    public DroneInfo master;
    private int myIndex;
    private DroneInfo me;
    public List<Order> orderQueue;
    public double runKm;
    public Object lockKM= new Object();
    public int deliveryDone;
    public Object lockDeliveryDone = new Object();
    //to synchro
    public List<DroneStatistics> listStats;
    //public boolean isExiting;
    public Client client;
    public boolean participant = false;
    public boolean election= false;

    public String suspendedDeliveryStat = "";
    public Object lockSuspendedDeliveryStat = new Object();


    public PingMasterThread pingMasterThread;
    public PrintStatThread printStatThread;

    public MasterDroneSendStatisticsThread masterDroneSendStatisticsThread;
    public ExitNetworkThread exitNetworkThread= null;

    public Object lockEndingOrderQueue = new Object();

    public List<Double> averagePolluting ;

    public PM10Simulator simulatorThread;

    public List<ChargeRequest> requestsChargeReceived;
    public ChargeRequest myChargeRequest= null;

    public Object lockCharge = new Object();
    public boolean recharging = false;

    public Object lockRechargingBool = new Object();
    public Object lockMyChargeRequest = new Object();
    public Object lockExitThread = new Object();

    public ChargeThread chargeThread;

    public Object lockChargeThread = new Object();

    public Object lockMaster = new Object();
    public Object lockDeliveryThread = new Object();


    public DroneData(int id, int port, String serverAddress, Client client){
        this.id = id;
        this.port = port;
        this.serverAddress = serverAddress;
        isDelivering=false;
        isMaster= false;
        me = new DroneInfo(id,Constants.IP_DRONES, port);
        runKm=0;
        listStats = new ArrayList<>();
        orderQueue = new ArrayList<>();
        //isExiting=false;
        this.client = client;
        averagePolluting = new ArrayList<>();
        requestsChargeReceived= new ArrayList<>();
    }


    public void printNetworkDrones(){
        List<DroneInfo> copy = GetNetworkDrones();
        if(copy==null) return;
        System.out.println(myIndex+" "+isMaster);
        for(DroneInfo info: copy){
            System.out.println("Id: "+info.getId()+", port: "+info.getPort()+ ", battery: "+info.getBatteryLevel()
                    +", isDelivering: "+ info.getIsDelivering()+ ", position: "+info.positionGet()+
                    ", isRecharging: "+ info.isRecharging()+"\n");
        }
    }

    public  List<DroneInfo> GetNetworkDrones(){
        synchronized (networkDrones) {
            return new ArrayList<DroneInfo>(networkDrones);
        }
    }

    public void addDroneInfo(DroneInfo droneInfo){
        synchronized (networkDrones) {
            if (networkDrones == null) networkDrones = new ArrayList<>();
            int index = networkDrones.indexOf(droneInfo);
            if (index == -1) {
                networkDrones.add(droneInfo);
                sortDrone();
                System.out.println("Right");
                return;
            }
            networkDrones.set(index, droneInfo);
        }
    }

    public void sortDrone(){
        synchronized (networkDrones){
            networkDrones.sort(Comparator.comparingInt(DroneInfo::getId));
        }
        findMyIndex();
    }

    public void removeDroneInfo(DroneInfo droneInfo){
        synchronized (networkDrones) {
            networkDrones.remove(droneInfo);
        }
        System.out.println("Remove "+ droneInfo.getId());
        findMyIndex();
    }

    private void findMyIndex(){
        List<DroneInfo> copy = GetNetworkDrones();
        myIndex = copy.indexOf(me);
    }

    public void setMyPosition(){
        synchronized (networkDrones) {
            networkDrones.get(myIndex).setPosition(position);
        }
    }

    public void becomeMaster(){
        synchronized (lockMaster) {
            isMaster = true;
            setMaster(id);
        }
        notParticipant();
        synchronized (lockSuspendedDeliveryStat){
            if(suspendedDeliveryStat!=""){
                DroneStatistics stat = new Gson().fromJson(suspendedDeliveryStat, DroneStatistics.class);
                addDroneStatistic(stat);
                setSuspendedDeliveryStat("");
            }
        }
        masterDroneThread = new MasterDroneThread(this);
        masterDroneSendStatisticsThread= new MasterDroneSendStatisticsThread(this);
        masterDroneThread.start();
        masterDroneSendStatisticsThread.start();
    }


    public void updateBatteryOfDrone(int id, int batteryLevel ){


            DroneInfo drone = findDrone(id);
            if (drone == null) return;
        synchronized (networkDrones) {
            drone.setBatteryLevel(batteryLevel);
            //drone.setDelivering(false);
            updateStatusDeliveringDrone(drone, false);
        }
    }

    public void updateBatteryOfDrone(int id, int batteryLevel, Pair<Integer,Integer> position){
        updateBatteryOfDrone(id, batteryLevel, position, false);
    }
    public void updateBatteryOfDrone(int id, int batteryLevel, Pair<Integer, Integer> position, boolean isDelivering){

            DroneInfo drone = findDrone(id);
            if (drone == null) {
                System.out.println("Not found");
                return;
            }
        synchronized (networkDrones) {
            drone.setBatteryLevel(batteryLevel);
            //drone.setDelivering(false);
            drone.setPosition(position);
            updateStatusDeliveringDrone(drone, isDelivering);
        }
    }

    public void updateBatteryPositionRecharging(int id, int batteryLevel, Pair<Integer, Integer> position){
        DroneInfo drone = findDrone(id);
        if(drone==null)return;
        synchronized (networkDrones){
            drone.setBatteryLevel(batteryLevel);
            drone.setPosition(position);
            drone.setRecharging(false);
        }
    }

    public void updateStatusDeliveringDrone(int id, boolean isDelivering){
        DroneInfo drone = findDrone(id);
        if(drone==null) return;
        synchronized (networkDrones) {
            drone.setDelivering(isDelivering);
        }
        printNetworkDrones();
    }
    public void updateStatusDeliveringDrone(DroneInfo drone, boolean isDelivering){
        drone.setDelivering(isDelivering);
    }



    public DroneInfo findDrone(int id){
        List<DroneInfo> copy = GetNetworkDrones();

        return copy.stream().filter(d->d.getId()==id).findFirst().orElse(null);
    }

    public DroneInfo findFreeDrone(Pair<Integer,Integer> startingPos){
        if(!isMaster) return null;
        List<DroneInfo> copy = GetNetworkDrones();
        List<DroneInfo> infos = copy.stream().filter(d->!d.getIsDelivering() && !d.isRecharging())
                .collect(Collectors.toList());

        int size = infos.size();
        System.out.println("size not delivering "+size);
        if(size==0) return null;
        DroneInfo droneInfo = infos.get(0);
        //System.out.println("something");
        if(size == 1) return droneInfo;
        double minDistance = Utility.distance(droneInfo.positionGet(), startingPos);
        for(int i=1; i<size; i++){

          DroneInfo drone = infos.get(i);
          //if(drone.getIsDelivering()==true) continue;

          double distance = Utility.distance(drone.positionGet(), startingPos);

          if(distance<minDistance ||
                  (distance==minDistance && drone.getBatteryLevel()>droneInfo.getBatteryLevel()) ||
                  (distance==minDistance && drone.getBatteryLevel()==droneInfo.getBatteryLevel() &&
                          drone.getId()>droneInfo.getId())){
              droneInfo = drone;
              minDistance = distance;
          }

        }
        //System.out.println("endSearch");
        return droneInfo;

    }
    public DroneInfo findFreeDrone(Pair<Integer,Integer> startingPos, boolean excludeMaster){
        if(!isMaster) return null;
        List<DroneInfo> copy = GetNetworkDrones();
        List<DroneInfo> infos = copy.stream().filter(
                d->!d.getIsDelivering() && !d.isRecharging() && d.getId()!=id).collect(Collectors.toList());

        int size = infos.size();
        System.out.println("size not delivering "+size);
        if(size==0) return null;
        DroneInfo droneInfo = infos.get(0);
        //System.out.println("something");
        if(size == 1) return droneInfo;
        double minDistance = Utility.distance(droneInfo.positionGet(), startingPos);
        for(int i=1; i<size; i++){

          DroneInfo drone = infos.get(i);
          //if(drone.getIsDelivering()==true) continue;

          double distance = Utility.distance(drone.positionGet(), startingPos);

          if(distance<minDistance || (distance==minDistance && drone.getId()>droneInfo.getId())){
              droneInfo = drone;
              minDistance = distance;
          }

        }
        //System.out.println("endSearch");
        return droneInfo;

    }

    public void addOrderToQueue(Order order){
        //System.out.println("Adding order to queue");
        synchronized (orderQueue) {
            orderQueue.add(order);
        }
    }

    public Order getFirstOrder(){
        synchronized (orderQueue){
            Order order = orderQueue.get(0);
            orderQueue.remove(0);
            System.out.println("Order queue "+orderQueue.size());
            return order;
        }
    }

    public List<Order> getOrderQueue(){
        synchronized (orderQueue){
            return new ArrayList<Order>(orderQueue);
        }
    }

    public void printOrderQueue() {
        List<Order> copy = getOrderQueue();
        if(copy.size()==0) return;

        for(Order order:copy){
            //order.toString();
            System.out.println(order.toString());
        }
    }

    public void addDroneStatistic(DroneStatistics stat){
        synchronized (listStats){
            listStats.add(stat);
        }

    }

    public List<DroneStatistics> getListStats(){
        synchronized (listStats){
            return new ArrayList<DroneStatistics>(listStats);
        }
    }

    public DroneInfo getNextDrone(){
        synchronized (networkDrones){
            int index = myIndex==networkDrones.stream().count()-1 ? 0 : myIndex+1;
            return networkDrones.get(index);
        }
    }

    public void setMaster(int id){
        election=false;
        DroneInfo drone = findDrone(id);
        if(drone==null) return;
        synchronized (lockMaster){
            master = drone;
        }

    }

    public boolean isMaster(){
        synchronized (lockMaster){
            return isMaster;
        }
    }


    public void startElection(){
        System.out.println("Trying to start new election");
        if(participant) return;
        participant =true;
        System.out.println("Send message election");
        new DroneCommunicationThread(this, getNextDrone(), batteryLevel, id).start();
    }

    public synchronized void checkParticipant(){
        while(participant) {
            try{wait();}catch (InterruptedException e){e.printStackTrace();}
            if(!participant) return;
        }

    }

    public synchronized void notParticipant(){
        participant=false;
        notifyAll();
    }

    public DroneInfo getMaster(){
        synchronized (lockMaster) {
            if (master == null) return null;
            return master;
        }

    }

    public void deleteMaster(){
        if(master==null) return;
        //removeDroneInfo(master);
        master=null;

    }

    public void addKm(double km){
        synchronized (lockKM){
            runKm+=km;
        }
    }

    public double getRunKm(){
        synchronized (lockKM){
            return runKm;
        }
    }

    public void raiseDelivery(){
        synchronized (lockDeliveryDone) {
            deliveryDone++;
        }
    }

    public int getDeliveryDone(){
        synchronized (lockDeliveryDone){
            return deliveryDone;
        }
    }

    public void printStats(){
        System.out.println("\nStats\n#Delivery: "+getDeliveryDone()+" TotalKm: "+getRunKm()+
                " Battery: "+getBatteryLevel());
    }

    public void updateBattery(int newBattery){
        synchronized (lockBatteryLevel){
            batteryLevel= newBattery;
        }
        updateBatteryOfDrone(id, newBattery);
    }

    public int getBatteryLevel(){
        synchronized (lockBatteryLevel){
           return batteryLevel;
        }
    }

    public GlobalStat getGlobalStat(){
        List<DroneStatistics> list = getListStats();
        emptyListStats();
        int deliveryDone= list.size()/GetNetworkDrones().size();
        double km=0;
        int battery=0;
        double polluting=0;
        double numberMeasurement=0;

        for (DroneStatistics s: list) {
            km+=s.getKm();
            battery+=s.getRemainingBattery();
            polluting += s.getPolluting().stream().mapToDouble(Double::doubleValue).sum();
            numberMeasurement+= s.getPolluting().size();
        }

        km= list.size()==0?0: km/list.size();
        battery =list.size()==0?0: battery/ list.size();
        polluting =numberMeasurement==0? 0: polluting/numberMeasurement;

        GlobalStat stat = new GlobalStat(deliveryDone, km, polluting, battery, System.currentTimeMillis());
        return stat;
    }

    public  void exit(){
        synchronized (lockExitThread) {
            if (exitNetworkThread != null) return;
            exitNetworkThread = new ExitNetworkThread(this);
            exitNetworkThread.start();
        }
    }

    public boolean isExiting(){
        synchronized (lockExitThread) {
            return exitNetworkThread != null;
        }
    }

    public void notifyExit(){
        synchronized (lockEndingOrderQueue) {
            lockEndingOrderQueue.notifyAll();
        }
    }

    public void setSuspendedDeliveryStat(String stat){
        synchronized (lockSuspendedDeliveryStat){
            suspendedDeliveryStat= stat;
        }
    }

    public void emptyListStats(){
        synchronized (listStats){
            listStats.clear();
        }
    }

    public void addAverageMeasurement(List<Measurement> m){
        if(m== null || m.size()==0) return;
        double d =0;
        for (Measurement measure: m) {
            d += measure.getValue();
        }

        synchronized (averagePolluting){
            averagePolluting.add(d/m.size());
        }

    }

    public List<Double> getAveragePolluting(){
        synchronized (averagePolluting){
            List<Double> copy = new ArrayList<>(averagePolluting);
            averagePolluting.clear();
            System.out.println("New dimension "+averagePolluting.size()+" "+ copy.size());
            return copy;
        }
    }

    public void setPosition(Pair<Integer, Integer> pos){
        synchronized (position){
            position=pos;
        }

        setMyPosition();
        //printNetworkDrones();
    }

    public Pair<Integer, Integer> getPosition(){
        synchronized (position){
            return position;
        }
    }

    public ChargeRequest getMyChargeRequest(){
        synchronized (lockMyChargeRequest){
            return myChargeRequest;
        }
    }

    public void createMyChargeRequest(){
        synchronized (lockMyChargeRequest){
            myChargeRequest = new ChargeRequest(id);
        }
    }

    public void deleteMyChargeRequest(){
        synchronized (lockMyChargeRequest){
            myChargeRequest=null;
        }
    }

    public boolean isRecharging(){
        synchronized (lockRechargingBool){
            return recharging;
        }
    }

    public void setRecharging(boolean b){
        synchronized (lockRechargingBool){
            recharging=b;
        }
        if(!b) return;
        synchronized (networkDrones){
            setDroneRecharging(id);
        }
    }

    public void endedChargeThread(){
        synchronized (lockChargeThread){
            chargeThread=null;
        }
    }


    public void startCharge(){
        if(isRecharging() || getMyChargeRequest()!=null) return;
        synchronized (lockChargeThread) {
            if(chargeThread!=null) return;
            createMyChargeRequest();
            chargeThread = new ChargeThread(this);
            chargeThread.start();
        }
    }

    public void addRequestCharge(ChargeRequest r){
        synchronized (requestsChargeReceived){
            requestsChargeReceived.add(r);
        }
    }

    public List<ChargeRequest> getRequestsChargeReceived(){
        synchronized (requestsChargeReceived){
            return new ArrayList<>(requestsChargeReceived);
        }
    }

    public void emptyRequestChargeReceived(){
        synchronized (requestsChargeReceived){
            requestsChargeReceived.clear();
        }
    }

    public void addAnswerCharge(int id){
        synchronized (lockChargeThread){
            if(chargeThread==null) return;
            chargeThread.addAnswer(id);
            lockChargeThread.notifyAll();
        }
    }

    public ChargeThread getChargeThread(){
        synchronized (lockChargeThread){
            return chargeThread;
        }
    }

    public void setDroneRecharging(int id){
        DroneInfo d = findDrone(id);
        if(d== null) return;
        synchronized (networkDrones){
            d.setRecharging(true);
        }
    }

    public boolean isDelivering(){
        synchronized (lockDeliveryThread){
            return isDelivering;
        }
    }

    public void setDelivering(boolean b){
        synchronized (lockDeliveryThread){
            isDelivering=b;
        }

        if(b)
            updateStatusDeliveringDrone(id, true);
    }

    public DeliveryThread getDeliveryThread(){
        synchronized (lockDeliveryThread){
            return deliveryThread;
        }
    }

    public void setDeliveryThread(DeliveryThread d) {
        synchronized (lockDeliveryThread){
            deliveryThread=d;
        }
        deliveryThread.start();
    }








}

