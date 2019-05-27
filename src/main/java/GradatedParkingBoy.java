import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GradatedParkingBoy implements Boy {

    Car car;
    Ticket ticket;
    String name;
    public GradatedParkingBoy(Car car, Ticket ticket, String name){
        this.car = car;
        this.ticket = ticket;
        this.name = name;
    }

    @Override
    public void parkCar(ParkingLots parking){
        System.out.println(this.name + " driving the car to parking...");
        try{
            int parkingLocationIndex = findParkingLocaition(parking);
            this.ticket = parking.receiveTheCar(car, parkingLocationIndex);
            if(Objects.isNull(ticket)){
                System.out.println(this.name + " driving the car to parking... fail");
            } else{
                this.car = null;
                System.out.println(this.name + " driving the car to parking... successfully");
            }
        } catch (Exception ex){
            System.out.println(this.name + " parking is full");
        }

    }

    @Override
    public void pickUpCar(ParkingLots parking) {
        Car car = parking.returnTheCar(ticket);
        this.car = car;
        if(!Objects.isNull(car))
            System.out.println("GradatedParkingBoy is picking up the car... with plate number ->" + car.getPlateNumber());
    }

    @Override
    public int findParkingLocaition(ParkingLots parking) {
        List<Integer> freeParkingNumber = parking.getMultipleParking().entrySet().stream()
                .filter(x -> x.getValue().size() < parking.getMultipleParking().keySet().size())
                .map(k -> k.getKey())
                .collect(Collectors.toList());

        return freeParkingNumber.size() > 0 ? freeParkingNumber.get(0) : -1;
    }


}
