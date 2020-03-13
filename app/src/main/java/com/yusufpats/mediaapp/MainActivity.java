package com.yusufpats.mediaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindMediaViews();
        setupButtons();
    }

    private void bindMediaViews() {
        imageView = findViewById(R.id.image_view);
        videoView = findViewById(R.id.video_player);
        textView = findViewById(R.id.code_text_view);
    }

    private void setupButtons() {
        Button cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 123);
            }
        });

        Button videoButton = findViewById(R.id.video_button);
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(cameraIntent, 321);
            }
        });

        Button galleryButton = findViewById(R.id.gallery_button);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, 786);
            }
        });

        galleryButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setVisibility(View.VISIBLE);
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView.setText(Code.code);
                return true;
            }
        });

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setVisibility(View.GONE);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        videoView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        if (requestCode == 123) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            showImage(imageBitmap);
        } else if (requestCode == 321) {
            showVideo(data.getData());
        } else if (requestCode == 786) {
            showImage(data.getData());
        }
    }

    private void showVideo(Uri uri) {
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    private void showImage(Uri uri) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageURI(uri);
    }

    private void showImage(Bitmap bm) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(bm);
    }
}
