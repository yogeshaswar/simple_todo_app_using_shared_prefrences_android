package com.yogeshaswar.sharedprefferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText edtTask;
    Button btnAddTask;
    TextView txtShowTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateUi();

        //reading data from shared pref
        readAndDisplayData();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Add Button Clicked", Toast.LENGTH_SHORT).show();
                String task = edtTask.getText().toString();
                if (task.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter Task", Toast.LENGTH_SHORT).show();
                    return;
                }
                //show data on screen
                txtShowTask.setText(task);
                //Save data into shared preferences
                saveDataToSp(task);

            }
        });
    }

    private void readAndDisplayData() {
        //step 1: create object/instance of SP
        SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);
        //step 2: read previous data
        String prevTask = sharedPreferences.getString("task", "added task will be shown here");
        //step 3: Display
        txtShowTask.setText(prevTask);

    }

    private void initiateUi() {
        edtTask = findViewById(R.id.edt_task);
        btnAddTask = findViewById(R.id.btn_add);
        txtShowTask = findViewById(R.id.txt_show_task);
    }

    private void saveDataToSp(String task) {
        //TODO - SP
        //step 1: SP object
        SharedPreferences sharedPreferences = getSharedPreferences("MyTasks", MODE_PRIVATE);
        //step 2: Edit
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //step 3: Adding data to editor key:value pair
        editor.putString("task", task);
        editor.apply();

    }
}