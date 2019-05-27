import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Objects;

public class GradatedParkingBoyTest {
    ParkingLots parking;

    @Before
    public void setUp(){
        parking = new ParkingLots(2,1);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void given_graduateParkingBoy_park_car_when_parking_is_availability_then_get_ticket() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyOne");
        gradatedParkingBoyOne.parkCar(parking);
        Assert.assertTrue(Objects.nonNull(gradatedParkingBoyOne.ticket));
    }

    @Test
    public void given_graduateParkingBoy_park_car_when_parking_is_availability_but_plate_duplicated_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyOne");
        gradatedParkingBoyOne.parkCar(parking);
        GradatedParkingBoy gradatedParkingBoyTwo = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyTwo");
        gradatedParkingBoyTwo.parkCar(parking);
        Assert.assertTrue(Objects.isNull(gradatedParkingBoyTwo.ticket));
    }

    @Test
    public void given_graduateParkingBoy_park_car_when_parking_is_not_availability_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyOne");
        gradatedParkingBoyOne.parkCar(parking);
        Car carTwo = new Car("b");
        GradatedParkingBoy gradatedParkingBoyTwo = new GradatedParkingBoy(carTwo, null, "gradatedParkingBoyTwo");
        gradatedParkingBoyTwo.parkCar(parking);
        Car carThree = new Car("c");
        GradatedParkingBoy gradatedParkingBoyThree = new GradatedParkingBoy(carThree, null, "gradatedParkingBoyThree");

        gradatedParkingBoyThree.parkCar(parking);
        Assert.assertTrue(parking.isFull());

    }

    @Test
    public void given_graduateParkingBoy_pickup_car_when_ticket_is_correct_then_get_car() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyOne");
        gradatedParkingBoyOne.parkCar(parking);
        gradatedParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null != gradatedParkingBoyOne.car);
        Assert.assertTrue(carOne.getPlateNumber().equals(gradatedParkingBoyOne.car.getPlateNumber()));


    }

    @Test
    public void given_graduateParkingBoy_pickup_car_when_ticket_is_incorrect_parkingIndex_correct_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyMrRight = new GradatedParkingBoy(carOne,  null, "gradatedParkingBoyMrRight");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(null,  new Ticket("a", 1), "bad guy");
        gradatedParkingBoyMrRight.parkCar(parking);
        gradatedParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == gradatedParkingBoyOne.car);
    }

    @Test
    public void given_graduateParkingBoy_pickup_car_when_ticket_is_correct_parkingIndex_incorrect_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        GradatedParkingBoy gradatedParkingBoyMrRight = new GradatedParkingBoy(carOne,  null, "gradatedParkingBoyMrRight");
        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(null,  new Ticket("abc", 1), "bad guy");
        gradatedParkingBoyMrRight.parkCar(parking);
        gradatedParkingBoyOne.pickUpCar(parking);
        Assert.assertTrue(null == gradatedParkingBoyOne.car);
    }

}
