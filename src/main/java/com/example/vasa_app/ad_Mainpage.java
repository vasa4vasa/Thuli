package com.example.vasa_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ad_Mainpage extends AppCompatActivity {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mainpage);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView= findViewById(R.id.ad_nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ad_front()).commit();
            navigationView.setCheckedItem(R.id.home);

        }
        replaceFragment(new ad_front());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.home){
                replaceFragment(new ad_front());
            }
            else if(item.getItemId()==R.id.shorts){
                replaceFragment(new ad_self());
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ad_self()).commit();
                navigationView.setCheckedItem(R.id.shorts);
            }
            else if(item.getItemId()==R.id.subscriptions){
                replaceFragment(new ad_users());
            }
            else{
                if(item.getItemId()==R.id.library){
                    replaceFragment(new ad_history());
                }
            }

//            switch (item.getItemId()) {
//                case R.id.home:
//                    replaceFragment(new HomeFragment());
//                    break;
//                case R.id.shorts:
//                    replaceFragment(new ShortsFragment());
//                    break;
//                case R.id.subscriptions:
//                    replaceFragment(new SubscriptionsFragment());
//                    break;
//                case R.id.library:
//                    replaceFragment(new LibraryFragment());
//                    break;
//                default: break;
//            }

            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

        giveto();

    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(ad_Mainpage.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(ad_Mainpage.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(ad_Mainpage.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    void giveto() {
        Intent intent = getIntent();
        if (intent != null) {
            String fnameDB = intent.getStringExtra("fname");
            String lnameDB = intent.getStringExtra("lname");
            String dobDB = intent.getStringExtra("dob");
            String mobDB = intent.getStringExtra("mob");
            String eidDB = intent.getStringExtra("eid");
            String natDB = intent.getStringExtra("nat");
            String staDB = intent.getStringExtra("sta");
            String disDB = intent.getStringExtra("dis");
            String taDB = intent.getStringExtra("taluk");
            String viDB = intent.getStringExtra("village");
            String adsss = intent.getStringExtra("ad_id");
            String apass = intent.getStringExtra("ad_pass");
            String areaDB = intent.getStringExtra("area");
            String pcDB = intent.getStringExtra("pc");


            ad_self fra = new ad_self();

            Bundle args = new Bundle();


            args.putString("fname", fnameDB);
            args.putString("lname", lnameDB);
            args.putString("dob", dobDB);
            args.putString("mob", mobDB);
            args.putString("eid", eidDB);
            args.putString("nat", natDB);
            args.putString("sta", staDB);
            args.putString("dis", disDB);
            args.putString("taluk", taDB);
            args.putString("village", viDB);
            args.putString("ad_id", adsss);
            args.putString("ad_pass", apass);
            args.putString("area", areaDB);
            args.putString("pc", pcDB);

            fra.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.other_activity_fr, fra)
                    .commit();

        }
    }}
