package com.example.vasa_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class alog extends AppCompatActivity {

    LinearLayout l1;
    EditText au,ap,amn;

    TextView reg,fot;

    String aids,apass,amns;

    Button b1;

    Intent inte;

    DatabaseReference ref;

    String passs;
    String adsss;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);


        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");

        l1 = (LinearLayout) findViewById(R.id.ad_log_in);
        au = (EditText) findViewById(R.id.ad_uu);
        ap = (EditText) findViewById(R.id.ad_upass);
        amn = (EditText) findViewById(R.id.ad_mn);
        b1 = (Button) findViewById(R.id.ad_signin);
        reg = (TextView) findViewById(R.id.adtext4);
        fot = (TextView) findViewById(R.id.adtext2);
        aids = au.getText().toString().trim();
        apass = ap.getText().toString().trim();
        amns = amn.getText().toString().trim();




        l1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                l1.setBackground(getDrawable(R.drawable.ac_lin_bor1));
                l1.setScaleX(1.001F);
                l1.setScaleY(1.001F);
                                  }
                              }
        );
        au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au.setBackground(getDrawable(R.drawable.edittext1));
                au.setScaleX(1.001F);
                au.setScaleY(1.001F);
            }
        }
        );
        amn.setOnClickListener(new View.OnClickListener() {
                                  @SuppressLint("UseCompatLoadingForDrawables")
                                  @Override
                                  public void onClick(View v) {
                                      amn.setBackground(getDrawable(R.drawable.edittext1));
                                      amn.setScaleX(1.001F);
                                      amn.setScaleY(1.001F);
                                  }
                              }
        );
        ap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                ap.setBackground(getDrawable(R.drawable.edittext1));
                ap.setScaleX(1.001F);
                ap.setScaleY(1.001F);
            }
        }
        );
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                b1.setTextColor(Color.WHITE);
                b1.setScaleX(1.0001F);
                b1.setScaleY(1.0001F);
                b1.setBackground(getDrawable(R.drawable.ac_lin_but1));

//                startActivity(new Intent(alog.this, US_Mainpage.class));
//                                        finish();

                if (au.length() == 0) {
                    au.setError("Enter admin id");
                    au.setBackground(getDrawable(R.drawable.edittext2));
                }
                else if (ap.length() == 0) {
                    ap.setError("Enter passsword");
                    ap.setBackground(getDrawable(R.drawable.edittext2));
                } else if (amn.length() == 0) {
                    amn.setError("Enter mobile number");
                    amn.setBackground(getDrawable(R.drawable.edittext2));
                }
                else if (amn.length()!=10) {
                    amn.setError("Mobile number must be in 10 digits");
                    amn.setBackground(getDrawable(R.drawable.edittext2));
                }
                else if (ap.length()!=8) {
                    ap.setError("Pasword must be in 8 digits");
                    ap.setBackground(getDrawable(R.drawable.edittext2));
                }
                else {
                    aids = au.getText().toString().trim();
                    apass = ap.getText().toString().trim();
                    amns = amn.getText().toString().trim();

                    ref.child("ad_reg_db").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(amns)) {
                                passs = snapshot.child(amns).child("password").getValue(String.class);
                                adsss = snapshot.child(amns).child("admin_id").getValue(String.class);
                                if (!apass.equals(passs)) {
                                    Toast.makeText(alog.this, "Enter Valid Password", Toast.LENGTH_LONG).show();

                                } else if (!aids.equals(adsss)) {
                                    Toast.makeText(alog.this, "Enter Valid Admin_Id", Toast.LENGTH_LONG).show();
                                } else {
                                    if (aids.equals(adsss) && apass.equals(passs)) {
                                        Toast.makeText(alog.this, "Successfully Logged In", Toast.LENGTH_LONG).show();

                                        String fnameDB = snapshot.child(amns).child("First_name").getValue(String.class);
                                        String lnameDB = snapshot.child(amns).child("Last_Name").getValue(String.class);
                                        String dobDB = snapshot.child(amns).child("Date_of_birth").getValue(String.class);
                                        String mobDB = snapshot.child(amns).child("mobile_number").getValue(String.class);
                                        String eidDB = snapshot.child(amns).child("Email_Id").getValue(String.class);
                                        String natDB = snapshot.child(amns).child("National").getValue(String.class);
                                        String staDB = snapshot.child(amns).child("State").getValue(String.class);
                                        String disDB = snapshot.child(amns).child("District").getValue(String.class);
                                        String taDB = snapshot.child(amns).child("Taluk").getValue(String.class);
                                        String viDB = snapshot.child(amns).child("Village").getValue(String.class);
                                        String areaDB = snapshot.child(amns).child("Location").getValue(String.class);
                                        String pcDB = snapshot.child(amns).child("Postal_code").getValue(String.class);

                                        inte = new Intent(alog.this,ad_change.class);
                                        inte.putExtra("fname",fnameDB);
                                        inte.putExtra("lname",lnameDB);
                                        inte.putExtra("dob",dobDB);
                                        inte.putExtra("mob",mobDB);
                                        inte.putExtra("eid",eidDB);
                                        inte.putExtra("nat",natDB);
                                        inte.putExtra("sta",staDB);
                                        inte.putExtra("dis",disDB);
                                        inte.putExtra("taluk",taDB);
                                        inte.putExtra("village",viDB);
                                        inte.putExtra("ad_id",adsss);
                                        inte.putExtra("ad_pass",apass);
                                        inte.putExtra("area",areaDB);
                                        inte.putExtra("pc",pcDB);
                                        startActivity(inte);
                                        finish();


                                    }
                                }

                            } else {
                                Toast.makeText(alog.this, "Enter Valid Mobile Number", Toast.LENGTH_LONG).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }

        });
        reg.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       inte = new Intent(alog.this,ad_reg.class);
                                       startActivity(inte);

                                   }
                               }
        );



    }
}












