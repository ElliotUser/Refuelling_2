package robot.mr.refuelling;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final float MAX_FUEL_IN_ONE_WING = 3915; //+48 (3896)
    public final float MAX_FUEL_IN_CENTRAL_TANK = 13066;
    public final float TOTAL_FUEL = 20800; //2896
    public final float TOTAL_FUEL_MAX = 20896; // возможно ввести, но нужно отнять 96 и разделить поровно по крыльевым бакам

    EditText rightAmountInKilograms;
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
    TextView centralAircraftTank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим элементы
        rightAmountInKilograms = findViewById(R.id.rightAmountInKg);
        theRemainingFuelInTheLeftWing = findViewById(R.id.theRemainingFuelInTheLeftWing);
        theRemainingFuelInTheRightWing = findViewById(R.id.theRemainingFuelInTheRightWing);
        fuelRemainingInTheCentralTank = findViewById(R.id.fuelRemainingInTheCentralTank);
        densityID = findViewById(R.id.densityID);

        buttonResult = findViewById(R.id.buttonResult);
        buttonClear = findViewById(R.id.buttonClear);

        theRemainingFuelInKilograms = findViewById(R.id.theRemainingFuelInKilograms);
        fuelRemainingInLiters = findViewById(R.id.fuelRemainingInLiters);
        theAmountOfFuelToRefuelInLiters = findViewById(R.id.theAmountOfFuelToRefuelInLiters);
        theAmountOfFuelToRefuelInKg = findViewById(R.id.theAmountOfFuelToRefuelInKg);

        leftWingTank = findViewById(R.id.leftWing);
        rightWingTank = findViewById(R.id.rightWing);
        centralAircraftTank = findViewById(R.id.centralTank);

        //прописываем обработчик
        buttonResult.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        //TODO мне не нравятся имена переменн, исправить обязательно!
        //todo исправить имя всех переменных, так как они очень похожи с объектами
        float rightAmountInKg = 0  ;
        float leftWing = 0;
        float centralTank = 0;
        float rightWing = 0;
        float density = 0;

        float resultRemainingFuelInKilograms = 0;
        float resultRemainingFuelInLiters = 0;
        float resultInKg = 0;
        float resultInLiters = 0;

        EmptyCell emptyCell = new EmptyCell(theRemainingFuelInTheLeftWing,leftWing);
        EmptyCell emptyCell1 = new EmptyCell(fuelRemainingInTheCentralTank,centralTank);
        EmptyCell emptyCell2 = new EmptyCell(theRemainingFuelInTheRightWing,rightWing);
        EmptyCell emptyCell3 = new EmptyCell(rightAmountInKilograms,rightAmountInKg);
        EmptyCell emptyCell4 = new EmptyCell(densityID,density);

        //todo выбрать имя переменным(проверка на пустоту)
        leftWing = emptyCell.checkForEmpty();
        centralTank = emptyCell1.checkForEmpty();
        rightWing = emptyCell2.checkForEmpty();
        rightAmountInKg = emptyCell3.checkForEmpty();
        density = emptyCell4.checkForEmpty();

        DistributionOfTheFuelInTheTanks boeing737 = new DistributionOfTheFuelInTheTanks(MAX_FUEL_IN_ONE_WING,rightAmountInKg);
        CalculationOfFuel calculationBoeing737 = new CalculationOfFuel(leftWing,centralTank,rightWing,density,rightAmountInKg);


        switch(v.getId()){
            case R.id.buttonResult:
                if(rightAmountInKg < 1 || rightAmountInKg > TOTAL_FUEL_MAX) {
                    resultRemainingFuelInLiters = 0;
                    resultRemainingFuelInKilograms = 0;
                    resultInKg = 0;
                    resultInLiters = 0;

                    leftWing = 0;
                    rightWing = 0;
                    centralTank = 0;
                    densityID.setText("");
                    rightAmountInKilograms.setText("");

                    AlertDialog.Builder windowForRightAmountInKg = new AlertDialog.Builder(MainActivity.this);
                    windowForRightAmountInKg.setTitle("Некоректный ввод данных!").setIcon(R.drawable.cross).setMessage("Введите требуемое количество топлива!(Max = 20896 kg)").setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertForRightAmountInKg = windowForRightAmountInKg.create();
                    alertForRightAmountInKg.show();

                }else if(density == 0 || density <= 0.699 || density > 0.9) {
                    resultRemainingFuelInLiters = 0;
                    resultRemainingFuelInKilograms = 0;
                    resultInKg = 0;
                    resultInLiters = 0;

                    leftWing = 0;
                    rightWing = 0;
                    centralTank = 0;

                    densityID.setText("");

                    AlertDialog.Builder windowForDensity = new AlertDialog.Builder(MainActivity.this);
                    windowForDensity.setTitle("Некорректный ввод данных!").setIcon(R.drawable.cross).setMessage("Введите плотность в диапазоне от 0.7 до 0.9!").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertForDensity = windowForDensity.create();
                    alertForDensity.show();

                }else if(leftWing < 0 || leftWing > MAX_FUEL_IN_ONE_WING) {
                    resultRemainingFuelInLiters = 0;
                    resultRemainingFuelInKilograms = 0;
                    resultInKg = 0;
                    resultInLiters = 0;

                    leftWing = 0;
                    rightWing = 0;
                    centralTank = 0;
                    theRemainingFuelInTheLeftWing.setText("");


                    AlertDialog.Builder windowLeftWing = new AlertDialog.Builder(MainActivity.this);
                    windowLeftWing.setTitle("Некорректный ввод данных!").setIcon(R.drawable.cross).setMessage("Максимальная вместимость топлива в левом баке 3915 kg.").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertLeftWing = windowLeftWing.create();
                    alertLeftWing.show();

                }else if(rightWing < 0 || rightWing > MAX_FUEL_IN_ONE_WING) {
                    resultRemainingFuelInLiters = 0;
                    resultRemainingFuelInKilograms = 0;
                    resultInKg = 0;
                    resultInLiters = 0;

                    leftWing = 0;
                    rightWing = 0;
                    centralTank = 0;

                    theRemainingFuelInTheRightWing.setText("");

                    AlertDialog.Builder windowRightWing = new AlertDialog.Builder(MainActivity.this);
                    windowRightWing.setTitle("Некорректный ввод данных!").setIcon(R.drawable.cross).setMessage("Максимальная вместимость топлива в правом баке 3915 kg.").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertRightWing = windowRightWing.create();
                    alertRightWing.show();

                }else if(centralTank < 0 || centralTank > 13066) {
                    resultRemainingFuelInLiters = 0;
                    resultRemainingFuelInKilograms = 0;
                    resultInKg = 0;
                    resultInLiters = 0;

                    leftWing = 0;
                    rightWing = 0;
                    centralTank = 0;

                    fuelRemainingInTheCentralTank.setText("");
                    AlertDialog.Builder windowCentralTank = new AlertDialog.Builder(MainActivity.this);
                    windowCentralTank.setTitle("Некорректный ввод данных!").setIcon(R.drawable.cross).setMessage("Максимальная вместимость топлива в центральном баке 13066 kg.").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertCentralTank = windowCentralTank.create();
                    alertCentralTank.show();

                }else if(rightAmountInKg >= 20767 && rightAmountInKg <= 20896){

                            resultRemainingFuelInKilograms = calculationBoeing737.resultRemainingFuelInKilograms();
                            resultRemainingFuelInLiters = calculationBoeing737.resultRemainingFuelInLiters();
                            resultInKg = calculationBoeing737.resultInKg();
                            resultInLiters = calculationBoeing737.resultInLiters();

                            float excessFuel = (rightAmountInKg-20766)/2;
                            leftWing = 3850+excessFuel;
                            rightWing = 3850+excessFuel;
                            centralTank = 13066;
                }else {

                    resultRemainingFuelInKilograms = calculationBoeing737.resultRemainingFuelInKilograms();
                    resultRemainingFuelInLiters = calculationBoeing737.resultRemainingFuelInLiters();
                    resultInKg = calculationBoeing737.resultInKg();
                    resultInLiters = calculationBoeing737.resultInLiters();

                    boeing737.distributeFuelInTanks();
                    leftWing = boeing737.getWingLeftTank();
                    rightWing = boeing737.getWingRightTank();
                    centralTank = boeing737.getCentralTank();

                }
                break;

            case R.id.buttonClear:

                rightAmountInKilograms.setText("");
                theRemainingFuelInTheRightWing.setText("");
                fuelRemainingInTheCentralTank.setText("");
                theRemainingFuelInTheLeftWing.setText("");
                densityID.setText("");

                resultRemainingFuelInKilograms = 0;
                resultRemainingFuelInLiters = 0;
                resultInKg = 0;
                resultInLiters = 0;
                leftWing = 0;
                rightWing = 0;
                centralTank = 0;

                leftWingTank.setText("");
                rightWingTank.setText("");
                centralAircraftTank.setText("");

                break;
                default:
                    break;
        }

        //строки вывода
        theRemainingFuelInKilograms.setText(resultRemainingFuelInKilograms+"");
        fuelRemainingInLiters.setText(resultRemainingFuelInLiters+"");
        theAmountOfFuelToRefuelInLiters.setText(resultInLiters+"");
        theAmountOfFuelToRefuelInKg.setText(resultInKg+"");

        leftWingTank.setText(leftWing+"");
        rightWingTank.setText(rightWing+"");
        centralAircraftTank.setText(centralTank+"");

    }
}



