package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView Resulttv,Solutiontv;
    MaterialButton ButtonC,Buttonbracketleft,ButtonBractekright;
    MaterialButton ButtonDivide,ButtonMultiply,ButtonPlus,ButtonMinus,ButtonEquals;
    MaterialButton Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Button0;
    MaterialButton ButtonAC,ButtonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resulttv = findViewById(R.id.resultv);
        Solutiontv = findViewById(R.id.solution_tv);
        assignId(ButtonC,R.id.buttonc);
        assignId(Buttonbracketleft,R.id.left_bracket);
        assignId(ButtonBractekright,R.id.rightbracket);
        assignId(ButtonDivide,R.id.divide);
        assignId(ButtonMultiply,R.id.buttonmultiply);
        assignId(ButtonPlus,R.id.plus);
        assignId(ButtonMinus,R.id.buttonMinus);
        assignId(ButtonEquals,R.id.buttonisequalto);
        assignId(Button1,R.id.number1);
        assignId(Button2,R.id.number2);
        assignId(Button3,R.id.number3);
        assignId(Button4,R.id.number4);
        assignId(Button5,R.id.number5);
        assignId(Button6,R.id.number6);
        assignId(Button7,R.id.number7);
        assignId(Button8,R.id.number8);
        assignId(Button9,R.id.number9);
        assignId(Button0,R.id.numberzero);
        assignId(ButtonAC,R.id.buttonAC);
        assignId(ButtonDot,R.id.buttondot);






    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
       String dataCalculate = Resulttv.getText().toString();

       //dataCalculate = dataCalculate+buttontext;
       Resulttv.setText(dataCalculate);
       if(buttontext.equals("AC")){
           Solutiontv.setText("");
           Solutiontv.setText("0");
           return;
       }

       if(buttontext.equals("=")){
           Solutiontv.setText(Resulttv.getText());
           return;
       }
       if(buttontext.equals("C")) {
           dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
       }else{
               dataCalculate = dataCalculate+buttontext;
           }
       Resulttv.setText(dataCalculate);

       String finalResult = getResult(dataCalculate);
       if(!finalResult.equals("Err")){
           Resulttv.setText(finalResult);
       }
       }

       String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e ){
            return "Err";
        }
       }



    }
