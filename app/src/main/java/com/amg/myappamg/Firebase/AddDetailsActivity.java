package com.amg.myappamg.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amg.myappamg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class AddDetailsActivity extends AppCompatActivity {
    EditText enterEmailEditText, usernameEditText, phoneNumberEditText;
    ImageView addProfileImageView;
    Button addBtn, viewBtn;

    // Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("my_users");
        storageReference = FirebaseStorage.getInstance().getReference();

        // Initialize UI elements
        enterEmailEditText = findViewById(R.id.enterEmailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addProfileImageView = findViewById(R.id.addProfileImageView);
        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(AddDetailsActivity.this, ViewDetailsActivity.class);
                startActivity(x);
            }
        });

        addProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDatabase();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            addProfileImageView.setImageURI(imageUri);
        }
    }

    private void addDataToDatabase() {
        String email = enterEmailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        // Check if the image is selected
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child("profile_images/" + System.currentTimeMillis() + ".jpg");

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Image uploaded successfully, get the download URL
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();

                                    // Create a data object
                                    Map<String, Object> data = new HashMap<>();
                                    data.put("email", email);
                                    data.put("username", username);
                                    data.put("phoneNumber", phoneNumber);
                                    data.put("profileImage", imageUrl);

                                    // Add the data to the database
                                    databaseReference.push().setValue(data);

                                    Toast.makeText(AddDetailsActivity.this, "Data added to the database", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddDetailsActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}