public class Ticket {

    private String plateNumber;
    private int parkingIndex;

    public Ticket(String plateNumber, int parkingIndex){
        this.plateNumber = plateNumber;
        this.parkingIndex = parkingIndex;
    }

    public String getPlateNumber(){
        return this.plateNumber;
    }

    public int getParkingIndex(){
        return this.parkingIndex;
    }

}
