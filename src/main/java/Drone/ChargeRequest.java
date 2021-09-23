package Drone;

public class ChargeRequest {
    private int id;
    private long timestamp;


    public ChargeRequest(){}
    public ChargeRequest(int id){
        this.id= id;
        timestamp = System.currentTimeMillis();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChargeRequest{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
