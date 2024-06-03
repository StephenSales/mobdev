package com.example.mobdev.event;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.jdbc.BookmarkDAO;

public class EventCardVertical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean[] bookmarked = {false};
        ImageButton addToBookmarks = findViewById(R.id.addToBookmarks);

        addToBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookmarked[0]) {
                    bookmarked[0] = false;
                    addToBookmarks.setBackground(getDrawable(R.drawable.baseline_bookmark_24_gray));
                } else {
                    bookmarked[0] = true;
                    addToBookmarks.setBackground(getDrawable(R.drawable.baseline_bookmark_24));
                    BookmarkDAO.addBookmark(Storage.loggedInUser.getId(), 1,  () -> {
                        Looper.prepare();
                        Toast.makeText(getBaseContext(), "Event Added to Bookmarks", Toast.LENGTH_SHORT).show();
                        finish();
                    }, exception -> {
                        Looper.prepare();
                        Toast.makeText(getBaseContext(), "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_card_vertical);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cardEventVertical), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}