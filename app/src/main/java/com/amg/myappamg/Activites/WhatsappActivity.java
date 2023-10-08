package com.amg.myappamg.Activites;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amg.myappamg.Fragments.*;
import com.amg.myappamg.R;

public class WhatsappActivity extends AppCompatActivity {
    TextView chatTextView, statusTextView, callsTextView;
    ImageView cameraImageView, menuImageView, searchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        // Initialize TextView elements
        chatTextView = findViewById(R.id.chatTextView);
        statusTextView = findViewById(R.id.statusTextView);
        callsTextView = findViewById(R.id.callTextView);

        // Set click listeners for TextViews
        chatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatFragment();
            }
        });

        statusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatusFragment();
            }
        });

        callsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallsFragment();
            }
        });
    }

    private void openChatFragment() {
        // Creating an instance of the Chat Fragment
        Fragment chatFragment = new ChatsFragment();
        // Replace the current fragment with the Chat Fragment
        replaceFragment(chatFragment);
    }

    private void openStatusFragment() {
        Fragment statusFragment = new StatusFragment();
        replaceFragment(statusFragment);
    }

    private void openCallsFragment() {
        Fragment callsFragment = new CallsFragment();
        replaceFragment(callsFragment);
    }

    // Helper method to replace the current fragment with a new fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


