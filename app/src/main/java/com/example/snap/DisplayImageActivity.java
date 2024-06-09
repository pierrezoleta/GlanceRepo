package com.example.snap;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import RecycleViewStory.StoryObject;

public class DisplayImageActivity extends AppCompatActivity {

    String userId;
    private ImageView mImage;
    private boolean started = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_image);

        Bundle b = getIntent().getExtras();
        userId = b.getString("userId");

        mImage = findViewById(R.id.image);

        listenForData();

    }

    ArrayList<String> imageUrlList  = new ArrayList<>();

    private void listenForData(){


            DatabaseReference followingStoryDb= FirebaseDatabase.getInstance().getReference().child("users").child(userId);
            followingStoryDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String imageUrl = "";
                    long timestampBeg=0;
                    long timestampEnd=0;
                    for(DataSnapshot storySnapchot : snapshot.child("story").getChildren()){
                        if (storySnapchot.child("timestampBeg").getValue() !=null){
                            timestampBeg = Long.parseLong(storySnapchot.child("timestampBeg").getValue().toString());
                        }
                        if (storySnapchot.child("timestampEnd").getValue() !=null){
                            timestampEnd = Long.parseLong(storySnapchot.child("timestampEnd").getValue().toString());
                        }
                        if (storySnapchot.child("imageUrl").getValue() !=null){
                            imageUrl = storySnapchot.child("imageUrl").getValue().toString();
                        }

                        long timestampCurrent =System.currentTimeMillis();
                        if(timestampCurrent>=timestampBeg && timestampCurrent <=timestampEnd) {
                            imageUrlList.add(imageUrl);
                            if(!started){
                                started = true;
                                initializeDisplay();
                            }
                        }
                    }
                }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

        private int imageIterator = 0;
        private void initializeDisplay() {
            Glide.with(getApplication()).load(imageUrlList.get(imageIterator)).into(mImage);
            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeImage();
                }
            });
            final Handler handler = new Handler();
            final int delay = 10000;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeImage();
                    handler.postDelayed(this, delay);
                }
            }, delay);
        }

    private void changeImage() {
            if(imageIterator == imageUrlList.size() - 1){
                finish();
                return;
            }
            imageIterator++;
            Glide.with(getApplication()).load(imageUrlList.get(imageIterator)).into(mImage);
    }


}