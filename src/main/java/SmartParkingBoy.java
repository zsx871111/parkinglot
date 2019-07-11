import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends GradatedParkingBoy {
    public SmartParkingBoy(Car car, Ticket ticket, String name) {
        super(car, ticket, name);
    }

    @Override
    public int findParkingLocation(ParkingLots parking){
        int sizeOfEachSmallParking = parking.getMultipleParking().keySet().size();

        List<SubParking> freeParkingNumber = parking.getMultipleParking().entrySet().stream()
                .filter(x -> x.getValue().size() < sizeOfEachSmallParking)
                .map(k -> new SubParking(k.getKey(), sizeOfEachSmallParking - k.getValue().size()))
                .collect(Collectors.toList());

        SubParking subParking = freeParkingNumber.stream()
                .max(Comparator.comparing(SubParking::getFreeSize)).get();

        return subParking.index;
    }

    class SubParking{
        int index;
        int freeSize;

        public SubParking(int index, int freeSize){
            this.index = index;
            this.freeSize = freeSize;
        }

        public int getFreeSize(){
            return this.freeSize;
        }

    }

}
