package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     EditText etNote;
     RadioGroup rgStars;
     RadioButton rb;
     Button btnInsert, btnShowList;
     int stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = (EditText) findViewById(R.id.editTextNote);
        rgStars = (RadioGroup) findViewById(R.id.radioGroupStars);
        btnInsert = (Button) findViewById(R.id.buttonInsertNote);
        btnShowList = (Button) findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = etNote.getText().toString();
                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                if (!note.equals("")){


                    DBHelper db = new DBHelper(MainActivity.this);
                    db.getWritableDatabase();
                    db.insertNote(note, Integer.parseInt(rb.getText().toString()));

                    Toast.makeText(MainActivity.this, "Note inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Note must not be empty", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToSecondActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(moveToSecondActivity);
            }
        });

    }
}
