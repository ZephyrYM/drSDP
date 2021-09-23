package ServerAdmin;

import javafx.util.Pair;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class DroneInfo {

    private int id;
    private String ip;
    private int port;
    private int batteryLevel;
    private boolean isDelivering;
    private int posX;
    private int posY;
    private boolean recharging;

    public DroneInfo(){}
    public DroneInfo(int id, String ip, int port){
        this.id = id;
        this.ip = ip;
        this.port = port;
        batteryLevel=0;
        isDelivering=false;
    }

    public DroneInfo(int id, String ip, int port, int battery){
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.batteryLevel=battery;
        isDelivering=false;
    }

    public DroneInfo(int id, String ip, int port, int battery, boolean isDelivering){
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.batteryLevel=battery;
        this.isDelivering = isDelivering;
    }



    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getIp(){return ip;}

    public void setIp(String ip){this.ip = ip;}

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean getIsDelivering() {
        return isDelivering;
    }

    public void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosition(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosition(Pair<Integer, Integer> pos){
        this.posX = pos.getKey();
        this.posY = pos.getValue();
    }

    public boolean isRecharging() {
        return recharging;
    }

    public void setRecharging(boolean b){
        recharging=b;
    }

    public Pair<Integer, Integer> positionGet(){
        return new Pair<>(posX, posY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DroneInfo)) return false;
        DroneInfo droneInfo = (DroneInfo) o;
        return getId() == droneInfo.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
