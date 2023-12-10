package com.example.vasa_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UslogActivity extends AppCompatActivity {


    ConstraintLayout c,c1;
    TextView reg;

    String uss,ums,ups;

    String disgo,tago,vilgo;
    EditText un,up,um;
    Button b1,b2;
    Intent inte;

    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;

    DatabaseReference ref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uslog);

        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");


        c1 = (ConstraintLayout) findViewById(R.id.us_log_in);
        un = (EditText) findViewById(R.id.us_uu);
        up = (EditText) findViewById(R.id.us_upass);
        um = (EditText) findViewById(R.id.us_mn);
        reg = (TextView) findViewById(R.id.ustext4);
        b1 = (Button) findViewById(R.id.us_signin);





        c1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      c1.setBackground(getDrawable(R.drawable.ac_lin_bor1));
                                      c1.setScaleX(1.001F);
                                      c1.setScaleY(1.001F);
                                  }
                              }
        );
        un.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      un.setBackground(getDrawable(R.drawable.edittext1));
                                      un.setScaleX(1.001F);
                                      un.setScaleY(1.001F);
                                  }
                              }
        );
        up.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      up.setBackground(getDrawable(R.drawable.edittext1));
                                      up.setScaleX(1.001F);
                                      up.setScaleY(1.001F);
                                  }
                              }
        );
        um.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      um.setBackground(getDrawable(R.drawable.edittext1));
                                      um.setScaleX(1.001F);
                                      um.setScaleY(1.001F);
                                  }
                              }
        );
        reg.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      reg.setScaleX(1.001F);
                                      reg.setScaleY(1.001F);
                                      inte = new Intent(UslogActivity.this,user_reg.class);
                                      startActivity(inte);
                                  }
                              }
        );

        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      b1.setTextColor(Color.WHITE);
                                      b1.setScaleX(1.001F);
                                      b1.setScaleY(1.001F);
                                      b1.setBackground(getDrawable(R.drawable.ac_lin_but1));

                                      if (un.length() == 0) {
                                          un.setError("Enter Username");
                                          un.setBackground(getDrawable(R.drawable.edittext2));
                                      } else if (um.length() == 0) {
                                          um.setError("Enter Your Mobile Number");
                                          um.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if (up.length() == 0) {
                                          up.setError("Enter Your Password");
                                          up.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if (um.length()!=10) {
                                          um.setError("Mobile number must be in 10 digits");
                                          um.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if (up.length()!=8) {
                                          up.setError("Pasword must be in 8 digits");
                                          up.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else{

                                          uss = un.getText().toString();
                                          ums = um.getText().toString();
                                          ups = up.getText().toString();

                                          ref.child("us_reg_db").addListenerForSingleValueEvent(new ValueEventListener() {
                                          @Override
                                          public void onDataChange(@NonNull DataSnapshot snapshot) {
                                              if(snapshot.hasChild(ums)) {
                                                  String passs = snapshot.child(ums).child("password").getValue(String.class);
                                                  if (ups.equals(passs)) {
                                                      Toast.makeText(UslogActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                                      tago = snapshot.child(ums).child("Taluk").getValue(String.class);
                                                      vilgo = snapshot.child(ums).child("Village").getValue(String.class);
//                                                      startActivity(new Intent(UslogActivity.this,us_get.class));
//                                                      finish();
//
//                                                      inte = new Intent(UslogActivity.this, us_get.class);
//                                                      inte.putExtra("taluk",tago);
//                                                      inte.putExtra("village",vilgo);
//                                                      startActivity(inte);
//                                                      finish();


                                                  } else {
                                                      Toast.makeText(UslogActivity.this, "Enter Valid Information", Toast.LENGTH_LONG).show();
                                                  }
                                              }
                                              else {
                                                  Toast.makeText(UslogActivity.this, "Wrong Mobile Number", Toast.LENGTH_LONG).show();
                                              }
                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError error) {

                                          }
                                      });


                                          ref.child("ad_up_dis_db").addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                  if (snapshot.hasChild(tago)) {
                                                      s1 = snapshot.child(tago).child(vilgo).child("Date").getValue(String.class);
                                                      s2 = snapshot.child(tago).child(vilgo).child("Time").getValue(String.class);
                                                      s3 = snapshot.child(tago).child(vilgo).child("Area").getValue(String.class);
                                                      s4 = snapshot.child(tago).child(vilgo).child("Amount_of_water").getValue(String.class);
                                                      s5 = snapshot.child(tago).child(vilgo).child("Purity_of_water").getValue(String.class);
                                                      s6 = snapshot.child(tago).child(vilgo).child("Source").getValue(String.class);
                                                      s7 = snapshot.child(tago).child(vilgo).child("Source_level").getValue(String.class);
                                                      s8 = snapshot.child(tago).child(vilgo).child("last_month_supplied_water_level").getValue(String.class);
                                                      s9 = snapshot.child(tago).child(vilgo).child("Intensity_of_rainfall").getValue(String.class);
                                                      s10= snapshot.child(tago).child(vilgo).child("Rharvesting").getValue(String.class);
                                                      s11 = snapshot.child(tago).child(vilgo).child("Message").getValue(String.class);

                                                      inte = new Intent(UslogActivity.this, us_get.class);
                                                      inte.putExtra("a",s1);
                                                      inte.putExtra("b",s2);
                                                      inte.putExtra("c",s3);
                                                      inte.putExtra("d",s4);
                                                      inte.putExtra("e",s5);
                                                      inte.putExtra("f",s6);
                                                      inte.putExtra("g",s7);
                                                      inte.putExtra("h",s8);
                                                      inte.putExtra("i",s9);
                                                      inte.putExtra("j",s10);
                                                      inte.putExtra("k",s11);
                                                      startActivity(inte);
                                                      finish();




                                                  } else {
                                                      Toast.makeText(UslogActivity.this,"" , Toast.LENGTH_LONG).show();
                                                  }
                                              }


                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {

                                              }
                                          });

                                  }




                                  }
                              }
        );






















































    }
}












