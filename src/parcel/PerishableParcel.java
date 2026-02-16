package parcel;

public class PerishableParcel extends Parcel {

    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive <= currentDay;
    }

    @Override
    public int getBaseCost() {
        return PERISHABLE_COST;
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка %s обёрнута в защитную плёнку.\n", description);
        super.packageItem();
    }
}