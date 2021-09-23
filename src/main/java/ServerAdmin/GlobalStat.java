package ServerAdmin;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GlobalStat {

    private int averageDelivery;
    private double averageKm;
    private double averagePolluting;
    private int averageBattery;
    private long timestamp;

    public GlobalStat(){}
    public GlobalStat (int delivery, double km, double polluting, int battery, long timestamp){
        this.averageDelivery = delivery;
        this.averageKm = km;
        this.averagePolluting = polluting;
        this.averageBattery = battery;
        this.timestamp = timestamp;
    }

    public int getAverageDelivery(){return averageDelivery;}
    public void setAverageDelivery(int delivery){ this.averageDelivery = delivery;}

    public double getAverageKm(){return averageKm;}
    public void setAverageKm(double km){ this.averageKm = km;}

    public double getAveragePolluting(){return averagePolluting;}
    public void setAveragePolluting(double polluting){ this.averagePolluting = polluting;}

    public int getAverageBattery(){return averageBattery;}
    public void setAverageBattery(int battery){ this.averageBattery = battery;}

    public long getTimestamp(){return timestamp;}
    public void setTimestamp(long timestamp){ this.timestamp = timestamp;}


}
