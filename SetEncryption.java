package com.example.secreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SetEncryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_encryption);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button b=(Button)findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SetEncryption.this,"Succesfully Updated",Toast.LENGTH_LONG).show();
                Intent i =new Intent(SetEncryption.this,Authenticate.class);
                startActivity(i);
                finish();
            }
        });
    }
}
