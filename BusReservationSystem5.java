import java.util.Scanner;

abstract class Bus {
    private String brand;
    private String model;
    private float fare;
    private int availableSeats;

    Bus(String brand, String model, float fare, int availableSeats) {
        this.brand = brand;
        this.model = model;
        this.fare = fare;
        this.availableSeats = availableSeats;
    }

    public abstract void displaySpecialFeatures();

    public void displayBusInfo() {
        System.out.println("\nBrand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Fare: Rs. " + fare);
        System.out.println("Available Seats: " + availableSeats);
    }
}

class LuxuryBus extends Bus {
    private String amenities;

    LuxuryBus(String brand, String model, float fare, int availableSeats, String amenities) {
        super(brand, model, fare, availableSeats);
        this.amenities = amenities;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Amenities: " + amenities);
    }
}

final class ExpressBus extends Bus {
    private String route;

    ExpressBus(String brand, String model, float fare, int availableSeats, String route) {
        super(brand, model, fare, availableSeats);
        this.route = route;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Route: " + route);
    }
}

class LuxuryBusReservationThread extends Thread {
    private LuxuryBus luxuryBus;

    LuxuryBusReservationThread(LuxuryBus luxuryBus) {
        this.luxuryBus = luxuryBus;
    }

    @Override
    public void run() {
        System.out.println("Luxury Bus Information:");
        luxuryBus.displayBusInfo();
        luxuryBus.displaySpecialFeatures();
    }
}

class ExpressBusReservationThread extends Thread {
    private ExpressBus expressBus;

    ExpressBusReservationThread(ExpressBus expressBus) {
        this.expressBus = expressBus;
    }

    @Override
    public void run() {
        System.out.println("Express Bus Information:");
        expressBus.displayBusInfo();
        expressBus.displaySpecialFeatures();
    }
}

public class BusReservationSystem5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Luxury Bus Details:");
        System.out.print("Brand: ");
        String luxuryBrand = scanner.nextLine();
        System.out.print("Model: ");
        String luxuryModel = scanner.nextLine();
        System.out.print("Fare: Rs. ");
        float luxuryFare = scanner.nextFloat();
        System.out.print("Available Seats: ");
        int luxuryAvailableSeats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Amenities: ");
        String luxuryAmenities = scanner.nextLine();

        LuxuryBus luxuryBus = new LuxuryBus(luxuryBrand, luxuryModel, luxuryFare, luxuryAvailableSeats,
                luxuryAmenities);
        LuxuryBusReservationThread luxuryThread = new LuxuryBusReservationThread(luxuryBus);

        System.out.println("\nEnter Express Bus Details:");
        System.out.print("Brand: ");
        String expressBrand = scanner.nextLine();
        System.out.print("Model: ");
        String expressModel = scanner.nextLine();
        System.out.print("Fare: Rs. ");
        float expressFare = scanner.nextFloat();
        System.out.print("Available Seats: ");
        int expressAvailableSeats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Route: ");
        String expressRoute = scanner.nextLine();

        ExpressBus expressBus = new ExpressBus(expressBrand, expressModel, expressFare, expressAvailableSeats,
                expressRoute);
        ExpressBusReservationThread expressThread = new ExpressBusReservationThread(expressBus);

        luxuryThread.start();

        try {
            luxuryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        expressThread.start();
    }
}
