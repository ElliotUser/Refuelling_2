package robot.mr.refuelling;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

//todo КЛАСС НАЗВАН КАК ТО НЕПРАВИЛЬНО(((НАЙТИ НОРМАЛЬНОЕ ИМЯ КЛАССА
public class EmptyCell {
    private TextView initializingСell;
    private String windowTitle;
    private String windowMessage;
    private Context context;
    //todo ОЧЕНЬ ПЛОХОЙ ВЫБОР ИМЕНИ ДЛЯ ПЕРЕМЕННОЙ value(((((ОБЯЗАТЕЛЬНО ИСПРАВИТЬ
    private float value;

    EmptyCell(TextView initializingСell, float value){
        this.initializingСell = initializingСell;
        this.value = value;
    }

    public float checkForEmpty(){
        if(TextUtils.isEmpty(initializingСell.getText().toString())){
            value = 0;
        }else
            value = Float.parseFloat(initializingСell.getText().toString());
        return value;
    }

    public TextView getInitializingСell() {
        return initializingСell;
    }

    public float getValue() {
        return value;
    }


}
