package parcel;

public abstract class Parcel {

    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;
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

    abstract int getBaseCost();

    public double calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public int getSendDay() {
        return sendDay;
    }

    @Override
    public String toString() {
        return "Посылка '" + description + "' (" + weight + " кг)";
    }
}