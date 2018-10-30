package robot.mr.refuelling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        EditText rightAmountInKg = (EditText) findViewById(R.id.rightAmountInKg);
        EditText theRemainingFuelInTheRightWing = (EditText) findViewById(R.id.theRemainingFuelInTheRightWing);
        EditText fuelRemainingInTheCentralTank = (EditText) findViewById(R.id.fuelRemainingInTheCentralTank);
        EditText theRemainingFuelInTheLeftWing = (EditText) findViewById(R.id.theRemainingFuelInTheLeftWing);
        EditText densityID = (EditText) findViewById(R.id.densityID);

        TextView theRemainingFuelInKilograms = (TextView) findViewById(R.id.theRemainingFuelInKilograms);
        TextView fuelRemainingInLiters = (TextView) findViewById(R.id.fuelRemainingInLiters);
        TextView theAmountOfFuelToRefuelInLiters = (TextView) findViewById(R.id.theAmountOfFuelToRefuelInLiters);


        int rightAmInKg = Integer.parseInt(rightAmountInKg.getText().toString());
        int leftWing = Integer.parseInt(theRemainingFuelInTheLeftWing.getText().toString());
        int centrTank = Integer.parseInt(fuelRemainingInTheCentralTank.getText().toString());
        int rightWing = Integer.parseInt(theRemainingFuelInTheRightWing.getText().toString());
        double denst = Double.parseDouble(densityID.getText().toString());

        double requiredAmountOfFuel = (rightAmInKg-(leftWing+centrTank+rightWing))/denst;
        theAmountOfFuelToRefuelInLiters.setText(Double.toString(requiredAmountOfFuel));

        int remainingKg = leftWing+centrTank+rightWing;
        theRemainingFuelInKilograms.setText(Integer.toString(remainingKg));

        double remainingLitr = (leftWing+centrTank+rightWing)/denst;
        fuelRemainingInLiters.setText(Double.toString(remainingLitr));




    }
}
