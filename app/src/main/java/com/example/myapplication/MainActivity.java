package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result , inputnumbers;
    MaterialButton buttonAc , buttonC, buttonClose , buttonOpen , buttonDivide , buttonNine , buttonEight , buttonSeven , buttonMultiply, buttonSix, buttonFive , buttonFour , buttonThree , buttonTwo , buttonAddation , buttonOne , buttonMinus , buttonZero , buttonDot , buttonEqual ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result= findViewById(R.id.result);
        inputnumbers= findViewById(R.id.inputnumbers);
        assignId(buttonC , R.id.C);
        assignId(buttonClose , R.id.closebracket);
        assignId(buttonOpen , R.id.openbracketbuton);
        assignId(buttonAc , R.id.ac);
        assignId(buttonEqual , R.id.equal);
        assignId(buttonZero , R.id.zero);
        assignId(buttonOne , R.id.one);
        assignId(buttonTwo , R.id.two);
        assignId(buttonThree , R.id.three);
        assignId(buttonFour , R.id.four);
        assignId(buttonFive , R.id.five);
        assignId(buttonSix , R.id.six);
        assignId(buttonSeven , R.id.seven);
        assignId(buttonEight , R.id.eight);
        assignId(buttonNine , R.id.nine);
        assignId(buttonMultiply , R.id.multiply);
        assignId(buttonDivide , R.id.dividbutton);
        assignId(buttonAddation , R.id.pluse);
        assignId(buttonMinus , R.id.minus);
        assignId(buttonEqual , R.id.equal);
        assignId(buttonDot , R.id.point);
    }

    void assignId(MaterialButton btn , int id){
        btn= findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String btn = button.getText().toString();
        String numbersforcalculate = inputnumbers.getText().toString();

        if(btn.equals("AC")){
            inputnumbers.setText("");
            result.setText("0");
            return;
        }
        if(btn.equals("=")){
            inputnumbers.setText( result.getText());
            return;
        }
        if(btn.equals("C")){
            numbersforcalculate = numbersforcalculate.substring(0 , numbersforcalculate.length()-1);

        }
        else {
            numbersforcalculate = numbersforcalculate+btn;
        }

        inputnumbers.setText(numbersforcalculate);
        String finalresult = getresult(numbersforcalculate);
        if(!finalresult.equals("Err")){
            result.setText(finalresult);
        }

    }
    String getresult(String data){
    try {
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initStandardObjects();
        String finalresult = context.evaluateString(scriptable , data , "Javascript" , 1 ,null).toString();

       if (finalresult.equals(".0")) finalresult = finalresult.replace(".0", "");
        return  finalresult;

    }
    catch (Exception e){
        return "Err";
    }

    }
}