package parcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();

    private int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            System.out.printf("Посылка '%s' добавлена в коробку.\n", parcel.getDescription());
        } else {
            System.out.printf("Вес посылки (%s кг.) превышает норму (%s кг.)! Упаковка невозможна!\n",
                    parcel.getWeight(), maxWeight);
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}