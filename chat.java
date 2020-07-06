package com.example.secreto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class chat extends AppCompatActivity implements  View.OnClickListener{
    final String user=Splash.uid;
    static  String text="";
    DatabaseReference dbreff;
    SQLiteDatabase db;
    LinearLayout.LayoutParams param;
    LinearLayout lid;
    ImageButton en,newchat;
    String s,msg;
    Map<String,String> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        dbreff= FirebaseDatabase.getInstance().getReference().child("User").child(user);
        db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        lid =(LinearLayout)findViewById(R.id.lid2);
         param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);
         newchat=(ImageButton)findViewById(R.id.newbtn);
         newchat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent in=new Intent(chat.this,Adduser.class);
                 startActivity(in);

             }
         });
        en=(ImageButton)findViewById(R.id.encrypt);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(chat.this,SetEncryption.class);
                startActivity(in);
            }
        });
        findchats();

    }
    void findchats()
    {

        lists=new HashMap<>();
        dbreff.child("Messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dta1:dataSnapshot.getChildren())
                {
                    // arr[i++]=dta1.getKey().toString();

                    View v=new View(chat.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,5));
                    v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                    lid.addView(v);
                    final Button b=new Button(chat.this);
                    b.setBackgroundColor(Color.TRANSPARENT);
                    b.setText(dta1.getKey().toString());
                    lists.put(dta1.getKey().toString(),dta1.getValue().toString());
                    //text=arr[i];
                    b.setTextColor(Color.RED);
                    b.setLayoutParams(param);
                    b.setTag(dta1.getKey().toString());
                    lid.addView(b);
                    b.setOnClickListener(chat.this);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(View view) {
         s=view.getTag().toString();
         msg=lists.get(s);
        try{db.execSQL("Insert into '"+s+"' values('"+msg+"','1')");}catch(Exception e){
            Toast.makeText(chat.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        Intent in=new Intent(chat.this,MainActivity.class);
        in.putExtra("User",s);
        in.putExtra("nwmsg",msg);
        startActivity(in);

    }
}
