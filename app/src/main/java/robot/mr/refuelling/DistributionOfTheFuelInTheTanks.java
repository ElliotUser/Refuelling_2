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

            if(i <= 7830){ //если заправка меньеше или равна 7830,
                            // то делим все по крыльевым бакам 3915
                wingLeftTank = i/2;
                wingRightTank = wingLeftTank;
                centralTank = 0;
            }else if(i > 7830){ //если заправка привышает 7830, то присваеваем крыльевым бакам 3850
                                                              //и остаток присваеваем центральному баку
                wingLeftTank = 3850;
                wingRightTank = wingLeftTank;
                centralTank = i-7700;
            }else if(i >= 20767){ //если заправка превышает 20766, то делим остаток пополам и заливаем
                                                                 //в крылья
                wingLeftTank = 3850;
                wingRightTank = wingLeftTank;
                centralTank = 13066;
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
