package robot.mr.refuelling;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        leftWingTank = (TextView) findViewById(R.id.leftWing);
        rightWingTank = (TextView) findViewById(R.id.rightWing);
        centralWingTank = (TextView) findViewById(R.id.centralTank);

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

        float resultCentralTank = rightAmInKg;
        float resultRightTank=0;
        float resultLeftTank=0;

        //если количество требуемого топлива равно 0
        if(TextUtils.isEmpty(rightAmountInKg.getText().toString())){
            rightAmInKg = 0;
        }else
            rightAmInKg = Float.parseFloat(rightAmountInKg.getText().toString());


        //если пустой левый бак
        if(TextUtils.isEmpty(theRemainingFuelInTheLeftWing.getText().toString())){
            leftWing = 0;
        }else
            leftWing = Float.parseFloat(theRemainingFuelInTheLeftWing.getText().toString());


        //если пустой центральный бак
        if(TextUtils.isEmpty(fuelRemainingInTheCentralTank.getText().toString())){
            centralTank = 0;
        }else
            centralTank = Float.parseFloat(fuelRemainingInTheCentralTank.getText().toString());


        //если пустой правый бак
        if(TextUtils.isEmpty(theRemainingFuelInTheRightWing.getText().toString())){
            rightWing = 0;
        }else
            rightWing = Float.parseFloat(theRemainingFuelInTheRightWing.getText().toString());

//        //Если плотность не введина вывести окно с предупреждением."УКАЖИТЕ ПЛОТНОСТЬ ТОПЛИВА!"
//        if(TextUtils.isEmpty(densityID.getText().toString())){
//            Dialog dialog = new Dialog(this);
//
//            dialog.setContentView(R.layout.custom_dialog);
//            dialog.setTitle("Custom Alert Dialog");
//
//            final EditText editText = (EditText) dialog.findViewById(R.id.editText);
//            Button btnSave          = (Button) dialog.findViewById(R.id.save);
//            Button btnCancel        = (Button) dialog.findViewById(R.id.cancel);
//            dialog.show();
//        }
        if(TextUtils.isEmpty(densityID.getText().toString())){
            denst = 0;
        }else
            denst = Float.parseFloat(densityID.getText().toString());


        switch(v.getId()){
            case R.id.buttonResult:
                resultRemainingFuelInKilograms = leftWing+centralTank+rightWing;
                resultRemainingFuelInLiters = (leftWing+centralTank+rightWing)/denst;
                resultInKg = (rightAmInKg-(leftWing+centralTank+rightWing));
                resultInLiters = (rightAmInKg-(leftWing+centralTank+rightWing))/denst;

                //Делим на все баки(в Boeing 737 в крыло вмещается 3780 кг топлива, а макс = 21000 кг
                // для всего самолета.)
                resultCentralTank = rightAmInKg;
                resultRightTank=0;
                resultLeftTank=0;

                for(float i = 0; i <=resultCentralTank ; i++) {
                    if(i==7560 && resultCentralTank >7560){
                        resultLeftTank=i/2;
                        resultRightTank=resultLeftTank;
                        resultCentralTank=resultCentralTank-i;
                    }else if(i==7560 && resultCentralTank == 7560){
                        resultLeftTank=i/2;
                        resultRightTank=resultLeftTank;
                        resultCentralTank=0;
                    }else if(resultCentralTank<7560) {
                        resultLeftTank = resultCentralTank / 2;
                        resultRightTank = resultLeftTank;
                        resultCentralTank = 0;
                    }
                }
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

        //ОБРАТИТЬ ВНИМАНИЕ ЧТО ДАННЫЙ РАСЧЕТ ИСПОЛЬЗУЕТСЯ ДЛЯ ОПРЕДЕЛЕННОГО САМОЛЕТА.(ВЫВЕСИТИ В ОТДЕЛЬНЫЙ КЛАСС?????)
        //Выводим в килограммах для всех баков в самолете
        leftWingTank.setText(resultLeftTank+"");
        rightWingTank.setText(resultRightTank+"");
        centralWingTank.setText(resultCentralTank+"");

    }
}



