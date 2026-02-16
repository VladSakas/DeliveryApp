package parcel;

public abstract class Parcel {

    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    protected static final int STANDARD_COST = 2;
    protected static final int PERISHABLE_COST = 3;
    protected static final int FRAGILE_COST = 4;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.printf("Посылка '%s' бережно упакована.\n", description);
    }

    public void deliver() {
        System.out.printf("Посылка '%s' доставлена по адресу: %s.\n", description, deliveryAddress);
    }

    protected abstract int getBaseCost();

    public double calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Посылка '" + description + "' (" + weight + " кг)";
    }

}