package com.amg.myappamg.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amg.myappamg.Adapter.MessageAdapter;
import com.amg.myappamg.Model.Message;
import com.amg.myappamg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private EditText editTextMessage;
    private Button buttonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MessageAdapter(messages);
        recyclerView.setAdapter(adapter);

        // Setup the input and send button
        editTextMessage = view.findViewById(R.id.editTextMessage);
        buttonSend = view.findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        loadMessages();

        return view;
    }

    private void loadMessages() {
        firestore.collection("messages").orderBy("timestamp")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("ChatFragment", "Error loading messages", error);
                            return;
                        }

                        messages.clear();
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            Message message = doc.toObject(Message.class);
                            messages.add(message);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void sendMessage() {
        String text = editTextMessage.getText().toString().trim();
        if (!text.isEmpty()) {
            Message message = new Message("mokwadiamg@gmail.com", text, System.currentTimeMillis());
            firestore.collection("messages").add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("ChatFragment", "Message sent with ID: " + documentReference.getId());
                            editTextMessage.setText(""); // Clear the input field
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("ChatFragment", "Error sending message", e);
                            Toast.makeText(getContext(), "Error sending message.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}