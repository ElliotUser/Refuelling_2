package robot.mr.refuelling;

public class CalculationOfFuel {
    private float leftWing;
    private float centralTank;
    private float rightWing;
    private float density;
    private float rightAmInKg;

    CalculationOfFuel(float leftWing,float centralTank,float rightWing, float density,float rightAmInKg){
        this.leftWing = leftWing;
        this.centralTank = centralTank;
        this.rightWing = rightWing;
        this.density = density;
        this.rightAmInKg = rightAmInKg;
    }


    public float resultRemainingFuelInKilograms(){
        return leftWing+centralTank+rightWing;
    }

    public float resultRemainingFuelInLiters(){
        return (leftWing+centralTank+rightWing)/density;
    }

    public float resultInKg(){
        return (rightAmInKg-(leftWing+centralTank+rightWing));
    }

    public float resultInLiters(){
        return (rightAmInKg-(leftWing+centralTank+rightWing))/density;
    }

}
