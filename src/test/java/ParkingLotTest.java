import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingLotTest {

    ParkingLots parking;

    @Before
    public void setUp(){
        parking = new ParkingLots(2,1);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void given_receive_car_when_parking_is_availability_then_parkinglot_generate_ticket() throws Exception {
        Car carOne = new Car("a");
        Assert.assertTrue(null != parking.receiveTheCar(carOne, 1));

    }

    @Test
    public void given_receive_car_when_parking_is_availability_but_there_is_duplicated_platenumber_car_in_parking_then_parkinglot_will_not_generate_ticket() throws Exception {
        Car carOne = new Car("a");
        Car carTwo = new Car("a");
        parking.receiveTheCar(carOne, 1);
        Assert.assertTrue(null == parking.receiveTheCar(carTwo, 1));
    }

    @Test(expected = Exception.class)
    public void given_receive_car_when_parking_is_availability_but_parking_is_full_then_parkinglot_will_not_generate_ticket() throws Exception {

        Car carOne = new Car("a");
        Car carTwo = new Car("b");
        Car carThree = new Car("c");
        parking.receiveTheCar(carOne,1);
        parking.receiveTheCar(carTwo,2);
        parking.receiveTheCar(carThree,2);
    }

    @Test
    public void given_car_left_when_correct_ticket_then_parkinglot_generate_ticket_and_parking_lot_size_minus_one() throws Exception {
        Car carOne = new Car("a");
        Ticket ticket = parking.receiveTheCar(carOne, 1);
        int afterParkingCapcity = this.parking.getMultipleParking().get(ticket.getParkingIndex()).keySet().size();
        parking.returnTheCar(ticket);
        int afterPickUpCarCapcity = this.parking.getMultipleParking().get(ticket.getParkingIndex()).keySet().size();
        Assert.assertTrue(afterParkingCapcity == afterPickUpCarCapcity + 1);

    }
}
