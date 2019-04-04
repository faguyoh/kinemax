package com.example.collo.kinemax;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

     TextView textViewUserEmail;
     TextView stbk;
    private Button buttonLogOut;
    private FirebaseDatabase mFirebase;
    private DatabaseReference mref;

   // private ListView DetailList;
   // List<UserInfo> userdetail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebase=FirebaseDatabase.getInstance();


        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        }
       FirebaseUser user=firebaseAuth.getCurrentUser();


        textViewUserEmail=(TextView)findViewById(R.id.textViewuserEmail);
        stbk=(TextView)findViewById(R.id.textViewBooked);
       // DetailList=(ListView)findViewById(R.id.list__view);

        //userdetail=new ArrayList<>();



       // textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogOut=(Button)findViewById(R.id.buttonLogOut);

        buttonLogOut.setOnClickListener(this);

        mref=mFirebase.getReference("Tickets").child(user.getUid());



    }



    @Override
    protected void onStart() {
        super.onStart();

//
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // String em=dataSnapshot.child("UserEmail").getValue().toString();
               // String st=dataSnapshot.child("seatNo").getValue().toString();


             // textViewUserEmail.setText(em);
             // stbk.setText(st);
              //  Map map=new HashMap();
             //   map.put("email",getEm)





            }


              /* for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    UserInfo userInfo =ds.getValue(UserInfo.class);

                    userdetail.add(userInfo);

                     }
                UserAdapter userAdapter=new UserAdapter(ProfileActivity.this, userdetail);
                DetailList.setAdapter(userAdapter);*/




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == buttonLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
