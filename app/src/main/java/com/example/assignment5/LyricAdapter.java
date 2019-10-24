package com.example.assignment5;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LyricAdapter extends RecyclerView.Adapter<LyricAdapter.LyricViewHolder>  {


    private Model model;

    public LyricAdapter(Model m) {
        super();
        this.model = m;
    }

    public static class LyricViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;

        public LyricViewHolder(LinearLayout l) {
            super(l);
            layout = l;

        }
    }

    @NonNull
    @Override
    public LyricAdapter.LyricViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.lyricview, parent, false);

        LyricViewHolder viewHolder = new LyricViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LyricViewHolder holder, int position) {
        LinearLayout held = holder.layout;
        TextView tvLyric = held.findViewById(R.id.tvLyric);

        tvLyric.setText(model.lyricList.get(position).word);

    }

    @Override
    public int getItemCount() {
        return model.lyricList.size();
    }



}
