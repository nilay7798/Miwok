package com.example.npatel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create TextView Object
        TextView number = (TextView) findViewById(R.id.number);
        final TextView family = (TextView) findViewById(R.id.familymember);
        TextView color = (TextView) findViewById(R.id.color);
        TextView phrases = (TextView) findViewById(R.id.phrases);

        //onclicklistener for number
        number.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,number.class);
                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
            }
        });

        //onclicklistener for family
        family.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,family.class);
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);

                }
            }
        });

        //onClickListener for color

        color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, color.class);
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);
                }
            }
        });

        //onclicklistener for phrases
        phrases.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,phrases.class);
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);
                }
            }
        });


    }



}

