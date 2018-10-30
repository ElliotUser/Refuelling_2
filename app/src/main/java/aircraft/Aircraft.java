package aircraft;

public class Aircraft {
    private String aircraftType;
    private double fuelFlight;
    private double leftWing;
    private double rightWing;
    private double centralTank;
    private double fuelDensity;

    public Aircraft(String aircraftType, double fuelFlight,double leftWing, double rightWing, double centralTank) {
        this.aircraftType = aircraftType;
        this.fuelFlight = fuelFlight;
        this.leftWing = leftWing;
        this.rightWing = rightWing;
        this.centralTank = centralTank;
    }

    public Aircraft(String aircraftType, double leftWing, double rightWing) {
        this.aircraftType = aircraftType;
        this.leftWing = leftWing;
        this.rightWing = rightWing;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public double getLeftWing() {
        return leftWing;
    }

    public double getRightWing() {
        return rightWing;
    }

    public double getCentralTank() {
        return centralTank;
    }

    public double getFuelFlight() {
        return fuelFlight;
    }

    public double remainingFuelInTheTanksKg(){
        return leftWing+centralTank+rightWing;
    }

    public double fuelForFillingInTanksKg(){
        return fuelFlight-(leftWing+centralTank+rightWing);
    }

    public double fuelForFillingInTanksInLiters(double fuelDensity){
        return (fuelFlight-(leftWing+centralTank+rightWing))/fuelDensity;
    }



}
