package io.realworld.android.programmermoviewatchlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    Context context;
    String[] movieNames;
    String[] movieImageUrls;

    public TaskAdapter(Context context, String[] movieNames, String[] movieImageUrls) {
        this.context = context;
        this.movieNames = movieNames;
        this.movieImageUrls = movieImageUrls;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_layout, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        String movieName = movieNames[position];
        String movieImageUrl = movieImageUrls[position];

        holder.movieName.setText(movieName);
        Glide.with(context).load(movieImageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).dontAnimate().into(holder.movieImage);
        holder.layout.setOnClickListener(v -> new AlertDialog.Builder(context)
                .setTitle("Information")
                .setMessage(movieName)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()).show());
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageView movieImage;
        CardView layout;
        ProgressBar progressBar;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieName = itemView.findViewById(R.id.movie_name);
            layout = itemView.findViewById(R.id.task_item_layout);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
