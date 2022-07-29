package com.example.video_qa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class JavaScript extends AppCompatActivity {

    ImageView add,play;
    private Uri videoUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    UploadTask uploadTask;
    videomodel videomodel;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_script);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videomodel = new videomodel();
        pbar = findViewById(R.id.java_pbar);
        pbar.setVisibility(View.INVISIBLE);
        add = findViewById(R.id.add);
        play = findViewById(R.id.play);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent,1);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JavaScript.this,add_video_javascript.class);
                startActivity(intent);
            }
        });
    }

    private String getExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            videoUri = data.getData();
            videoView.setVideoURI(data.getData());
            uploadVideo();
        }


    }

    private void uploadVideo() {
        pbar = findViewById(R.id.java_pbar);
        pbar.setVisibility(View.VISIBLE);
        String desc = "This is a sample video for the application";
        String title = "Sample Video";
        storageReference = FirebaseStorage.getInstance().getReference("Video");
        databaseReference = FirebaseDatabase.getInstance().getReference("javascript");

        if(videoUri!=null){
            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "."+getExt(videoUri));
            uploadTask = reference.putFile(videoUri);

            Task<Uri> urltask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    pbar = findViewById(R.id.java_pbar);

                    pbar.setVisibility(View.INVISIBLE);
                    if(task.isSuccessful()){
                        Uri downloadurl = task.getResult();
                        Toast.makeText(JavaScript.this,"Video Uploaded",Toast.LENGTH_SHORT).show();
                        videomodel.setDesc("This is a sample video for the JavaScript Space");
                        videomodel.setTitle("JavaScript Sample Video");
                        videomodel.setUrl(downloadurl.toString());
                        String i = databaseReference.push().getKey();
                        databaseReference.child(i).setValue(videomodel);
                    }
                    else{
                        Toast.makeText(JavaScript.this,"Video Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}