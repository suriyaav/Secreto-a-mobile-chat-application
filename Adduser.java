package com.example.secreto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adduser extends AppCompatActivity {
    EditText use;
    Button b1;
    int flag=0;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        use=(EditText)findViewById(R.id.user);
        b1=(Button)findViewById(R.id.adduser);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref=FirebaseDatabase.getInstance().getReference();
                dbref.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i=0;
                        for(DataSnapshot dta1:dataSnapshot.getChildren())
                        {
                            if(use.getText().toString().equals(dta1.getKey().toString())){
                                i=1;
                                startchat(use.getText().toString(),dta1.child("name").getValue().toString());
                                break;}

                        }
                        if(i==0){
                        Toast.makeText(Adduser.this,"No User Found",Toast.LENGTH_LONG).show();}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    void startchat(String user,String name)
    {
        SQLiteDatabase db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        Cursor test=db.rawQuery("Select * from chatlists",null);
             db.execSQL("Insert into chatlists values('" + user + "','" + name + "');");
             String sql = "CREATE TABLE if not exists'" + user + "' (msg varchar,typ varchar)";
             try {
                 db.execSQL(sql);
             } catch (Exception e) {
                 Toast.makeText(Adduser.this, e.getMessage(), Toast.LENGTH_LONG).show();
             }

        //Toast.makeText(Adduser.this,"User Successfully added in your chatlist",Toast.LENGTH_LONG).show();
        Intent in=new Intent(Adduser.this,MainActivity.class);
       in.putExtra("User",user);
       startActivity(in);
       finish();
    }
}
