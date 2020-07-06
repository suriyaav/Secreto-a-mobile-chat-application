package com.example.secreto;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass,phn;
    Button signin;
    private DatabaseReference dbreff;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        email=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.button2);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(LoginActivity.this,UnauthChat.class);
                        Splash.uid="9176323705";
                        db.execSQL("Insert into User values('9176323705','Vignesh K G','vicky');");
                        startActivity(in);
                        finish();
                    }
                });
        phn=(EditText)findViewById(R.id.phn);
        dbreff= FirebaseDatabase.getInstance().getReference().child("User");
        signin=(Button)findViewById(R.id.login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register()
    {
        Member m=new Member();
        try{

            m.setName(phn.getText().toString());
            m.setUserid(email.getText().toString());
            m.setPassword(pass.getText().toString());
            dbreff.child(email.getText().toString()).setValue(m);
            db.execSQL("Insert into User values('"+m.getUserid()+"','"+m.getName()+"','"+m.getPassword()+"');");
       }catch (Exception e){
            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        Toast.makeText(LoginActivity.this,"Sign Up Successfull",Toast.LENGTH_LONG).show();
         Splash.uid=m.getUserid();
         Intent in=new Intent(this,UnauthChat.class);
        startActivity(in);
    }
}
