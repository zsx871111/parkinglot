import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Objects;

public class CustomerTest {
    ParkingLots parking;

    @Before
    public void setUp(){
        parking = new ParkingLots(2,1);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void given_customer_park_car_when_parking_is_availability_then_get_ticket() throws Exception {
        Car carOne = new Car("a");
        Customer customerOne = new Customer(carOne, null, "customerOne");
        customerOne.parkCar(parking);
        Assert.assertTrue(Objects.nonNull(customerOne.ticket));
    }

    @Test
    public void given_customer_park_car_when_parking_is_availability_but_plate_duplicated_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        Customer customerOne = new Customer(carOne, null, "customerOne");
        customerOne.parkCar(parking);
        Customer customerTwo = new Customer(carOne, null, "customerTwo");
        customerTwo.parkCar(parking);
        Assert.assertTrue(Objects.isNull(customerTwo.ticket));
    }

    @Test
    public void given_customer_park_car_when_parking_is_not_availability_then_no_ticket() throws Exception {
        Car carOne = new Car("a");
        Customer customerOne = new Customer(carOne, null, "customerOne");
        customerOne.parkCar(parking);
        Car carTwo = new Car("b");
        Customer customerTwo = new Customer(carTwo, null, "customerTwo");
        customerTwo.parkCar(parking);
        Car carThree = new Car("c");
        Customer customerThree = new Customer(carThree, null, "customerThree");

        customerThree.parkCar(parking);
        Assert.assertTrue(parking.isFull());

    }

    @Test
    public void given_customer_pickup_car_when_ticket_is_correct_then_get_car() throws Exception {
        Car carOne = new Car("a");
        Customer customerOne = new Customer(carOne, null, "customerOne");
        customerOne.parkCar(parking);
        customerOne.pickUpCar(parking);
        Assert.assertTrue(null != customerOne.car);
        Assert.assertTrue(carOne.getPlateNumber().equals(customerOne.car.getPlateNumber()));


    }

    @Test
    public void given_customer_pickup_car_when_ticket_is_incorrect_parkingIndex_correct_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        Customer customerMrRight = new Customer(carOne,  null, "customerMrRight");
        Customer customerOne = new Customer(null,  new Ticket("a", 1), "bad guy");
        customerMrRight.parkCar(parking);
        customerOne.pickUpCar(parking);
        Assert.assertTrue(null == customerOne.car);
    }

    @Test
    public void given_customer_pickup_car_when_ticket_is_correct_parkingIndex_incorrect_platenumber_then_no_car() throws Exception {
        Car carOne = new Car("a");
        Customer customerMrRight = new Customer(carOne,  null, "customerMrRight");
        Customer customerOne = new Customer(null,  new Ticket("abc", 0), "bad guy");
        customerMrRight.parkCar(parking);
        customerOne.pickUpCar(parking);
        Assert.assertTrue(null == customerOne.car);
    }

}
