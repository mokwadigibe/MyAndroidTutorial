package com.amg.myappamg.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amg.myappamg.Model.Chat;
import com.amg.myappamg.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ChatsFragment extends Fragment {

    private RecyclerView recyclerViewChats;
    private FloatingActionButton fab;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Chat> chatList = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerViewChats = view.findViewById(R.id.recyclerViewChats);
        fab = view.findViewById(R.id.fab);

        // Setup RecyclerView
        recyclerViewChats.setLayoutManager(new LinearLayoutManager(getContext()));
        chatAdapter = new ChatAdapter(chatList, this::onChatClicked);
        recyclerViewChats.setAdapter(chatAdapter);

        fab.setOnClickListener(v -> createNewChat());

        loadChats();

        return view;
    }

    private void loadChats() {
        db.collection("chats").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                chatList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Chat chat = document.toObject(Chat.class);
                    chatList.add(chat);
                }
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createNewChat() {
        // Generate a random chat name with a combination of letters and numbers
        String chatName = generateRandomChatName();
        Chat newChat = new Chat(chatName, "No messages yet");
        db.collection("chats").add(newChat).addOnSuccessListener(documentReference -> {
            chatList.add(newChat);
            chatAdapter.notifyItemInserted(chatList.size() - 1);
        });
    }

    private String generateRandomChatName() {
        // Define characters and numbers that can be part of the chat name
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // Create a StringBuilder to build the chat name
        StringBuilder chatName = new StringBuilder("Chat ");

        // Append a random character or number to the chat name
        Random random = new Random();
        for (int i = 0; i < 5; i++) { // You can adjust the length of the chat name
            if (i % 2 == 0) {
                chatName.append(characters.charAt(random.nextInt(characters.length())));
            } else {
                chatName.append(numbers.charAt(random.nextInt(numbers.length())));
            }
        }

        return chatName.toString();
    }


    private void onChatClicked(int position) {
        Chat selectedChat = chatList.get(position);
        // Open the chat details or send a message. You can start a new activity or fragment for this.
    }


    // Your RecyclerView Adapter
    public static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

        private List<Chat> chats;
        private OnChatClickListener onChatClickListener;

        public ChatAdapter(List<Chat> chats, OnChatClickListener onChatClickListener) {
            this.chats = chats;
            this.onChatClickListener = onChatClickListener;
        }

        @NonNull
        @Override
        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
            return new ChatViewHolder(view, onChatClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            Chat chat = chats.get(position);
            holder.chatNameTextView.setText(chat.getChatName());
            holder.lastMessageTextView.setText(chat.getLastMessage());
        }

        @Override
        public int getItemCount() {
            return chats.size();
        }

        public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView chatNameTextView;
            TextView lastMessageTextView;
            OnChatClickListener onChatClickListener;

            public ChatViewHolder(@NonNull View itemView, OnChatClickListener onChatClickListener) {
                super(itemView);
                chatNameTextView = itemView.findViewById(R.id.chatNameTextView);
                lastMessageTextView = itemView.findViewById(R.id.lastMessageTextView);
                this.onChatClickListener = onChatClickListener;

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                onChatClickListener.onChatClick(getAdapterPosition());
            }
        }

        public interface OnChatClickListener {
            void onChatClick(int position);
        }
    }
}