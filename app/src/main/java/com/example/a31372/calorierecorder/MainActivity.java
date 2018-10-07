package com.example.a31372.calorierecorder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.speech.RecognitionService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText begin;
    private EditText end;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        begin = (EditText) findViewById(R.id.text_begin);
        end = (EditText) findViewById(R.id.text_end);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalorieActivity.class);
                startActivity(intent);
            }
        });

        begin.setInputType(InputType.TYPE_NULL);    //不显示系统输入键盘
        end.setInputType(InputType.TYPE_NULL);    //不显示系统输入键盘

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(1);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(2);
            }
        });

        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    /*
    展示日期选择对话框
     */
    private void showDatePickerDialog(final int i) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(i == 1){
                    begin.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
                else if(i == 2){
                    end.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
