public class App {

    public static void main(String [] args) throws Exception {

        ParkingLots parking = new ParkingLots(2,2);
        Car carOne = new Car("a");
        Car carTwo = new Car("b");
        Car carThree = new Car("c");
        Car carFour = new Car("d");
        Car carFive = new Car("e");

        GradatedParkingBoy gradatedParkingBoyOne = new GradatedParkingBoy(carOne, null, "gradatedParkingBoyOne");
        GradatedParkingBoy gradatedParkingBoyTwo = new GradatedParkingBoy(carTwo, null, "gradatedParkingBoyTwo");
        GradatedParkingBoy gradatedParkingBoyThree = new GradatedParkingBoy(carThree, null, "gradatedParkingBoyThree");
        GradatedParkingBoy gradatedParkingBoyFour = new GradatedParkingBoy(carFour, null, "gradatedParkingBoyFour");
        GradatedParkingBoy gradatedParkingBoyFive = new GradatedParkingBoy(carFive, null, "gradatedParkingBoyFive");

        gradatedParkingBoyOne.parkCar(parking);
        gradatedParkingBoyTwo.parkCar(parking);
        gradatedParkingBoyThree.parkCar(parking);
        gradatedParkingBoyFour.parkCar(parking);
        gradatedParkingBoyFive.parkCar(parking);

        gradatedParkingBoyOne.pickUpCar(parking);
        gradatedParkingBoyFive.pickUpCar(parking);
    }
}
