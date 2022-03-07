package com.individual.resultintents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText txtid, txtname;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        final Person p = (Person) bundle.getSerializable("per");
        btnsave = findViewById(R.id.btnSave);
        txtid = findViewById(R.id.txtid);
        txtname = findViewById(R.id.txtname);
        txtid.setText(String.format("%d", p.getId()));
        txtname.setText(p.getName());
        txtid.setEnabled(false);
        btnsave.setOnClickListener(arg0 -> {
            p.setName(txtname.getText()+"");
            setResult(MainActivity.SAVE_EDIT_EMPLOYEE, intent);
            finish();
        });

    }
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.activity_edit, menu);
        return true;
    }
}