import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class SeniorParkingBoyTest {

    ParkingLots parking;

    @Before
    public void setUp(){
        parking = new ParkingLots(2,2);
    }


    @Test
    public void given_SeniorParkingBoy_park_car_when_parking_is_availability_then_get_ticket() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(carOne, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);
        Assert.assertTrue(Objects.nonNull(SeniorParkingBoyOne.ticket));
    }

    @Test
    public void given_SeniorParkingBoy_park_car_when_parking_is_availability_but_plate_duplicated_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(carOne, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);
        SeniorParkingBoyOne = new SeniorParkingBoy(carOne, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);
        Assert.assertTrue(Objects.isNull(SeniorParkingBoyOne.ticket));
    }

    @Test
    public void given_SeniorParkingBoy_park_car_when_parking_is_not_availability_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(carOne, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Car carTwo = new Car("b");
        SeniorParkingBoyOne = new SeniorParkingBoy(carTwo, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Car carThree = new Car("c");
        SeniorParkingBoyOne = new SeniorParkingBoy(carThree, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Car carFour = new Car("d");
        SeniorParkingBoyOne = new SeniorParkingBoy(carFour, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Car carFive = new Car("e");
        SeniorParkingBoyOne = new SeniorParkingBoy(carFive, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Car carFive1 = new Car("e");
        SeniorParkingBoyOne = new SeniorParkingBoy(carFive1, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);

        Assert.assertTrue(!parking.isFull());

    }

    @Test
    public void given_SeniorParkingBoy_pickup_car_when_ticket_is_correct_then_get_car() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(carOne, null, "SeniorParkingBoyOne");
        SeniorParkingBoyOne.parkCar(parking);
        SeniorParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null != SeniorParkingBoyOne.car);
        Assert.assertTrue(carOne.getPlateNumber().equals(SeniorParkingBoyOne.car.getPlateNumber()));


    }

    @Test
    public void given_SeniorParkingBoy_pickup_car_when_ticket_is_incorrect_parkingIndex_correct_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyMrRight = new SeniorParkingBoy(carOne,  null, "SeniorParkingBoyMrRight");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(null,  new Ticket("a", 1), "bad guy");
        SeniorParkingBoyMrRight.parkCar(parking);
        SeniorParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == SeniorParkingBoyOne.car);
    }

    @Test
    public void given_SeniorParkingBoy_pickup_car_when_ticket_is_correct_parkingIndex_incorrect_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyMrRight = new SeniorParkingBoy(carOne,  null, "SeniorParkingBoyMrRight");
        SeniorParkingBoy SeniorParkingBoyOne = new SeniorParkingBoy(null,  new Ticket("abc", 1), "bad guy");
        SeniorParkingBoyMrRight.parkCar(parking);
        SeniorParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == SeniorParkingBoyOne.car);
    }

    @Test
    public void given_SeniorParkingBoy_find_correct_parkingIndex_when_there_is_two_parking_then_return_index_of_most_slots_parkinglot() {
        Car carOne = new Car("a");
        SeniorParkingBoy SeniorParkingBoyMrRight = new SeniorParkingBoy(carOne,  null, "SeniorParkingBoyMrRight");
        SeniorParkingBoyMrRight.parkCar(parking);

        Car carTwo = new Car("b");
        SeniorParkingBoyMrRight = new SeniorParkingBoy(carTwo,  null, "SecondCarBoy");

        Assert.assertTrue(1 == SeniorParkingBoyMrRight.findParkingLocation(this.parking));
    }

}



