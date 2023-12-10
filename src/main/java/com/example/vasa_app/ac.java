package com.example.vasa_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ac extends AppCompatActivity {

    LinearLayout ll;
    Button b1,b2;
    Intent inte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac);


        b1 = (Button) findViewById(R.id.admin);
        b2 = (Button) findViewById(R.id.users);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setScaleX(1.001F);
                b1.setScaleY(1.001F);
                b1.setBackground(getDrawable(R.drawable.ac_lin_but));
                inte = new Intent(ac.this,alog.class);
                startActivity(inte);


                                  }
                              }
        );
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setScaleX(1.001F);
                b2.setScaleY(1.001F);
                b2.setBackground(getDrawable(R.drawable.ac_lin_but));
                inte = new Intent(ac.this,UslogActivity.class);
                startActivity(inte);

            }
        }
        );
    }

}