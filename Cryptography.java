package com.example.secreto;

import java.util.HashMap;
import java.util.Map;

final class Cryptography {
    private Map<String,String> dict;
    Cryptography()
    {
        this.initialize();
    }

    void initialize()
    {
        dict=new HashMap<>();
        dict.put("A","..-"); dict.put("B",".--"); dict.put("C",".-"); dict.put("D","-."); dict.put("E","..");
        dict.put("F","--"); dict.put("G","..."); dict.put("H","---"); dict.put("I","-..");
        dict.put("J","--."); dict.put("K","---"); dict.put("L",".-."); dict.put("M","-.-"); dict.put("N",".");
        dict.put("O","-"); dict.put("P","...."); dict.put("Q","...-"); dict.put("R","..-."); dict.put("S",".-..");
        dict.put("T","-.-."); dict.put("W",".--."); dict.put("X","..--"); dict.put("Y","--.."); dict.put("Z","----");

    }
    StringBuilder encode(String msg)
    {
        StringBuilder str=new StringBuilder("");
        int len=msg.length();
        for(int i=0;i<len;i++)
        {
            if(msg.charAt(i)==' ')
            {
                str.append("  ");
                continue;
            }
            str.append(dict.get(String.valueOf(msg.charAt(i)).toUpperCase()));
            str.append(" ");
        }
        return str;
    }
    StringBuilder decode(String msg)
    {
        StringBuilder str=new StringBuilder("");
        int len=msg.length();
        String arr[]=msg.split("  ");
        for(int i=0;i<arr.length;i++)
        {

        }
        return str;
    }


}
