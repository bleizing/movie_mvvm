package com.bleizing.dimasciciltest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bleizing.dimasciciltest.R;
import com.bleizing.dimasciciltest.network.SearchMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SearchMovie> searchMovieArrayList;

    public MovieAdapter(Context context, ArrayList<SearchMovie> searchMovieArrayList) {
        this.context = context;
        this.searchMovieArrayList = searchMovieArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchMovie searchMovie = searchMovieArrayList.get(position);

        String title = String.format(context.getResources().getString(R.string.title_movie), searchMovie.getTitle(), searchMovie.getYear());
        holder.tvTitle.setText(title);
        Picasso.get().load(searchMovie.getPosterUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return searchMovieArrayList.size();
    }

    public void setSearchMovieArrayList(ArrayList<SearchMovie> searchMovieArrayList) {
        this.searchMovieArrayList = searchMovieArrayList;
        notifyDataSetChanged();
    }

    public ArrayList<SearchMovie> getSearchMovieArrayList() {
        return searchMovieArrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = (ImageView) itemView.findViewById(R.id.img_poster);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
