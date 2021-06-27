package com.example.firehotel;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class History extends Fragment {

    /*TextView name,area,cost,userId;
    ToggleButton toggleButton;*/

    private FirebaseUser user2;
    private DatabaseReference reference2,reff;
    private String userID2;

    RecyclerView recyclerView2;
    HistoryAdapter historyAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.history, container, false);

        recyclerView2=view.findViewById(R.id.recview2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));


        user2= FirebaseAuth.getInstance().getCurrentUser();
        reference2= FirebaseDatabase.getInstance().getReference("Users");
        userID2=user2.getUid();

        Query query= FirebaseDatabase.getInstance().getReference().child("Booked").child(userID2);

        FirebaseRecyclerOptions<getBooked> options2 =
                new FirebaseRecyclerOptions.Builder<getBooked>()
                        .setQuery(query, getBooked.class)
                        .build();

        historyAdapter= new HistoryAdapter(options2);
        recyclerView2.setAdapter(historyAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        historyAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        historyAdapter.stopListening();
    }
}

/*final TextView name=view.findViewById(R.id.nametext);
        final TextView area=view.findViewById(R.id.areatext);
        final TextView cost=view.findViewById(R.id.costtext);
        final TextView userId=view.findViewById(R.id.textuserId);
        final ToggleButton toggleButton=view.findViewById(R.id.btnToggle);*/

        /*user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid().toString();*/

        /*user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();*/

        /*user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();


        reff=FirebaseDatabase.getInstance().getReference().child("Booked").child(userID);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                *//*List<getBooked> list= (List<getBooked>) snapshot.getValue();*//*

 *//*System.out.println(Arrays.asList(list));*//*
 *//*Log.e("History.java", String.valueOf(snapshot.getChildren()));*//*




 *//*for(DataSnapshot bookedDatasnap:snapshot.getChildren()){*//*
                    getBooked getBookedobj=snapshot.getValue(getBooked.class);

                    if (getBookedobj!=null){
                        String hotelName=getBookedobj.nameofhotel;
                        String hotelarea=getBookedobj.areaofhotel;
                        String hotelcost=getBookedobj.costofhotel;
                        String toggleValue=getBookedobj.toggleofhotel;
                        String idUser=getBookedobj.userIdofhotel;

                            name.setText(hotelName);
                            area.setText(hotelarea);
                            cost.setText(hotelcost);
                            toggleButton.setText(toggleValue);
                            userId.setText(idUser);


                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });*/
