package com.example.video_qa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class add_video_cp extends AppCompatActivity {

    ViewPager2 viewPager2;
    video_adapter adapter;
    ImageView share,like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_cp);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        share = findViewById(R.id.share);

        viewPager2 = findViewById(R.id.vpager);
        FirebaseRecyclerOptions<videomodel> options = new FirebaseRecyclerOptions.Builder<videomodel>().setQuery(FirebaseDatabase.getInstance().getReference("cp"),videomodel.class).build();
        adapter = new video_adapter(options);
        viewPager2.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    public void sharehere(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"Hello");
        intent.setType("text/plain");
        Intent intent1 = Intent.createChooser(intent,null);
        startActivity(intent1);
    }

    public void liked(View view){
        like = findViewById(R.id.like);
        like.setImageResource(R.drawable.heart_full);
    }
}