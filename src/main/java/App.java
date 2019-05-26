public class App {

    public static void main(String [] args) throws Exception {

        ParkingLots parking = new ParkingLots(2,1);
        Car carOne = new Car("a");
        Car carTwo = new Car("b");
        Car carThree = new Car("c");
        Car carFour = new Car("d");
        Car carFive = new Car("e");

        Customer customerOne = new Customer(carOne, null, "customerOne");
        Customer customerTwo = new Customer(carTwo, null, "customerTwo");
        Customer customerThree = new Customer(carThree, null, "customerThree");
        Customer customerFour = new Customer(carFour, null, "customerFour");
        Customer customerFive = new Customer(carFive, null, "customerFive");

        customerOne.parkCar(parking);
        customerTwo.parkCar(parking);
        customerThree.parkCar(parking);
        customerFour.parkCar(parking);
        customerFive.parkCar(parking);

        customerOne.pickUpCar(parking);
        customerFive.pickUpCar(parking);
    }
}
