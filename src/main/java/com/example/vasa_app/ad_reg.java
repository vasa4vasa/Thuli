package com.example.vasa_app;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class ad_reg extends AppCompatActivity {


    String selectn, selects, selectd, selectt, selectv;
    public Spinner nspinner, sspinner, dspinner, tspinner, vspinner;

    ArrayAdapter<CharSequence> narr, sarr, darr, tarr, varr;

    EditText fn, ln, age, mn, eid, dn, pc;

    ImageView img;

    public String fns,lns,ages,mns,eids,pwds,cpwds,dns,pcs,males,females,otherss;

    Button req;


    private Uri imguri;
    private Bitmap imgstore;

    private static final int PICK_IMAGE_REQUEST = 1;

    DatabaseReference refer,ref ;



    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                    "\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                    ")+"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_reg);

        refer = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");
        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");

        nspinner = findViewById(R.id.ad_reg_spin_nat);

        narr = ArrayAdapter.createFromResource(this, R.array.national, R.layout.ad_spinner_na);
        narr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nspinner.setAdapter(narr);

        nspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sspinner = findViewById(R.id.ad_reg_spin_sta);
                selectn = nspinner.getSelectedItem().toString();
                int parentId = parent.getId();
                if (parentId == R.id.ad_reg_spin_nat) {
                    switch (selectn) {
                        case "Select Your Nation":
                            sarr = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.default_state, R.layout.ad_spinner_na);
                            break;
                        case "India":
                            sarr = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.ind_states, R.layout.ad_spinner_na);
                            break;

                        case "Pakistan":
                            sarr = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.pak_state, R.layout.spinner_na);
                            break;
                        default:
                            break;
                    }
                    sarr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sspinner.setAdapter(sarr);
                    sspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            dspinner = findViewById(R.id.ad_reg_spin_dis);
                            selects = sspinner.getSelectedItem().toString();
                            int paId = parent.getId();
                            if (paId == R.id.ad_reg_spin_sta) {
                                switch (selects) {
                                    case "Select Your State":
                                        darr = ArrayAdapter.createFromResource(parent.getContext(),
                                                R.array.default_districts, R.layout.ad_spinner_na);
                                        break;
                                    case "Tamil Nadu":
                                        darr = ArrayAdapter.createFromResource(parent.getContext(),
                                                R.array.array_tamil_nadu_districts, R.layout.ad_spinner_na);
                                        break;
                                    default:
                                        break;
                                }
                                darr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                dspinner.setAdapter(darr);
                                dspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        tspinner = findViewById(R.id.ad_reg_spin_tha);
                                        selectd = dspinner.getSelectedItem().toString();
                                        int parId = parent.getId();
                                        if (parId == R.id.ad_reg_spin_dis) {
                                            switch (selectd) {
                                                case "Select Your District":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.default_taluk, R.layout.ad_spinner_na);
                                                    break;
                                                case "Dindigul":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.array_dindigul_taluk, R.layout.ad_spinner_na);
                                                    break;
                                                case "Madurai":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.array_madurai_taluk, R.layout.ad_spinner_na);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            tarr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            tspinner.setAdapter(tarr);
                                            tspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    vspinner = findViewById(R.id.ad_reg_spin_v);
                                                    selectt = tspinner.getSelectedItem().toString();
                                                    int pId = parent.getId();
                                                    if (pId == R.id.ad_reg_spin_tha) {
                                                        switch (selectt) {
                                                            case "Select Your Taluk":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_default_village, R.layout.ad_spinner_na);
                                                                break;
                                                            case "Attur":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_attur_village, R.layout.ad_spinner_na);
                                                                break;
                                                            case "Dindigul(all)":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_dindigul_village, R.layout.ad_spinner_na);
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                        varr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        vspinner.setAdapter(varr);
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fn = (EditText) findViewById(R.id.ad_reg_fn);
        ln = (EditText) findViewById(R.id.ad_reg_ln);
        age = (EditText) findViewById(R.id.ad_reg_age);
        mn = (EditText) findViewById(R.id.ad_reg_mn);
        eid = (EditText) findViewById(R.id.ad_reg_em);
        dn = (EditText) findViewById(R.id.ad_reg_add);
        pc = (EditText) findViewById(R.id.ad_reg_pc);
        req = (Button) findViewById(R.id.ad_reg_btn);
        img = (ImageView) findViewById(R.id.imageView);




        req.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view){

                fns = fn.getText().toString();
                lns = ln.getText().toString();
                ages = age.getText().toString();
                mns = mn.getText().toString();
                eids = eid.getText().toString();
                dns = dn.getText().toString();
                pcs  = pc.getText().toString();
                selectn = nspinner.getSelectedItem().toString();
                selects = sspinner.getSelectedItem().toString();
                selectd = dspinner.getSelectedItem().toString();
                selectt = tspinner.getSelectedItem().toString();
                selectv = vspinner.getSelectedItem().toString();





                if(fn.length() == 0){
                    fn.setError("Enter Your First Name");
                    fn.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(ln.length() == 0){
                    ln.setError("Enter Your Last Name");
                    ln.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(age.length() == 0){
                    age.setError("Enter Your Age");
                    age.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(mn.length() == 0){
                    mn.setError("Enter Your Mobile Number");
                    mn.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(mn.length() != 10){
                    mn.setError("Enter Your Mobile Number in 10 Digits");
                    mn.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(eid.length() == 0){
                    eid.setError("Enter Your Email-Id");
                    eid.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(eids).matches()){
                    eid.setError("Enter valid Email-Id");
                    eid.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(dn.length() == 0){
                    dn.setError("Enter Your Local Address");
                    dn.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else if(pc.length() == 0){
                    pc.setError("Enter Your Postal Code");
                    pc.setBackground(getDrawable(R.drawable.error_edit1));
                }
                else{
                    if (!selectv.equals("Select Your Village")){
                        req.setEnabled(true);
                        fns = fn.getText().toString();
                        lns = ln.getText().toString();
                        ages = age.getText().toString();
                        mns = mn.getText().toString();
                        eids = eid.getText().toString();
                        dns = dn.getText().toString();
                        pcs = pc.getText().toString();
                        selectn = nspinner.getSelectedItem().toString();
                        selects = sspinner.getSelectedItem().toString();
                        selectd = dspinner.getSelectedItem().toString();
                        selectt = tspinner.getSelectedItem().toString();
                        selectv = vspinner.getSelectedItem().toString();


                        refer.child("ad_reg_db").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(mns)){
                                    Toast.makeText(ad_reg.this,"Already Registered Mobile Number",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    refer.child("ad_reg_db").child(mns).child("First_name").setValue(fns);
                                    refer.child("ad_reg_db").child(mns).child("Last_Name").setValue(lns);
                                    refer.child("ad_reg_db").child(mns).child("Date_of_birth").setValue(ages);
                                    refer.child("ad_reg_db").child(mns).child("mobile_number").setValue(mns);
                                    refer.child("ad_reg_db").child(mns).child("Email_Id").setValue(eids);
                                    refer.child("ad_reg_db").child(mns).child("National").setValue(selectn);
                                    refer.child("ad_reg_db").child(mns).child("State").setValue(selects);
                                    refer.child("ad_reg_db").child(mns).child("District").setValue(selectd);
                                    refer.child("ad_reg_db").child(mns).child("Taluk").setValue(selectt);
                                    refer.child("ad_reg_db").child(mns).child("Village").setValue(selectv);
                                    refer.child("ad_reg_db").child(mns).child("Location").setValue(dns);
                                    refer.child("ad_reg_db").child(mns).child("Postal_code").setValue(pcs);
                                    Toast.makeText(ad_reg.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ad_reg.this,alog.class));
                                    finish();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                        ref.child("ad_dis_db").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    refer.child("ad_dis_db").child(selectd).child("First_name").setValue(fns);
                                    refer.child("ad_dis_db").child(selectd).child("Last_Name").setValue(lns);
                                    refer.child("ad_dis_db").child(selectd).child("Date_of_birth").setValue(ages);
                                    refer.child("ad_dis_db").child(selectd).child("mobile_number").setValue(mns);
                                    refer.child("ad_dis_db").child(selectd).child("Email_Id").setValue(eids);
                                    refer.child("ad_dis_db").child(selectd).child("National").setValue(selectn);
                                    refer.child("ad_dis_db").child(selectd).child("State").setValue(selects);
                                    refer.child("ad_dis_db").child(selectd).child("District").setValue(selectd);
                                    refer.child("ad_dis_db").child(selectd).child("Taluk").setValue(selectt);
                                    refer.child("ad_dis_db").child(selectd).child("Village").setValue(selectv);
                                    refer.child("ad_dis_db").child(selectd).child("Location").setValue(dns);
                                    refer.child("ad_dis_db").child(selectd).child("Postal_code").setValue(pcs);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }



            }

    });


        fn.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      fn.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                  }
                              }
        );

        ln.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      ln.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                  }
                              }
        );

        age.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       age.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                   }
                               }
        );

        mn.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      mn.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                  }
                              }
        );

        eid.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       eid.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                   }
                               }
        );

        dn.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      dn.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                  }
                              }
        );

        pc.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      pc.setBackground(getDrawable(R.drawable.ad_reg_edit));
                                  }
                              }
        );

    }






}

