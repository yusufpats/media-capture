package com.yusufpats.mediaapp;

public class Code {

    public static String code = "package com.yusufpats.mediaapp;\n" +
            "\n" +
            "import android.content.Intent;\n" +
            "import android.graphics.Bitmap;\n" +
            "import android.net.Uri;\n" +
            "import android.os.Bundle;\n" +
            "import android.provider.MediaStore;\n" +
            "import android.text.method.ScrollingMovementMethod;\n" +
            "import android.view.View;\n" +
            "import android.widget.Button;\n" +
            "import android.widget.ImageView;\n" +
            "import android.widget.TextView;\n" +
            "import android.widget.VideoView;\n" +
            "\n" +
            "import androidx.annotation.Nullable;\n" +
            "import androidx.appcompat.app.AppCompatActivity;\n" +
            "\n" +
            "public class MainActivity extends AppCompatActivity {\n" +
            "\n" +
            "    private VideoView videoView;\n" +
            "    private ImageView imageView;\n" +
            "    private TextView textView;\n" +
            "\n" +
            "    @Override\n" +
            "    protected void onCreate(Bundle savedInstanceState) {\n" +
            "        super.onCreate(savedInstanceState);\n" +
            "        setContentView(R.layout.activity_main);\n" +
            "\n" +
            "        bindMediaViews();\n" +
            "        setupButtons();\n" +
            "    }\n" +
            "\n" +
            "    private void bindMediaViews() {\n" +
            "        imageView = findViewById(R.id.image_view);\n" +
            "        videoView = findViewById(R.id.video_player);\n" +
            "        textView = findViewById(R.id.code_text_view);\n" +
            "    }\n" +
            "\n" +
            "    private void setupButtons() {\n" +
            "        Button cameraButton = findViewById(R.id.camera_button);\n" +
            "        cameraButton.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View v) {\n" +
            "                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);\n" +
            "                startActivityForResult(cameraIntent, 123);\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        Button videoButton = findViewById(R.id.video_button);\n" +
            "        videoButton.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View v) {\n" +
            "                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);\n" +
            "                startActivityForResult(cameraIntent, 321);\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        Button galleryButton = findViewById(R.id.gallery_button);\n" +
            "        galleryButton.setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View v) {\n" +
            "                Intent galleryIntent = new Intent(Intent.ACTION_PICK,\n" +
            "                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);\n" +
            "\n" +
            "                startActivityForResult(galleryIntent, 786);\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        galleryButton.setOnLongClickListener(new View.OnLongClickListener() {\n" +
            "            @Override\n" +
            "            public boolean onLongClick(View v) {\n" +
            "                textView.setVisibility(View.VISIBLE);\n" +
            "                textView.setMovementMethod(new ScrollingMovementMethod());\n" +
            "                textView.setText(Code.code);\n" +
            "                return true;\n" +
            "            }\n" +
            "        });\n" +
            "\n" +
            "        textView.setOnLongClickListener(new View.OnLongClickListener() {\n" +
            "            @Override\n" +
            "            public boolean onLongClick(View v) {\n" +
            "                textView.setVisibility(View.GONE);\n" +
            "                return true;\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {\n" +
            "        super.onActivityResult(requestCode, resultCode, data);\n" +
            "        if (resultCode != RESULT_OK) {\n" +
            "            return;\n" +
            "        }\n" +
            "\n" +
            "        videoView.setVisibility(View.GONE);\n" +
            "        imageView.setVisibility(View.GONE);\n" +
            "\n" +
            "        if (requestCode == 123) {\n" +
            "            Bundle extras = data.getExtras();\n" +
            "            Bitmap imageBitmap = (Bitmap) extras.get(\"data\");\n" +
            "            showImage(imageBitmap);\n" +
            "        } else if (requestCode == 321) {\n" +
            "            showVideo(data.getData());\n" +
            "        } else if (requestCode == 786) {\n" +
            "            showImage(data.getData());\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private void showVideo(Uri uri) {\n" +
            "        videoView.setVisibility(View.VISIBLE);\n" +
            "        videoView.setVideoURI(uri);\n" +
            "        videoView.start();\n" +
            "    }\n" +
            "\n" +
            "    private void showImage(Uri uri) {\n" +
            "        imageView.setVisibility(View.VISIBLE);\n" +
            "        imageView.setImageURI(uri);\n" +
            "    }\n" +
            "\n" +
            "    private void showImage(Bitmap bm) {\n" +
            "        imageView.setVisibility(View.VISIBLE);\n" +
            "        imageView.setImageBitmap(bm);\n" +
            "    }\n" +
            "}\n";
}
