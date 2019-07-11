import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SeniorParkingBoy extends GradatedParkingBoy {
    public SeniorParkingBoy(Car car, Ticket ticket, String name) {
        super(car, ticket, name);
    }

    @Override
    public int findParkingLocation(ParkingLots parking){
        int sizeOfEachSmallParking = parking.getMultipleParking().keySet().size();

        List<SeniorParkingBoy.SubParking> freeParkingNumber = parking.getMultipleParking().entrySet().stream()
                .filter(x -> x.getValue().size() < sizeOfEachSmallParking)
                .map(k -> new SeniorParkingBoy.SubParking(k.getKey(), sizeOfEachSmallParking - k.getValue().size()))
                .collect(Collectors.toList());

        SeniorParkingBoy.SubParking subParking = freeParkingNumber.stream()
                .max((o1, o2) -> {
                    BigDecimal tempOne = new BigDecimal(o1.getFreeSize()).divide(new BigDecimal(sizeOfEachSmallParking));
                    BigDecimal tempTwo = new BigDecimal(o2.getFreeSize()).divide(new BigDecimal(sizeOfEachSmallParking));

                    if(tempOne.compareTo(tempTwo) >= 0)
                        return 1;
                    else
                        return 0;

                }).get();

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
