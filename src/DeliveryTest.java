import org.junit.jupiter.api.Test;
import parcel.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    private final Parcel standardParcel =
            new StandardParcel("Одежда", 5, "Калининград", 7);
    private final Parcel fragileParcel =
            new FragileParcel("Люстра", 4, "Санкт-Петербург", 25);
    private final Parcel perishableParcel =
            new PerishableParcel("Мяско", 6, "Москва", 1, 14);
    private final PerishableParcel perishableParcel2 =
            new PerishableParcel("Овощи", 5, "Москва", 1, 10);
    private final ParcelBox<StandardParcel> box = new ParcelBox<>(10);

    // цена доставки
    @Test
    public void shouldReturn10ForStandardParcelDeliveryCost() {
        assertEquals(10, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn16ForFragileParcelDeliveryCost() {
        assertEquals(16, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn24ForPerishableParcelDeliveryCost() {
        assertEquals(18, perishableParcel.calculateDeliveryCost());
    }

    //порча скоропортящейся посылки
    @Test
    public void shouldNotBeExpired_BeforeExpirationDate() {
        assertFalse(perishableParcel2.isExpired(5));
    }

    @Test
    public void shouldNotBeExpired_OnExpirationDay() {
        assertFalse(perishableParcel2.isExpired(10));
    }

    @Test
    public void shouldBeExpired_AfterExpirationDate() {
        assertTrue(perishableParcel2.isExpired(11));
    }

    @Test
    public void shouldBeExpired_AfterExpiration() {
        assertTrue(perishableParcel2.isExpired(15));
    }

    //добавление в коробку
    @Test
    public void shouldAddParcelWhenWeightIsLessThanMax() {
        StandardParcel parcel = new StandardParcel("Лёгкая", 5, "Москва", 1);
        box.addParcel(parcel);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void shouldNotAddParcelWhenWeightExceedsMax() {
        StandardParcel parcel = new StandardParcel("Тяжёлая", 15, "Москва", 1);
        box.addParcel(parcel);
        assertEquals(0, box.getAllParcels().size());
    }

    @Test
    public void shouldAddParcelWhenWeightEqualsMax() {
        StandardParcel parcel = new StandardParcel("Средняя", 10, "Москва", 1);
        box.addParcel(parcel);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void shouldWorkWithDifferentBoxTypes() {
        ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(5);
        FragileParcel parcel = new FragileParcel("Ваза", 3, "Москва", 1);
        fragileBox.addParcel(parcel);
        assertEquals(1, fragileBox.getAllParcels().size());
    }
}