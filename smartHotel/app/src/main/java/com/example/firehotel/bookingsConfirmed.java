package com.example.firehotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bookingsConfirmed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bookingsConfirmed extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name, area, cost, purl;

    /*Spinner spinner;
    DatabaseReference databaseReference;

    DataSnapshot dataSnapshot;*/

    private FirebaseUser user;
    DatabaseReference reff,reference;
    Booked booked;

    private String userID;


    public bookingsConfirmed() {
        // Required empty public constructor
    }

    public  bookingsConfirmed(String name, String area, String cost,String purl){
        this.name=name;
        this.area=area;
        this.cost=cost;
        this.purl=purl;
    }


    public static bookingsConfirmed newInstance(String param1, String param2) {
        bookingsConfirmed fragment = new bookingsConfirmed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookings_confirmed, container, false);
        ImageView imageholder=view.findViewById(R.id.imageholder);
        TextView nameholder=view.findViewById(R.id.nameholder);
        TextView areaholder=view.findViewById(R.id.areaholder);
        TextView costholder=view.findViewById(R.id.textcost);
        TextView userIdholder=view.findViewById(R.id.userId);

        Button bookingBtn=view.findViewById(R.id.btnbooking);
        ToggleButton toggleButton=view.findViewById(R.id.btnToggle);


      /*  spinner=view.findViewById(R.id.spinner);
        ArrayList<String> names=new ArrayList<String>();

       databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rooms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot childSnapshot:dataSnapshot.getChildren()){
                    String spinnerName=dataSnapshot.child("Rooms").getValue(String.class);
                    names.add(spinnerName);
                }

                *//*ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,android.R.layout.simple_spinner_item,names);*//*
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,names);
                *//*ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item,names);*//*
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        nameholder.setText(name);
        areaholder.setText(area);
        costholder.setText(cost);
        userIdholder.setText(userID);
        Glide.with(getContext()).load(purl).into(imageholder);

        booked=new Booked();

        reff=FirebaseDatabase.getInstance().getReference().child("Booked").child(userID);

        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendName=nameholder.getText().toString().trim();
                String sendArea=areaholder.getText().toString().trim();
                String sendCost=costholder.getText().toString().trim();
               /* String sendUserId=userIdholder.getText().toString().trim()*/;

                String result= (String) toggleButton.getText();



                booked.setName(sendName);
                booked.setArea(sendArea);
                booked.setCost(sendCost);
                booked.setToggle(result);
                /*booked.setUserId(sendUserId);*/
                reff.push().setValue(booked);
                Toast.makeText(getContext(),"Your Booking is Confirmed",Toast.LENGTH_LONG).show();

                /*AppCompatActivity activity= (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,new History()).addToBackStack(null).commit();*/
            }
        });

        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity= (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Home()).addToBackStack(null).commit();
    }
}