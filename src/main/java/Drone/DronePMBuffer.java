package Drone;


import Simulators.Buffer;
import Simulators.Measurement;
import Util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DronePMBuffer implements Buffer {

    private List<Measurement> buffer;
    private int overlap;
    private DroneData drone;

    public DronePMBuffer(DroneData drone){
        buffer = new ArrayList<Measurement>();
        overlap = (int)(Constants.WINDOW_DIMENSION*Constants.WINDOW_OVERLAP);
        this.drone = drone;
    }

    public List<Measurement> getBuffer(){
        synchronized (buffer){
            return new ArrayList<>(buffer);
        }
    }

    @Override
    public void addMeasurement(Measurement m) {
        synchronized (buffer){
            buffer.add(m);
            if(buffer.size()>= Constants.WINDOW_DIMENSION){
                drone.addAverageMeasurement(readAllAndClean());
            }
        }
    }

    @Override
    public List<Measurement> readAllAndClean() {
        List<Measurement> copyBuffer = getBuffer();
        removeOverlap();
        return copyBuffer.subList(0,
                Constants.WINDOW_DIMENSION>copyBuffer.size() ? copyBuffer.size() : Constants.WINDOW_DIMENSION-1);
    }

    public void removeOverlap(){
        synchronized (buffer){
            if(overlap>= buffer.size()){
                buffer.clear();
                return;
            }
            buffer = buffer.subList(overlap, buffer.size());
            //System.out.println("OVERLAP "+ overlap+" "+ buffer.size());
        }
    }
}
