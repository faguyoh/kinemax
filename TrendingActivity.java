package com.example.collo.kinemax;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;


public class TrendingActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;
    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        //action support
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trending in Entertainment");

         mSharedPref=getSharedPreferences("SortSettings",MODE_PRIVATE);
         String mSorting=mSharedPref.getString("Sort","Newest"); //where if no settings
        //if selected newest will be default

        //since default is newesr so for first time it will display newest post first

        if(mSorting.equals("Newest")){
            mLayoutManager=new LinearLayoutManager(this);

            //this will load items from bottom(newest first)
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if(mSorting.equals("Oldest")){

            mLayoutManager=new LinearLayoutManager(this);
            //this will load items from top(oldest first)
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);

        }



        //RecyclerView
        mrecyclerView=findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);

        //setLayout as LinearLayout
        mrecyclerView.setLayoutManager(mLayoutManager);

        //send Query as FirabaseDatabase
        mfirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mfirebaseDatabase.getReference("Data");
    }

    //search data
    private void firebaseSearch(String searchText){

        //convert string entered in searchview to lowercase
        String query=searchText.toLowerCase();

        Query firebaseSearchQuery=mRef.orderByChild("search").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        firebaseSearchQuery

                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                        viewHolder.setDetails(getApplicationContext(), model.getTitle(),model.getImage(), model.getDescription());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void OnItemClick(View view, int position) {

                                //VIEW
                                TextView mTitleTv=view.findViewById(R.id.rTitletv);
                                TextView mDescTv=view.findViewById(R.id.rDescriptionTv);
                                ImageView mImageView=view.findViewById(R.id.rImageView);

                                //get data from views
                                String mTitle=mTitleTv.getText().toString();
                                String mDesc=mDescTv.getText().toString();
                                Drawable mDrawable=mImageView.getDrawable();
                                Bitmap mbitmap=((BitmapDrawable)mDrawable).getBitmap();

                                //pass this data to new activity
                                Intent intent= new Intent(view.getContext(), TrendingDetailActivity.class);

                                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                mbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes=stream.toByteArray();
                                intent.putExtra("image", bytes); //put bitmap as array of bytes
                                intent.putExtra("title", mTitle);  //put title
                                intent.putExtra("description", mDesc);  //put description

                                startActivity(intent); //start activity


                            }

                            @Override
                            public void OnLongClick(View view, int position) {
                                //later implementation
                            }
                        });

                        return viewHolder;
                    }

                };

        //setadapter to recycleview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    //load data into recyclerview onstart

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef

                ){
            protected void populateViewHolder(ViewHolder viewHolder, Model model, int position){

                viewHolder.setDetails(getApplicationContext(), model.getTitle(),model.getImage(), model.getDescription());

            }




                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);

                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {

                        //VIEW
                        TextView mTitleTv=view.findViewById(R.id.rTitletv);
                        TextView mDescTv=view.findViewById(R.id.rDescriptionTv);
                        ImageView mImageView=view.findViewById(R.id.rImageView);

                        //get data from views
                        String mTitle=mTitleTv.getText().toString();
                        String mDesc=mDescTv.getText().toString();
                        Drawable mDrawable=mImageView.getDrawable();
                        Bitmap mbitmap=((BitmapDrawable)mDrawable).getBitmap();

                        //pass this data to new activity
                        Intent intent= new Intent(view.getContext(), TrendingDetailActivity.class);

                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        mbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bytes=stream.toByteArray();
                        intent.putExtra("image", bytes); //put bitmap as array of bytes
                        intent.putExtra("title", mTitle);  //put title
                        intent.putExtra("description", mDesc);  //put description

                        startActivity(intent); //start activity


                    }

                    @Override
                    public void OnLongClick(View view, int position) {
                 //later implementation
                    }
                });

                return viewHolder;
                    }
                };

        //setadapter to recycleview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adda items to the action bar if it preseent
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        //handle other action bar item click here

        if(id == R.id.action_sort){
            //display alert dialog to choose sorting
            showSortDialog();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog(){

        //options to display dialog
        String [] sortOptions= {"Newest", "Oldest"};

        //create alert Dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setIcon(R.drawable.ic_action_sort) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //The 'which' argument contains the index of the selected item
                        //0 means "Newest" and 1 means "Oldest"
                        if(which == 0){
                            //sort by newest
                            //Edit our shared preference
                            SharedPreferences.Editor editor=mSharedPref.edit();
                            editor.putString("Sort", "Newest"); //where "sort" is key & newest is value
                            editor.apply();//apply save the value in our shared preference
                            recreate();//restart activity to take effect

                        }else if(which == 1){
                            //sort oldest
                           //Edit our shared preference
                            SharedPreferences.Editor editor=mSharedPref.edit();
                            editor.putString("Sort", "Oldest"); //where "sort" is key & oldest is value
                            editor.apply();//apply save the value in our shared preference
                            recreate();//restart activity to take effect
                        }
                    }
                });
        builder.show();

    }
}
