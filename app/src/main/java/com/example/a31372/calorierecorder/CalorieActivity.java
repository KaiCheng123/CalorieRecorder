package com.example.a31372.calorierecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalorieActivity extends Activity {

    private TextView data1;
    private Button btn1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorielayout);

        btn1 = (Button) findViewById(R.id.btn1);
        data1 = (TextView) findViewById(R.id.data1);
        sharedPreferences = getSharedPreferences("calorie_data_file",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalorieActivity.this,KalendarActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                if(resultCode == RESULT_OK){
                    String result = data.getStringExtra("calorie_data");
                    editor.putString("Value",result);
                    editor.commit();
                }
                break;
                default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String value = sharedPreferences.getString("Value","");
        data1.setText(value);
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putString("Value",data1.getText().toString().trim());
        editor.commit();
    }
}
