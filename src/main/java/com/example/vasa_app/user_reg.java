package com.example.vasa_app;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class user_reg extends AppCompatActivity {

    String selectn,selects,selectd,selectt,selectv;
    public Spinner nspinner,sspinner,dspinner,tspinner,vspinner;

    ArrayAdapter<CharSequence> narr,sarr,darr,tarr,varr;

    EditText fn,ln,age,mn,eid,pwd,cpwd,dn,pc,us;

    public String fns,lns,ages,mns,eids,pwds,cpwds,dns,pcs,males,females,otherss,uss;

    Button submit;


    DatabaseReference refer,ref ;





    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                    "\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                    ")+"
    );



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);


        refer = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");
        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thuli-firebase-default-rtdb.firebaseio.com/");

        nspinner = findViewById(R.id.us_reg_spin_nat);

        narr = ArrayAdapter.createFromResource(this, R.array.national, R.layout.spinner_na);
        narr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nspinner.setAdapter(narr);

        nspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sspinner = findViewById(R.id.us_reg_spin_sta);
                selectn = nspinner.getSelectedItem().toString();
                int parentId = parent.getId();
                if (parentId == R.id.us_reg_spin_nat) {
                    switch (selectn) {
                        case "Select Your Nation":
                            sarr = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.default_state, R.layout.spinner_na);
                            break;
                        case "India":
                            sarr = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.ind_states, R.layout.spinner_na);
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
                            dspinner = findViewById(R.id.us_reg_spin_dis);
                            selects = sspinner.getSelectedItem().toString();
                            int paId = parent.getId();
                            if (paId == R.id.us_reg_spin_sta) {
                                switch (selects) {
                                    case "Select Your State":
                                        darr = ArrayAdapter.createFromResource(parent.getContext(),
                                                R.array.default_districts, R.layout.spinner_na);
                                        break;
                                    case "Tamil Nadu":
                                        darr = ArrayAdapter.createFromResource(parent.getContext(),
                                                R.array.array_tamil_nadu_districts, R.layout.spinner_na);
                                        break;
                                    default:
                                        break;
                                }
                                darr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                dspinner.setAdapter(darr);
                                dspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        tspinner = findViewById(R.id.us_reg_spin_tha);
                                        selectd = dspinner.getSelectedItem().toString();
                                        int parId = parent.getId();
                                        if (parId == R.id.us_reg_spin_dis) {
                                            switch (selectd) {
                                                case "Select Your District":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.default_taluk, R.layout.spinner_na);
                                                    break;
                                                case "Dindigul":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.array_dindigul_taluk, R.layout.spinner_na);
                                                    break;
                                                case "Madurai":
                                                    tarr = ArrayAdapter.createFromResource(parent.getContext(),
                                                            R.array.array_madurai_taluk, R.layout.spinner_na);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            tarr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            tspinner.setAdapter(tarr);
                                            tspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    vspinner = findViewById(R.id.us_reg_spin_v);
                                                    selectt = tspinner.getSelectedItem().toString();
                                                    int pId = parent.getId();
                                                    if (pId == R.id.us_reg_spin_tha) {
                                                        switch (selectt) {
                                                            case "Select Your Taluk":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_default_village, R.layout.spinner_na);
                                                                break;
                                                            case "Attur":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_attur_village, R.layout.spinner_na);
                                                                break;
                                                            case "Dindigul(all)":
                                                                varr = ArrayAdapter.createFromResource(parent.getContext(),
                                                                        R.array.array_dindigul_village, R.layout.spinner_na);
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

        fn = (EditText) findViewById(R.id.us_reg_fn);
        ln = (EditText) findViewById(R.id.us_reg_ln);
        age = (EditText) findViewById(R.id.us_reg_age);
        mn = (EditText) findViewById(R.id.us_reg_mn);
        eid = (EditText) findViewById(R.id.us_reg_em);
        dn = (EditText) findViewById(R.id.add);
        pc = (EditText) findViewById(R.id.pc);
        pwd = (EditText) findViewById(R.id.pwd);
        cpwd = (EditText) findViewById(R.id.cpwd);
        us = (EditText) findViewById(R.id.us_usname);
        submit = (Button) findViewById(R.id.us_reg_btn);




//

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                fns = fn.getText().toString();
                lns = ln.getText().toString();
                ages = age.getText().toString();
                mns = mn.getText().toString();
                eids = eid.getText().toString();
                dns = dn.getText().toString();
                pcs = pc.getText().toString();
                pwds = pwd.getText().toString();
                cpwds = cpwd.getText().toString();
                uss = us.getText().toString();
                selectn = nspinner.getSelectedItem().toString();
                selects = sspinner.getSelectedItem().toString();
                selectd = dspinner.getSelectedItem().toString();
                selectt = tspinner.getSelectedItem().toString();
                selectv = vspinner.getSelectedItem().toString();




                if (fn.length() == 0) {
                    fn.setError("Enter Your First Name");
                    fn.setBackground(getDrawable(R.drawable.error_edit));
                } else if (ln.length() == 0) {
                    ln.setError("Enter Your Last Name");
                    ln.setBackground(getDrawable(R.drawable.error_edit));
                } else if (age.length() == 0) {
                    age.setError("Enter Your Age");
                    age.setBackground(getDrawable(R.drawable.error_edit));
                } else if (mn.length() == 0) {
                    mn.setError("Enter Your Mobile Number");
                    mn.setBackground(getDrawable(R.drawable.error_edit));
                } else if (mn.length() != 10) {
                    mn.setError("Enter Your Mobile Number in 10 Digits");
                    mn.setBackground(getDrawable(R.drawable.error_edit));
                } else if (eid.length() == 0) {
                    eid.setError("Enter Your Email-Id");
                    eid.setBackground(getDrawable(R.drawable.error_edit));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(eids).matches()) {
                    eid.setError("Enter valid Email-Id");
                    eid.setBackground(getDrawable(R.drawable.error_edit));
                } else if (dn.length() == 0) {
                    dn.setError("Enter Your Local Address");
                    dn.setBackground(getDrawable(R.drawable.error_edit));
                } else if (us.length() == 0) {
                    us.setError("Enter Username");
                    us.setBackground(getDrawable(R.drawable.error_edit));
                }else if (pc.length() == 0) {
                    pc.setError("Enter Your Postal Code");
                    pc.setBackground(getDrawable(R.drawable.error_edit));
                } else if (pwd.length() == 0) {
                    pwd.setError("Enter Password");
                    pwd.setBackground(getDrawable(R.drawable.error_edit));
                } else if (cpwd.length() == 0) {
                    cpwd.setError("Enter Confirm Password");
                    cpwd.setBackground(getDrawable(R.drawable.error_edit));
                } else if (pwd.length() <= 7) {
                    pwd.setError("Password must have atleast 8 character");
                    pwd.setBackground(getDrawable(R.drawable.error_edit));
                } else if (!pwds.equals(cpwds)) {
                    cpwd.setError("Password didn't match");
                    cpwd.setBackground(getDrawable(R.drawable.error_edit));
                } else {
                    if (!selectv.equals("Select Your Village")) {
                        submit.setEnabled(true);


                        refer.child("us_reg_db").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(mns)){
                                    Toast.makeText(user_reg.this,"Already Registered Mobile Number",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    refer.child("us_reg_db").child(mns).child("First_name").setValue(fns);
                                    refer.child("us_reg_db").child(mns).child("Last_Name").setValue(lns);
                                    refer.child("us_reg_db").child(mns).child("Date_of_birth").setValue(ages);
                                    refer.child("us_reg_db").child(mns).child("mobile_number").setValue(mns);
                                    refer.child("us_reg_db").child(mns).child("Email_Id").setValue(eids);
                                    refer.child("us_reg_db").child(mns).child("National").setValue(selectn);
                                    refer.child("us_reg_db").child(mns).child("State").setValue(selects);
                                    refer.child("us_reg_db").child(mns).child("District").setValue(selectd);
                                    refer.child("us_reg_db").child(mns).child("Taluk").setValue(selectt);
                                    refer.child("us_reg_db").child(mns).child("Village").setValue(selectv);
                                    refer.child("us_reg_db").child(mns).child("Location").setValue(dns);
                                    refer.child("us_reg_db").child(mns).child("Postal_code").setValue(pcs);
                                    refer.child("us_reg_db").child(mns).child("username").setValue(uss);
                                    refer.child("us_reg_db").child(mns).child("password").setValue(pwds);
                                    refer.child("us_reg_db").child(mns).child("Confirm_password").setValue(cpwds);
                                    Toast.makeText(user_reg.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(user_reg.this,UslogActivity.class));
                                    finish();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                        ref.child("us_dis_db").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ref.child("us_dis_db").child(selectd).child("First_name").setValue(fns);
                                ref.child("us_dis_db").child(selectd).child("Last_Name").setValue(lns);
                                ref.child("us_dis_db").child(selectd).child("Date_of_birth").setValue(ages);
                                ref.child("us_dis_db").child(selectd).child("mobile_number").setValue(mns);
                                ref.child("us_dis_db").child(selectd).child("Email_Id").setValue(eids);
                                ref.child("us_dis_db").child(selectd).child("National").setValue(selectn);
                                ref.child("us_dis_db").child(selectd).child("State").setValue(selects);
                                ref.child("us_dis_db").child(selectd).child("District").setValue(selectd);
                                ref.child("us_dis_db").child(selectd).child("Taluk").setValue(selectt);
                                ref.child("us_dis_db").child(selectd).child("Village").setValue(selectv);
                                ref.child("us_dis_db").child(selectd).child("Location").setValue(dns);
                                ref.child("us_dis_db").child(selectd).child("Postal_code").setValue(pcs);
                                ref.child("us_dis_db").child(selectd).child("username").setValue(uss);
                                ref.child("us_dis_db").child(selectd).child("password").setValue(pwds);
                                ref.child("us_dis_db").child(selectd).child("Confirm_password").setValue(cpwds);

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
                                      fn.setBackground(getDrawable(R.drawable.reg_edittext1));
                                  }
                              }
        );

        ln.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      ln.setBackground(getDrawable(R.drawable.reg_edittext1));
                                  }
                              }
        );

        age.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       age.setBackground(getDrawable(R.drawable.reg_edittext1));
                                   }
                               }
        );

        mn.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      mn.setBackground(getDrawable(R.drawable.reg_edittext1));
                                  }
                              }
        );

        eid.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       eid.setBackground(getDrawable(R.drawable.reg_edittext1));
                                   }
                               }
        );

        dn.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      dn.setBackground(getDrawable(R.drawable.reg_edittext1));
                                  }
                              }
        );

        pc.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      pc.setBackground(getDrawable(R.drawable.reg_edittext1));
                                  }
                              }
        );

        pwd.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       pwd.setBackground(getDrawable(R.drawable.reg_edittext1));
                                   }
                               }
        );

        cpwd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        cpwd.setBackground(getDrawable(R.drawable.reg_edittext1));
                                    }
                                }
        );





    }





}






















































































