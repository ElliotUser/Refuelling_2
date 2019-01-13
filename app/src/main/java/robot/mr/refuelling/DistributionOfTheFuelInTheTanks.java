package robot.mr.refuelling;

public class DistributionOfTheFuelInTheTanks {

    private float maxFuelInAircraft;//тут указывается количество топлива в центральном баке.передать значение через аргумент
    private float centralTank;
    private float wingRightTank;
    private float wingLeftTank;
    private float sumFuelWings;

    DistributionOfTheFuelInTheTanks(float maxFuelInOneTankWing, float maxFuelInAircraft){
        sumFuelWings = maxFuelInOneTankWing*2;
        this.maxFuelInAircraft = maxFuelInAircraft;
    }

    public void distributeFuelInTanks(){
        for(float i = 0; i <=maxFuelInAircraft ; i++) {
            if(i==sumFuelWings && maxFuelInAircraft >sumFuelWings){
                wingRightTank=i/2;
                wingLeftTank=wingRightTank;
                centralTank=maxFuelInAircraft-i;
            }else if(i==sumFuelWings && maxFuelInAircraft == sumFuelWings){
                wingRightTank=i/2;
                wingLeftTank = wingRightTank;
                centralTank=0;
            }else if(maxFuelInAircraft<sumFuelWings) {
                wingRightTank = maxFuelInAircraft / 2;
                wingLeftTank = wingRightTank;
                centralTank = 0;
            }
        }
    }

    public float getMaxFuelInAircraft() {
        return maxFuelInAircraft;
    }

    public float getWingRightTank() {
        return wingRightTank;
    }

    public float getWingLeftTank() {
        return wingLeftTank;
    }

    public float getSumFuelWings() {
        return sumFuelWings;
    }

    public float getCentralTank() {
        return centralTank;
    }
}
