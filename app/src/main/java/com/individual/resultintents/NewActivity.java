package com.individual.resultintents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    EditText txtid, txtname;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        txtid = findViewById(R.id.txtid);
        txtname = findViewById(R.id.txtname);
        btnsave = findViewById(R.id.btnSave);
        btnsave.setOnClickListener(arg0 -> {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            int id = Integer.parseInt(txtid.getText().toString());
            String name = txtname.getText().toString();
            Person p = new Person(id, name);
            bundle.putSerializable("per", p);
            intent.putExtra("DATA", bundle);
            setResult(MainActivity.SAVE_NEW_EMPLOYEE, intent);
            finish();
        });
    }
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.activity_new, menu);
        return true;
    }
}