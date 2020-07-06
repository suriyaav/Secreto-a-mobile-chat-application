package com.example.secreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class UnauthChat extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    LinearLayout lid1;
    LinearLayout.LayoutParams param;
    ImageButton access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauth_chat);
        db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lid1 =(LinearLayout)findViewById(R.id.lid3);
        access=(ImageButton)findViewById(R.id.access);
        param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);
        findchats();
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(UnauthChat.this,Authenticate.class);
                startActivity(in);
            }
        });
    }
    void findchats()
    {
        Cursor c=db.rawQuery("Select  distinct * from chatlists",null);
        while(c.moveToNext())
        {

             Button b=new Button(UnauthChat.this);
            b.setBackgroundColor(Color.TRANSPARENT);
            b.setText(c.getString(1));
            b.setTextColor(Color.RED);
            b.setLayoutParams(param);
            b.setTag(c.getString(0));
            lid1.addView(b);
            b.setOnClickListener(this);
            View v=new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,5));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
            lid1.addView(v);

        }
    }


    @Override
    public void onClick(View view) {
        String clicked=view.getTag().toString();
        Intent in=new Intent(this,UnauthMain.class);
        in.putExtra("User",clicked);
        startActivity(in);

    }
}
