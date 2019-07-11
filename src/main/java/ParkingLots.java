import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLots {

    private Map<Integer, Map<String, Car>> multipleParking;
    private int size = 0;
    private int length = 0;
    private boolean isFull = false;

    public ParkingLots(int size, int length) {
        this.multipleParking = Maps.newConcurrentMap();
        this.size = size;
        this.length = length;
        generateParking(this.size, this.length);
    }

    private void generateParking(int num, int length) {
        for (int i = 0; i < num; i++) {
            Map<String, Car> subParks = new HashMap<>(length);
            multipleParking.putIfAbsent(i, subParks);
        }
    }

    public Ticket receiveTheCar(Car car, int number) throws Exception {
        if (checkAvailability(car)) {
            int parkingIndex = number;
            if (parkingIndex == -1) {
                this.isFull = true;
                throw new Exception("full");
            } else {
                this.multipleParking.get(parkingIndex).putIfAbsent(car.getPlateNumber(), car);
                return new Ticket(car.getPlateNumber(), parkingIndex);
            }
        }
        return null;
    }

    public Car returnTheCar(Ticket ticket) {
        if (checkTicket(ticket)) {
            Car car = new Car(this.multipleParking
                    .get(ticket.getParkingIndex())
                    .get(ticket.getPlateNumber())
                    .getPlateNumber());
            this.multipleParking.get(ticket.getParkingIndex()).remove(ticket.getPlateNumber());
            this.isFull = false;
            return car;
        } else {
            System.out.println("wrong ticket");
        }
        return null;

    }

    private boolean checkTicket(Ticket ticket) {
        if (!Objects.isNull(ticket))
            return this.multipleParking.get(ticket.getParkingIndex()).keySet().stream()
                    .filter(Objects::nonNull)
                    .anyMatch(x -> x.equals(ticket.getPlateNumber()));
        else
            return false;
    }


    private boolean checkAvailability(Car car) {
        for (Map.Entry<Integer, Map<String, Car>> entry : this.multipleParking.entrySet()) {
            for (Map.Entry<String, Car> subEntry : entry.getValue().entrySet()) {
                if (subEntry.getKey().equals(car.getPlateNumber()))
                    return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        return isFull;
    }

    public Map<Integer, Map<String, Car>> getMultipleParking() {
        return multipleParking;
    }
}
