package com.example.a31372.calorierecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CalorieActivity extends Activity {

    private Button btn1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorielayout);

        btn1 = (Button) findViewById(R.id.btn1);
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
                    arrayList.add(result);
                    saveData();
                }
                break;
                default:
        }
    }

    private void saveData() {
        editor.putInt("ListNumber", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++){
            editor.putString("item_"+i, arrayList.get(i));
        }
        editor.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        int ListNumber = sharedPreferences.getInt("ListNumber",0);
        arrayList.clear();
        for (int i = 0; i < ListNumber; i++){
            String ListItem = sharedPreferences.getString("item_"+i, null);
            arrayList.add(ListItem);
        }
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayList));
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }
}
