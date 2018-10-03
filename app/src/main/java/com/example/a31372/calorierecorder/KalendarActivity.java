package com.example.a31372.calorierecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class KalendarActivity extends Activity {

    private CalendarView calendar;
    private EditText calorieText;
    private Button btn2;
    private Context context;
    private String time;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalendarlayout);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calorieText = (EditText) findViewById(R.id.calorieText);
        btn2 = (Button) findViewById(R.id.btn2);
        context = this;

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                time = year+"-"+(month+1)+"-"+dayOfMonth;
                //Toast.makeText(this,time,Toast.LENGTH_LONG).show();
                Toast.makeText(context, time, Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("calorie_data",time+": "+calorieText.getText());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
