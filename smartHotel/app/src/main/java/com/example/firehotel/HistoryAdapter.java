package com.example.firehotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class HistoryAdapter extends FirebaseRecyclerAdapter<getBooked,HistoryAdapter.myViewholder> {

    public HistoryAdapter(@NonNull FirebaseRecyclerOptions<getBooked> options2) {
        super(options2);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull getBooked getBookedObj) {
        holder.name.setText(getBookedObj.getName());
        holder.area.setText(getBookedObj.getArea());
        holder.cost.setText(getBookedObj.getCost());
        /*holder.userId.setText(getBookedObj.getUserid());*/
        holder.toggleButton.setText(getBookedObj.getToggle());

        ToggleValue toggleValue=new ToggleValue();
        /*String toggleOff,toggleOn;*/
        DatabaseReference reference,reff,hotelreff;
        FirebaseUser user,hotel;
        String userID,hotelID;

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        hotel=FirebaseAuth.getInstance().getCurrentUser();
        hotelreff=FirebaseDatabase.getInstance().getReference("Booked").child(userID);
        hotelID=hotel.getUid();

        /*hotelreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String key=ds.getKey();
                        String value=ds.getValue(String.class);

                        System.out.println(key);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



        reff= FirebaseDatabase.getInstance().getReference().child("Booked").child(userID);

        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hashMap=new HashMap();
                boolean on=((ToggleButton)v).isChecked();
                if(!on){
                    holder.toggleButton.setText("OFF");
                    toggleValue.setToggle("OFF"); //off
                    String off="OFF";
                    getBookedObj.setToggle("OFF");
                    reff.child(getRef(position).getKey()).child("toggle").setValue(getBookedObj.getToggle());
                    /*hashMap.put(getBookedObj.getToggle(),off);
                    reff.child("MXk7sFxY5kvA2kI13w4").updateChildren(hashMap);*/
                }
                else {
                    holder.toggleButton.setText("ON");
                    toggleValue.setToggle("ON");
                    /*reff.push().setValue(toggleValue);*/
                    String onn="ON";
                    getBookedObj.setToggle("ON");
                    reff.child(getRef(position).getKey()).child("toggle").setValue(getBookedObj.getToggle());
                    /*hashMap.put(getBookedObj.getToggle(),onn);
                    reff.child("MXk7sFxY5kvA2kI13w4").updateChildren(hashMap);*/
                }
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_history,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        TextView name,area,cost,userId;
        ToggleButton toggleButton;


        public myViewholder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.textname);
            area=itemView.findViewById(R.id.textarea);
            cost=itemView.findViewById(R.id.textcost);
            /*userId=itemView.findViewById(R.id.textuserId);*/
            toggleButton=itemView.findViewById(R.id.btnToggle);
        }

    }

}
