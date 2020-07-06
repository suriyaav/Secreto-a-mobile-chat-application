package com.example.secreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {
    SQLiteDatabase db;
    public static  String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        db=openOrCreateDatabase("Chat", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS User(id varchar,name varchar,password varchar);");
        db.execSQL("Create table if not exists chatlists(id varchar,name varchar);");
        Cursor c=db.rawQuery("select count(*) from User",null);
        c.moveToFirst();
        int count=c.getInt(0);
        if(count==0)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent in=new Intent(Splash.this,LoginActivity.class);
                    startActivity(in);
                    finish();
                }
            },2000);
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Cursor cr=db.rawQuery("select id from User",null);
                    cr.moveToFirst();
                    uid=cr.getString(0);
                    Intent in=new Intent(Splash.this,UnauthChat.class);
                    startActivity(in);
                    finish();
                }
            },2000);

        }

    }
}
