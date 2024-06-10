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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import RecycleViewStory.StoryObject;

public class DisplayImageActivity extends AppCompatActivity {

    String userId, chatOrStory;
    private ImageView mImage;
    private boolean started = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_image);

        Bundle b = getIntent().getExtras();
        userId = b.getString("userId");
        chatOrStory = b.getString("chatOrStory");

        mImage = findViewById(R.id.image);

        switch (chatOrStory){
            case "chat":
                listenForChat();
                break;
            case "story":
                listenForStory();
        }

    }

    private void listenForChat() {
        DatabaseReference chatDb = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("received").child(userId);
        chatDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageUrl = "";
                for(DataSnapshot chatSnapshot: snapshot.getChildren()){
                    if (chatSnapshot.child("imageUrl").getValue() !=null){
                        imageUrl = chatSnapshot.child("imageUrl").getValue().toString();
                    }
                        imageUrlList.add(imageUrl);
                        if(!started){
                            started = true;
                            initializeDisplay();
                        }
                        chatDb.child(chatSnapshot.getKey()).removeValue();
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    ArrayList<String> imageUrlList  = new ArrayList<>();

    private void listenForStory(){
        DatabaseReference followingStoryDb= FirebaseDatabase.getInstance().getReference().child("users").child(userId);
            followingStoryDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String imageUrl = "";
                    long timestampBeg=0;
                    long timestampEnd=0;
                    for(DataSnapshot storySnapshot : dataSnapshot.child("story").getChildren()){
                        if (storySnapshot.child("timestampBeg").getValue() !=null){
                            timestampBeg = Long.parseLong(storySnapshot.child("timestampBeg").getValue().toString());
                        }
                        if (storySnapshot.child("timestampEnd").getValue() !=null){
                            timestampEnd = Long.parseLong(storySnapshot.child("timestampEnd").getValue().toString());
                        }
                        if (storySnapshot.child("imageUrl").getValue() !=null){
                            imageUrl = storySnapshot.child("imageUrl").getValue().toString();
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