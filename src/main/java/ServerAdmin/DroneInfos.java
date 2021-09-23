package ServerAdmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DroneInfos {

    @XmlElement(name = "drones_List")
    private List<DroneInfo> dronesList;

    private static DroneInfos instance;

    private DroneInfos(){
        dronesList = new ArrayList<DroneInfo>();
    }

    public synchronized  static DroneInfos getInstance(){
        if(instance == null){
            instance = new DroneInfos();
        }
        return instance;
    }

    public synchronized List<DroneInfo> getDronesList(){
        return new ArrayList<DroneInfo>(dronesList);
    }

    public synchronized boolean addDrone(DroneInfo droneInfo) {
        if(dronesList.stream().anyMatch(d -> d.getId()==(droneInfo.getId()))) return false;
        dronesList.add(droneInfo);
        return true;
    }

    public synchronized void removeDrone(DroneInfo droneInfo){
        DroneInfo d = getById(droneInfo.getId());
        dronesList.remove(d);
    }

    public DroneInfo getById(int id){
        List<DroneInfo> dronesCopy = getDronesList();
        Optional<DroneInfo> drone = dronesCopy.stream().filter(d->d.getId()==id).findFirst();
        return drone.orElse(null);
    }


}
