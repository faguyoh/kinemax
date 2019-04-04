package com.example.collo.kinemax;

/*import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserInfo> {
   


    private Activity context;
    private List<UserInfo> userdetail;

    public UserAdapter(Activity context, List<UserInfo> userdetail){

        super(context, R.layout.listviewprofile, userdetail);
        this.context=context;
        this.userdetail=userdetail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.listviewprofile, null, true);
       TextView textViewUserEmail=(TextView)view.findViewById(R.id.textViewuserEmail);
        TextView textViewBooked=(TextView)view.findViewById(R.id.textViewBooked);

        UserInfo userInfo=userdetail.get(position);


        textViewUserEmail.setText(userInfo.getUserEmail());
                textViewBooked.setText(String.valueOf(userInfo.getSeatNo()));


        return view;
    }
}
*/