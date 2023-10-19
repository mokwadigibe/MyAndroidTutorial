package com.amg.myappamg.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

        // Initialize your menuImageView
        menuImageView = findViewById(R.id.menuImageView);

        // Create a PopupMenu
        final PopupMenu popupMenu = new PopupMenu(this, menuImageView);

        // Inflate the menu resource (menuItems.xml in this example)
        popupMenu.getMenuInflater().inflate(R.menu.menu_item, popupMenu.getMenu());

        // Set a click listener for menuImageView to show the popup menu
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });

        // Set a listener for menu item clicks
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item clicks here
                if (item.getItemId() == R.id.menu_item_1) {
                    // Perform an action for menu item 1
                    Toast.makeText(WhatsappActivity.this, "You clicked New Group", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_2) {
                    // Perform an action for menu item 2
                    Toast.makeText(WhatsappActivity.this, "You clicked New Broadcast", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_3) {
                    // Perform an action for menu item 3
                    Toast.makeText(WhatsappActivity.this, "You clicked Linked Devices", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_4) {
                    // Perform an action for menu item 4
                    Toast.makeText(WhatsappActivity.this, "You clicked Starred Messages", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_5) {
                    // Perform an action for menu item 4
                    Toast.makeText(WhatsappActivity.this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            }
        });


        // Set click listeners for TextViews
        chatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatFragment();
                highlightSelectedTextView(chatTextView);
            }
        });

        statusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatusFragment();
                highlightSelectedTextView(statusTextView);
            }
        });

        callsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallsFragment();
                highlightSelectedTextView(callsTextView);
            }
        });

        // to open the chatsfragment as default
        openChatFragment();
        highlightSelectedTextView(chatTextView);
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

    // Helper method to highlight the selected TextView
    private void highlightSelectedTextView(TextView selectedTextView) {
        // Reset the underline for all TextViews
        chatTextView.setPaintFlags(0);
        statusTextView.setPaintFlags(0);
        callsTextView.setPaintFlags(0);

        // Set underline for the selected TextView
        selectedTextView.setPaintFlags(selectedTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


}
