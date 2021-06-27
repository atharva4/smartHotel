package com.example.firehotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

   /* RecyclerView recyclerView;
    myadapter adapter;*/
    private FirebaseAuth mAuth;
    private FirebaseUser usermain;
    private DatabaseReference referencemain;
    private String userIDmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);


        Home fragment=new Home();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, "Home");
        fragmentTransaction.commit();



        usermain= FirebaseAuth.getInstance().getCurrentUser();
        referencemain= FirebaseDatabase.getInstance().getReference("Users");
        userIDmain=usermain.getUid();

        referencemain.child(userIDmain).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile=snapshot.getValue(User.class);

                if (userProfile!=null){
                    String fullName=userProfile.fullName;

                    View header=navigationView.getHeaderView(0);
                    TextView username=header.findViewById(R.id.userName);
                    username.setText(fullName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onBackPressed(){
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {super.onBackPressed();}
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int id=item.getItemId();

        if (id == R.id.Home) {
            setTitle("Home");
            /*startActivity(new Intent(MainActivity.this,hotelListActivity.class));*/
            Home fragment = new Home();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Home");
            fragmentTransaction.commit();
        }
        else if (id == R.id.MyAccount) {
            setTitle("My Account");
            MyAccount fragment = new MyAccount();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "My Account");
            fragmentTransaction.commit();
        } else if (id == R.id.History) {
            setTitle("My Bookings");
            History fragment = new History();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "History");
            fragmentTransaction.commit();
        }
        else if (id == R.id.logOut) {
            mAuth.signOut();
        }

        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
