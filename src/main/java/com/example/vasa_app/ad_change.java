package com.example.vasa_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ad_change extends AppCompatActivity {


    EditText ar,da,ti,ma,am,pu,so,sl,last,ra,rh;

    String areat,datet,timet,messaget,amountt,put,sot,slt,lat,rat,rht;

    Button b1,b2,b3,b4,send,b5;

    LinearLayout l1,l2;

    RecyclerView recycle;
    adadapter adad;
    ArrayList<ad_us> list;


    TextView fnt,lnt,dobt,mobt,eidt,natt,stat,dist,tat,vt,idt,passt,areath,pct;

    Intent inte;

    ConstraintLayout c1,c2,c3,c4;

    String fnameDB,lnameDB,mobDB,eidDB,dobDB,natDB,staDB,disDB,taDB,viDB,adsss,apass,areaDB,pcDB;

    String ufnameDB,ulnameDB,umobDB,ueidDB,udobDB,unatDB,ustaDB,udisDB,utaDB,uviDB,uadsss,uapass,uareaDB,upcDB;

    String aid,ap;

    DatabaseReference refer,re,ref;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_change);

        c1 = (ConstraintLayout)findViewById(R.id.ad_in_out1);
        c2 = (ConstraintLayout)findViewById(R.id.ad_in_out);
        l1 = (LinearLayout)findViewById(R.id.ad_lin_upin);


        c2.setVisibility(View.GONE);
        l1.setVisibility(View.VISIBLE);

//        recycle = findViewById(R.id.recycle);
//
//        list = new ArrayList<>();
//
//        recycle.setHasFixedSize(true);
//        recycle.setLayoutManager(new LinearLayoutManager(this));
//        ref2 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");
//
//        adad = new adadapter(this,list);
//
//        recycle.setAdapter(adad);
//
//        ref2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    ad_us ad = dataSnapshot.getValue(ad_us.class);
//                    list.add(ad);
//                }
//                adad.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });







        refer = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");

        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");

        ar = (EditText) findViewById(R.id.ad_uat);
        da=(EditText) findViewById(R.id.ad_udt);
        ti=(EditText) findViewById(R.id.ad_utt);
        ma=(EditText) findViewById(R.id.ad_umt);
        am=(EditText) findViewById(R.id.ad_am);
        pu=(EditText) findViewById(R.id.ad_pu);
        so=(EditText) findViewById(R.id.ad_so);
        sl=(EditText) findViewById(R.id.ad_sole);
        last=(EditText) findViewById(R.id.ad_last);
        ra=(EditText) findViewById(R.id.ad_rain);
        rh=(EditText) findViewById(R.id.ad_rh);

        inte = getIntent();

        fnameDB = inte.getStringExtra("fname");
        lnameDB = inte.getStringExtra("lname");
        dobDB = inte.getStringExtra("dob");
        mobDB = inte.getStringExtra("mob");
        eidDB = inte.getStringExtra("eid");
        natDB = inte.getStringExtra("nat");
        staDB = inte.getStringExtra("sta");
        disDB = inte.getStringExtra("dis");
        taDB = inte.getStringExtra("taluk");
        viDB = inte.getStringExtra("village");
        adsss = inte.getStringExtra("ad_id");
        apass = inte.getStringExtra("ad_pass");
        areaDB = inte.getStringExtra("area");
        pcDB = inte.getStringExtra("pc");

        fnt =(TextView) findViewById(R.id.ad_de_fn);
        lnt =(TextView) findViewById(R.id.ad_de_ln);
        mobt =(TextView)findViewById(R.id.ad_de_mob);
        eidt =(TextView)findViewById(R.id.ad_de_eid);
        dobt =(TextView)findViewById(R.id.ad_de_age);
        natt =(TextView)findViewById(R.id.ad_de_nat);
        stat =(TextView)findViewById(R.id.ad_de_sta);
        dist =(TextView)findViewById(R.id.ad_de_dis);
        tat =(TextView) findViewById(R.id.ad_de_ta);
        vt =(TextView) findViewById(R.id.ad_de_vi);
        areath =(TextView) findViewById(R.id.ad_de_dns);
        passt =(TextView) findViewById(R.id.ad_de_pass);
        idt =(TextView)findViewById(R.id.ad_de_aid);
        pct =(TextView)findViewById(R.id.ad_de_pcs);

       send = findViewById(R.id.ad_send);
        b2 = findViewById(R.id.ad2);
       b3 = findViewById(R.id.ad3);
       b4= findViewById(R.id.ad4);


            send.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      if(ar.length() == 0){
                                          ar.setError("Enter Update Area");
                                          ar.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if(da.length() == 0){
                                              da.setError("Enter Update Date");
                                              da.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if(ti.length() == 0){
                                              ti.setError("Enter Update Time");
                                              ti.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if(ma.length() == 0){
                                              ma.setError("Enter Message");
                                              ma.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else if(am.length() == 0){
                                              am.setError("Enter amount of water");
                                              am.setBackground(getDrawable(R.drawable.edittext2));
                                      }
                                      else{

                                          Date currentDate = new Date();

                                          // Specify the desired date and time format
                                          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                                          // Format the current date and time to a string
                                          String formattedDate = dateFormat.format(currentDate);





                                          areat = ar.getText().toString().trim();
                                          timet = ti.getText().toString().trim();
                                          datet = da.getText().toString().trim();
                                          messaget= ma.getText().toString().trim();
                                          amountt = am.getText().toString().trim();
                                          put= pu.getText().toString().trim();
                                          sot= so.getText().toString().trim();
                                          slt= sl.getText().toString().trim();
                                          lat= last.getText().toString().trim();
                                          rat= ra.getText().toString().trim();
                                          rht=rh.getText().toString().trim();


                                          refer.child("ad_update_db").addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Admin_Id").setValue(adsss);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("District").setValue(disDB);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Taluk").setValue(taDB);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Village").setValue(viDB);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Date").setValue(datet);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Time").setValue(timet);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Area").setValue(areat);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Source").setValue(sot);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Source_level").setValue(slt);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("last_month_supplied_water_level").setValue(lat);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Intensity_of_rainfall").setValue(rat);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Rharvesting").setValue(rht);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Purity_of_water").setValue(put);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Amount_of_water").setValue(amountt);
                                                      refer.child("ad_update_db").child(adsss).child(formattedDate).child("Message").setValue(messaget);
                                                      Toast.makeText(ad_change.this,"Message has updated successfully",Toast.LENGTH_SHORT).show();


                                              }

                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {

                                              }
                                          });

                                          refer.child("ad_up_dis_db").addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Admin_Id").setValue(adsss);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("District").setValue(disDB);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Taluk").setValue(taDB);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Village").setValue(viDB);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Date").setValue(datet);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Time").setValue(timet);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Area").setValue(areat);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Source").setValue(sot);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Source_level").setValue(slt);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("last_month_supplied_water_level").setValue(lat);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Intensity_of_rainfall").setValue(rat);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Rharvesting").setValue(rht);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Purity_of_water").setValue(put);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Amount_of_water").setValue(amountt);
                                                  refer.child("ad_up_dis_db").child(taDB).child(viDB).child("Message").setValue(messaget);


                                              }

                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {

                                              }
                                          });



