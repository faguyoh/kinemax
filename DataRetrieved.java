package com.example.collo.kinemax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DataRetrieved extends AppCompatActivity {
    private ListView DetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieved);
        DetailList=(ListView)findViewById(R.id.list__view);
    }
}
