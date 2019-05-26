import java.util.Objects;

public class Customer implements Driver {

    Car car;
    Ticket ticket;
    String name;
    public Customer(Car car, Ticket ticket, String name){
        this.car = car;
        this.ticket = ticket;
        this.name = name;
    }

    @Override
    public void parkCar(ParkingLots parking){
        System.out.println(this.name + " driving the car to parking...");
        try{
            this.ticket = parking.receiveTheCar(car);
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
    public void pickUpCar(ParkingLots parking) throws Exception {
        Car car = parking.returnTheCar(ticket);
        this.car = car;
        if(!Objects.isNull(car))
            System.out.println("Customer is picking up the car... with plate number ->" + car.getPlateNumber());
    }


}
