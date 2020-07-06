package com.example.secreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authenticate extends AppCompatActivity {
    EditText pass;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pass=(EditText)findViewById(R.id.passwd);
        b=(Button)findViewById(R.id.next);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(pass.getText().toString().equals("1234"))
                        {
                            Intent in=new Intent(Authenticate.this,chat.class);
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(Authenticate.this,"Incorrect Credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