//                                          refer.child("ad_update_his_db").addListenerForSingleValueEvent(new ValueEventListener() {
//                                              @Override
//                                              public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                                  refer.child("ad_update_db").child(adsss).child("Admin_Id").setValue(adsss);
//                                                  refer.child("ad_update_db").child(adsss).child("District").setValue(disDB);
//                                                  refer.child("ad_update_db").child(adsss).child("Taluk").setValue(taDB);
//                                                  refer.child("ad_update_db").child(adsss).child("Village").setValue(viDB);
//                                                  refer.child("ad_update_db").child(adsss).child("Date").setValue(datet);
//                                                  refer.child("ad_update_db").child(adsss).child("Time").setValue(timet);
//                                                  refer.child("ad_update_db").child(adsss).child("Area").setValue(areat);
//                                                  refer.child("ad_update_db").child(adsss).child("Amount_of_water").setValue(amountt);
//                                                  refer.child("ad_update_db").child(adsss).child("Message").setValue(messaget);
//                                                  Toast.makeText(ad_change.this,"Message has updated successfully",Toast.LENGTH_SHORT).show();
//
//                                              }
//
//                                              @Override
//                                              public void onCancelled(@NonNull DatabaseError error) {
//
//                                              }
//                                          });

                                      }




                                  }
                              }
        );



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      l1.setVisibility(View.GONE);
                                      c2.setVisibility(View.VISIBLE);

                                      fnt.setText(fnameDB);
                                      lnt.setText(lnameDB);
                                      dobt.setText(dobDB);
                                      mobt.setText(mobDB);
                                      eidt.setText(eidDB);
                                      natt.setText(natDB);
                                      stat.setText(staDB);
                                      dist.setText(disDB);
                                      tat.setText(taDB);
                                      vt.setText(viDB);
                                      areath.setText(areaDB);
                                      idt.setText(adsss);
                                      passt.setText(apass);
                                      pct.setText(pcDB);
                                  }
                              }
        );

        b4.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      inte = new Intent(ad_change.this,ad_his.class);
                                      startActivity(inte);
                                  }
                              }
        );

    }

//    private  void sendSMS(){
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage();


//        ref.child("us_reg_db").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child(disDB).child(disDB).hasChild() && snapshot.hasChild(taDB) && snapshot.hasChild(viDB)) {
//
//                    ufnameDB =
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


private void sendSMS(){

}


}