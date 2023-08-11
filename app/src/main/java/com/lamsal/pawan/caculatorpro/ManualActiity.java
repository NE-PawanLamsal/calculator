package com.lamsal.pawan.caculatorpro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManualActiity extends AppCompatActivity {
    EditText countTV;
    Button countBtn;
    //int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        countTV = findViewById(R.id.countTV);
        countBtn = findViewById(R.id.btnCount);
        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(countTV.getText().toString());
                count++;
                Toast.makeText(ManualActiity.this, "Wow", Toast.LENGTH_SHORT).show();
                countTV.setText(count+"");
            }
        });
    }
}
