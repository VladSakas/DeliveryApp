package parcel;

public class FragileParcel extends Parcel implements Trackable {

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getBaseCost() {
        return FRAGILE_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка %s изменила местоположение на %s.\n", description, newLocation);
    }
}