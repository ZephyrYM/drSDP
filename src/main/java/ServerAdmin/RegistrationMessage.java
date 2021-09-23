package ServerAdmin;

import Util.Constants;
import javafx.util.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class RegistrationMessage {
    private List<DroneInfo> drones;
    private int positionX;
    private int positionY;

    public RegistrationMessage(){}
    public RegistrationMessage(List<DroneInfo> drones, int posX, int posY){
        this.drones = drones;
        positionX = posX;
        positionY = posY;

    }

    public List<DroneInfo> getDrones() {
        return drones;
    }

    public void setDrones(List<DroneInfo> drones) {
        this.drones = drones;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int position) {
        this.positionX = position;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
