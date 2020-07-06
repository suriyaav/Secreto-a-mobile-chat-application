package com.example.secreto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText msg;
    ImageButton send;
    LinearLayout lid;
    DatabaseReference dbreff;
    DatabaseReference dbsend;
    String user=Splash.uid;
    String chat,hismsg;
    TextView newtext;
    TextView receiver;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("Chat",Context.MODE_PRIVATE,null);
        msg=(EditText)findViewById(R.id.msg);
        send=(ImageButton)findViewById(R.id.send);
        lid=(LinearLayout)findViewById(R.id.lid);
        receiver=(TextView)findViewById(R.id.textView8);
        Intent in=getIntent();
        chat=in.getStringExtra("User");
        hismsg=in.getStringExtra("nwmsg");
        receiver.setText(chat);


        int i=0;
        while(i<10)
        {

            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,150);
            TextView t=new TextView(this);
            String msg="Hi Bro How  is it?";
            t.setText(msg);
            t.setPadding(10,20,10,20);
            if(i%2==0){
                t.setGravity(Gravity.CENTER_HORIZONTAL);
                t.setBackgroundColor(Color.LTGRAY);
                param.gravity=Gravity.CENTER_HORIZONTAL;}
            t.setLayoutParams(param);
            lid.addView(t);
            i++;
            if(i==10) {
                Cursor c = db.rawQuery("Select * from '" + chat + "'", null);
                String ms, ty;
                while (c.moveToNext()) {
                    LinearLayout.LayoutParams param10=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,150);
                    TextView t10=new TextView(this);
                    ms = c.getString(0);
                    ty = c.getString(1);
                    t10.setPadding(10,20,10,20);
                    if (ty.equals("0")) {
                        t10.setGravity(Gravity.CENTER_HORIZONTAL);
                        t10.setBackgroundColor(Color.LTGRAY);
                        param10.gravity = Gravity.CENTER_HORIZONTAL;

                    }
                    t10.setText(ms);
                    t10.setLayoutParams(param10);
                    lid.addView(t10);
                    lid.addView(new TextView(this));
                }
            }

        }

        dbreff=FirebaseDatabase.getInstance().getReference().child("User").child(user).child("Messages");
        dbreff.child(chat).removeValue();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1=new TextView(MainActivity.this);
                t1.setText(msg.getText().toString());
                LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,200);
                param.gravity=Gravity.CENTER_HORIZONTAL;
                t1.setLayoutParams(param);
                t1.setPadding(10,20,10,20);
                t1.setGravity(Gravity.CENTER_HORIZONTAL);
                t1.setBackgroundColor(Color.LTGRAY);
                lid.addView(t1);
                dbsend=FirebaseDatabase.getInstance().getReference().child("User").child(chat).child("Messages");
                try{dbsend.child(user).setValue(msg.getText().toString());
                db.execSQL("Insert into '"+chat+"' values ('"+msg.getText().toString()+"','0');");
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    newtext.setText(e.getMessage());
                }

            }
        });

    }


}
