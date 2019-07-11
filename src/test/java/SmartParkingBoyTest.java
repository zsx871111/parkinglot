import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class SmartParkingBoyTest {
    ParkingLots parking;

    @Before
    public void setUp(){
        parking = new ParkingLots(2,2);
    }
    

    @Test
    public void given_smartParkingBoy_park_car_when_parking_is_availability_then_get_ticket() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(carOne, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);
        Assert.assertTrue(Objects.nonNull(SmartParkingBoyOne.ticket));
    }

    @Test
    public void given_smartParkingBoy_park_car_when_parking_is_availability_but_plate_duplicated_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(carOne, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);
        SmartParkingBoyOne = new SmartParkingBoy(carOne, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);
        Assert.assertTrue(Objects.isNull(SmartParkingBoyOne.ticket));
    }

    @Test
    public void given_smartParkingBoy_park_car_when_parking_is_not_availability_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(carOne, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Car carTwo = new Car("b");
        SmartParkingBoyOne = new SmartParkingBoy(carTwo, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Car carThree = new Car("c");
        SmartParkingBoyOne = new SmartParkingBoy(carThree, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Car carFour = new Car("d");
        SmartParkingBoyOne = new SmartParkingBoy(carFour, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Car carFive = new Car("e");
        SmartParkingBoyOne = new SmartParkingBoy(carFive, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Car carFive1 = new Car("e");
        SmartParkingBoyOne = new SmartParkingBoy(carFive1, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);

        Assert.assertTrue(!parking.isFull());

    }

    @Test
    public void given_smartParkingBoy_pickup_car_when_ticket_is_correct_then_get_car() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(carOne, null, "SmartParkingBoyOne");
        SmartParkingBoyOne.parkCar(parking);
        SmartParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null != SmartParkingBoyOne.car);
        Assert.assertTrue(carOne.getPlateNumber().equals(SmartParkingBoyOne.car.getPlateNumber()));


    }

    @Test
    public void given_smartParkingBoy_pickup_car_when_ticket_is_incorrect_parkingIndex_correct_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyMrRight = new SmartParkingBoy(carOne,  null, "SmartParkingBoyMrRight");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(null,  new Ticket("a", 1), "bad guy");
        SmartParkingBoyMrRight.parkCar(parking);
        SmartParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == SmartParkingBoyOne.car);
    }

    @Test
    public void given_smartParkingBoy_pickup_car_when_ticket_is_correct_parkingIndex_incorrect_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyMrRight = new SmartParkingBoy(carOne,  null, "SmartParkingBoyMrRight");
        SmartParkingBoy SmartParkingBoyOne = new SmartParkingBoy(null,  new Ticket("abc", 1), "bad guy");
        SmartParkingBoyMrRight.parkCar(parking);
        SmartParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == SmartParkingBoyOne.car);
    }
    
    @Test
    public void given_smartParkingBoy_find_correct_parkingIndex_when_there_is_two_parking_then_return_index_of_most_slots_parkinglot() {
        Car carOne = new Car("a");
        SmartParkingBoy SmartParkingBoyMrRight = new SmartParkingBoy(carOne,  null, "SmartParkingBoyMrRight");
        SmartParkingBoyMrRight.parkCar(parking);

        Car carTwo = new Car("b");
        SmartParkingBoyMrRight = new SmartParkingBoy(carTwo,  null, "SecondCarBoy");

        Assert.assertTrue(1 == SmartParkingBoyMrRight.findParkingLocation(this.parking));
    }
}
