package Dronazon;

import javafx.util.Pair;

public class Order {
    private String id;
    private Pair<Integer,Integer> pickupPoint;
    private Pair<Integer,Integer> deliveryPoint;

    public Order(){}
    public Order(String id, Pair<Integer,Integer> pickup, Pair<Integer,Integer> delivery){
        this.id = id;
        this.pickupPoint = pickup;
        this.deliveryPoint = delivery;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pair<Integer, Integer> getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(Pair<Integer, Integer> deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public Pair<Integer, Integer> getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(Pair<Integer, Integer> pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", pickupPoint=" + pickupPoint +
                ", deliveryPoint=" + deliveryPoint +
                '}';
    }
}
