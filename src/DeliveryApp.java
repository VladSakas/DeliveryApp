import parcel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackingParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(10);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(5);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(7);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine()); //выбираем, что сделать

            switch (choice) {
                case 1 -> addParcel(); //добавляем посылку
                case 2 -> sendParcels();
                case 3 -> calculateCosts();
                case 4 -> updateTrackingStatus();
                case 5 -> showBoxContent();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Трекинг");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        while (true) {
            showParcelMenu(); // показываем меню какую посылку отправить
            int parcelType = Integer.parseInt(scanner.nextLine()); //выбираем тип посылки

            if (parcelType > 0 && parcelType < 4) {
                Parcel parcel = createParcel(parcelType);
                allParcels.add(parcel);

                switch (parcelType) {
                    case 1 -> standardBox.addParcel((StandardParcel) parcel);
                    case 2 -> fragileBox.addParcel((FragileParcel) parcel);
                    case 3 -> perishableBox.addParcel((PerishableParcel) parcel);
                }

                System.out.println(parcel + " успешно добавлена в список!");

                if (parcel instanceof Trackable) {
                    trackingParcels.add((Trackable) parcel);
                    System.out.println("Посылка добавлена в систему отслеживания!");
                }
                break;

            } else {
                System.out.println("Ошибка! Неверный тип посылки. Введите число от 1 до 3");
            }

        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        double totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставок: " + totalCost + " у.е.");
    }

    private static void showParcelMenu() {
        System.out.println("Какой тип посылки необходимо доставить?");
        System.out.println("Введите число от 1 до 3 соответствующее типу посылки");
        System.out.println("1 - Стандартная посылка");
        System.out.println("2 - Хрупкая посылка");
        System.out.println("3 - Скоропортящаяся посылка");
    }

    private static Parcel createParcel(int parcelType) {
        System.out.print("Введите описание: ");
        String description = scanner.nextLine();

        System.out.print("Введите вес: ");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите адрес доставки: ");
        String address = scanner.nextLine();

        int sendDay;

        while (true) {
            System.out.print("Введите день отправки: ");
            sendDay = Integer.parseInt(scanner.nextLine());
            if (sendDay < 1 || sendDay > 31) {
                System.out.println("Выход за пределы месяца! Введите число от 1 до 31");
            } else {
                break;
            }
        }

        switch (parcelType) {
            case 1:
                return new StandardParcel(description, weight, address, sendDay);
            case 2:
                return new FragileParcel(description, weight, address, sendDay);
            case 3:
                System.out.print("Введите срок годности: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                return new PerishableParcel(description, weight, address, sendDay, timeToLive);
            default:
                return null;
        }
    }

    private static void updateTrackingStatus() {
        if (trackingParcels.isEmpty()) {
            System.out.println("Нет посылок в системе отслеживания");
            return;
        }

        System.out.print("Введите новое местоположение (для всех посылок): ");
        String newLocation = scanner.nextLine();

        System.out.println("Обновление статусов отслеживания...");

        for (Trackable trackable : trackingParcels) {
            trackable.reportStatus(newLocation);
        }

        System.out.println("Статус всех посылок обновлён!");
    }

    private static void showBoxContent() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 - Стандартные посылки");
        System.out.println("2 - Хрупкие посылки");
        System.out.println("3 - Скоропортящиеся посылки");

        int boxType = Integer.parseInt(scanner.nextLine());

        switch (boxType) {
            case 1 -> {
                for (Parcel parcel : standardBox.getAllParcels()) {
                    System.out.println(parcel);
                }
            }
            case 2 -> {
                for (Parcel parcel : fragileBox.getAllParcels()) {
                    System.out.println(parcel);
                }
            }
            case 3 -> {
                for (Parcel parcel : perishableBox.getAllParcels()) {
                    System.out.println(parcel);
                }
            }
            default -> System.out.println("Неверный выбор! Введите число от 1 до 3");
        }
    }
}