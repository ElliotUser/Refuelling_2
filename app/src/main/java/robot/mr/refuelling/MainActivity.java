package robot.mr.refuelling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import robot.mr.refuelling.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText rightAmountInKg;
    EditText theRemainingFuelInTheRightWing;
    EditText fuelRemainingInTheCentralTank;
    EditText theRemainingFuelInTheLeftWing;
    EditText densityID;

    Button buttonResult;
    Button buttonClear;

    TextView theRemainingFuelInKilograms;
    TextView fuelRemainingInLiters;
    TextView theAmountOfFuelToRefuelInLiters;
    TextView theAmountOfFuelToRefuelInKg;

    TextView leftWingTank;
    TextView rightWingTank;
    TextView centralWingTank;

    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим элементы
        rightAmountInKg = (EditText) findViewById(R.id.rightAmountInKg);
        theRemainingFuelInTheLeftWing = (EditText) findViewById(R.id.theRemainingFuelInTheLeftWing);
        theRemainingFuelInTheRightWing = (EditText) findViewById(R.id.theRemainingFuelInTheRightWing);
        fuelRemainingInTheCentralTank = (EditText) findViewById(R.id.fuelRemainingInTheCentralTank);
        densityID = (EditText) findViewById(R.id.densityID);

        buttonResult = (Button)findViewById(R.id.buttonResult);
        buttonClear = (Button)findViewById(R.id.buttonClear);

        theRemainingFuelInKilograms = (TextView) findViewById(R.id.theRemainingFuelInKilograms);
        fuelRemainingInLiters = (TextView) findViewById(R.id.fuelRemainingInLiters);
        theAmountOfFuelToRefuelInLiters = (TextView) findViewById(R.id.theAmountOfFuelToRefuelInLiters);
        theAmountOfFuelToRefuelInKg = (TextView) findViewById(R.id.theAmountOfFuelToRefuelInKg);
        leftWingTank = (TextView) findViewById(R.id.leftWing);;
        rightWingTank = (TextView) findViewById(R.id.rightWing);;
        centralWingTank = (TextView) findViewById(R.id.centralTank);;

        //прописываем обработчик
        buttonResult.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //TODO Auto-generated method stub
        float rightAmInKg = 0  ;
        float leftWing = 0;
        float centralTank = 0;
        float rightWing = 0;
        float denst = 0;

        float resultRemainingFuelInKilograms = 0;
        float resultRemainingFuelInLiters = 0;
        float resultInKg = 0;
        float resultInLiters = 0;

        float resultLeftTank = 0;
        float resultCentralTank = 0;
        float resultRightTank = 0;

        //Еще поделить на все баки!!!

        //Проверяем поля на пустоту
        if(TextUtils.isEmpty(rightAmountInKg.getText().toString()) ||
                TextUtils.isEmpty(theRemainingFuelInTheLeftWing.getText().toString()) ||
                TextUtils.isEmpty(fuelRemainingInTheCentralTank.getText().toString()) ||
                TextUtils.isEmpty(theRemainingFuelInTheRightWing.getText().toString()) ||
                TextUtils.isEmpty(densityID.getText().toString())){
            return;
        }

        //читаем EditText и заполняем переменные числами
        rightAmInKg = Float.parseFloat(rightAmountInKg.getText().toString());
        leftWing = Float.parseFloat(theRemainingFuelInTheLeftWing.getText().toString());;
        centralTank = Float.parseFloat(fuelRemainingInTheCentralTank.getText().toString());;
        rightWing = Float.parseFloat(theRemainingFuelInTheRightWing.getText().toString());;
        denst = Float.parseFloat(densityID.getText().toString());;


        switch(v.getId()){
            case R.id.buttonResult:
                resultRemainingFuelInKilograms = leftWing+centralTank+rightWing;
                resultRemainingFuelInLiters = (leftWing+centralTank+rightWing)/denst;
                resultInKg = (rightAmInKg-(leftWing+centralTank+rightWing));
                resultInLiters = (rightAmInKg-(leftWing+centralTank+rightWing))/denst;

                resultLeftTank = (float)(resultInKg*0.18);
                resultRightTank = (float)(resultInKg*0.18);
                resultCentralTank = (float) (resultInKg-(resultLeftTank+resultRightTank));
                break;

            case R.id.buttonClear:
                rightAmountInKg.setText("");
                theRemainingFuelInTheRightWing.setText("");
                fuelRemainingInTheCentralTank.setText("");
                theRemainingFuelInTheLeftWing.setText("");
                densityID.setText("");

                resultRemainingFuelInKilograms = 0;
                resultRemainingFuelInLiters = 0;
                resultInKg = 0;
                resultInLiters = 0;

                leftWingTank.setText("");
                rightWingTank.setText("");
                centralWingTank.setText("");
                break;
                default:
                    break;
        }

        //строки вывода
        theRemainingFuelInKilograms.setText(resultRemainingFuelInKilograms+"");
        fuelRemainingInLiters.setText(resultRemainingFuelInLiters+"");
        theAmountOfFuelToRefuelInLiters.setText(resultInLiters+"");
        theAmountOfFuelToRefuelInKg.setText(resultInKg+"");

        leftWingTank.setText(resultLeftTank+"");
        rightWingTank.setText(resultRightTank+"");
        centralWingTank.setText(resultCentralTank+"");

    }
}



