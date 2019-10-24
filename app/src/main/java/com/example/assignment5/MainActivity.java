    package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

    private Model model;
    private LyricAdapter lyricServer = null;
    private RecyclerView lyricRecycle = null;
    private GestureDetectorCompat detector = null;
    private StringBuilder fullLyric = new StringBuilder();
    private String blankSpace = " ";
    private int listPosition;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View v = lyricRecycle.findChildViewUnder(e.getX(), e.getY());
            TextView displayLyric = findViewById(R.id.displayLyric);

            if (v != null) {
                RecyclerView.ViewHolder holder = lyricRecycle.getChildViewHolder(v);
                if (holder instanceof LyricAdapter.LyricViewHolder) {
                    int position = holder.getAdapterPosition();
                    listPosition = position; // Storing the position for later
                    fullLyric.append(model.lyricList.get(position).toString()); // Using a StringBuilder to properly build lyric string
                    fullLyric.append(blankSpace); // Inserting a space after each lyric
                    displayLyric.setText(fullLyric.toString());


                    return true;
                }
            }
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = Model.getModel();

        lyricServer = new LyricAdapter(model);

        lyricRecycle = findViewById(R.id.displayRV);
        lyricRecycle.setAdapter(lyricServer);

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        lyricRecycle.setLayoutManager(manager);

        detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        lyricRecycle.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);

            }
        });

        Button insertLyric = findViewById(R.id.addLyric);
        insertLyric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText lyricInsert = findViewById(R.id.lyricInsert);

                try {
                    model.lyricList.add(new Model.Lyric(lyricInsert.getText().toString()));
                }
                catch (Exception e) {
                    TextView displayLyric = findViewById(R.id.displayLyric);
                    displayLyric.setText(e.toString());
                }

                lyricServer.notifyItemChanged(model.lyricList.size() - 1);
            }
        });

        Button clearLyric = findViewById(R.id.clearLyric);
        clearLyric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.lyricList.size() != 0) {
                    model.lyricList.remove(listPosition); // Clearing arrayList and clearing the TextViw to display nothing on the screen
                    TextView displayLyric = findViewById(R.id.displayLyric);
                    displayLyric.setText(null);
                    lyricServer.notifyDataSetChanged();

                }
            }
        });
    }
}
