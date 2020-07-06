package com.example.secreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UnauthMain extends AppCompatActivity {
    EditText msg;
   // ImageButton send;
    LinearLayout lid;
    TextView receiver;
    String chat="";
    SQLiteDatabase db;
    Cryptography c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unauth_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        msg=(EditText)findViewById(R.id.msg);
        db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        //send=(ImageButton)findViewById(R.id.send);
        lid=(LinearLayout)findViewById(R.id.lid4);
        receiver=(TextView)findViewById(R.id.textView8);

        int i=0;
        c=new Cryptography();

        Intent in=getIntent();
        chat=in.getStringExtra("User");
        receiver.setText(chat);
        while(i<10)
        {

            TextView t=new TextView(this);
            String msg1="Hi Bro mrng";
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,150);
            t.setText(c.encode(msg1));

            t.setPadding(10,20,10,20);
            if(i%2==0){
                t.setGravity(Gravity.CENTER_HORIZONTAL);
                t.setBackgroundColor(Color.LTGRAY);
            param.gravity=Gravity.CENTER_HORIZONTAL;}
            t.setLayoutParams(param);
            lid.addView(t);
            i++;
            if(i==10) {
                Cursor cr = db.rawQuery("Select * from '" + chat + "'", null);
                String ms, ty;
                while (cr.moveToNext()) {
                    LinearLayout.LayoutParams param10=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,150);
                    TextView t10=new TextView(this);
                    ms = cr.getString(0);
                    ty = cr.getString(1);
                    t10.setPadding(10,20,10,20);
                    if (ty.equals("0")) {
                        t10.setGravity(Gravity.CENTER_HORIZONTAL);
                        t10.setBackgroundColor(Color.LTGRAY);
                        param10.gravity = Gravity.CENTER_HORIZONTAL;
                    }
                    t10.setText(c.encode(ms));
                    t10.setLayoutParams(param10);
                    lid.addView(t10);
                    lid.addView(new TextView(this));
                }
            }

        }

    }
}
